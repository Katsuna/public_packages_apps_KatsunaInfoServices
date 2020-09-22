/**
* Copyright (C) 2020 Manos Saratsis
*
* This file is part of Katsuna.
*
* Katsuna is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Katsuna is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Katsuna.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.katsuna.infoservices.Alarms;

import android.content.Context;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.jaredrummler.android.device.DeviceName;
import com.katsuna.infoservices.KatsunaInfoServicesApplication;
import com.katsuna.infoservices.Preferences.PreferencesProvider;
import com.katsuna.infoservices.facade.LocationCollectionFacade;
import com.katsuna.infoservices.facade.LocationFacade;
import com.katsuna.infoservices.facade.RegisterFacade;
import com.katsuna.infoservices.facade.UserFacade;
import com.katsuna.infoservices.facade.UserTimezoneFacade;
import com.katsuna.infoservices.http.JSONRequest;
import com.katsuna.infoservices.httpRequests.HttpManager;
import com.katsuna.infoservices.httpRequests.KatsunaResponseHandler;
import com.katsuna.infoservices.httpRequests.ResponseWrapper;
import com.katsuna.infoservices.httpRequests.ServerConstants;
import com.katsuna.infoservices.managers.UserManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by cmitatakis on 4/12/2017.
 */

public class ReportingServerFunctions {

    private static final String AUTHORITY = "com.katsuna.services.sqlite.locationDb.PointOfInterestContentProvider";

    private static final String POINTS_OF_INTEREST_PATH = "PointsOfInterest";

    public static final Uri POINTS_OF_INTEREST_URI = Uri.parse("content://" + AUTHORITY
            + "/" + POINTS_OF_INTEREST_PATH);

    public static final String TABLE_POINTS_OF_INTEREST = "PointsOfInterest";

    public static final String id = "id";
    public static final String latitude = "latitude";
    public static final String longitude = "longitude";
    public static final String speedAv = "speedAv";
    public static final String totalTime = "total_time";
    public static final String timesVisited = "times";
    public static final String description = "description";

    public static void register() {


        final RegisterFacade userFacade = PreferencesProvider.LoggedUserInfo();
        if (userFacade != null && !userFacade.getToken().isEmpty() ) {
            String deviceName = DeviceName.getDeviceName();
            String reqString = Build.MANUFACTURER
                    + " " + Build.MODEL + " " + Build.VERSION.RELEASE
                    + " " + Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName();
       //     System.out.println("User has already been sign in " + userFacade.getToken() + " " + userFacade.getRefreshToken() + " name: " + deviceName);
//             if (BuildConfig.DEBUG) {
//                 sendPointsOfInterest();
//             }


        } else {
            Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());

            TelephonyManager manager = (TelephonyManager) KatsunaInfoServicesApplication.getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
            String countryCode = manager.getSimCountryIso();
            if (countryCode.isEmpty())
                countryCode = Locale.getDefault().getCountry();
            String imei = manager.getDeviceId();
            String imsi = manager.getSubscriberId();
            Date today = Calendar.getInstance().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("Z");
            String gmt = sdf.format(today);
            gmt = gmt.substring(0, Math.min(gmt.length(), 3));
            String[] katsunaVersion = propReader().split(" ");
            String model = Build.MANUFACTURER
                    + " " + Build.MODEL;

            UserFacade rFacade = new UserFacade(Long.parseLong(imei), imsi, countryCode, 30, "male", katsunaVersion[1], model);
            UserTimezoneFacade userTimezoneFacade = new UserTimezoneFacade();
            userTimezoneFacade.setDateTimestamp(timestamp.getTime());
            userTimezoneFacade.setTimezoneValue(gmt);

            final RegisterFacade registerFacade = new RegisterFacade(rFacade, userTimezoneFacade);

            UserManager.register(registerFacade, new UserManager.RegisterOperationCompletedListener() {
                @Override
                public void OperationCompleted(UserManager.OperationCompletedStatus status, final RegisterFacade registerfacade) {
                    switch (status) {
                        case Success:
                            PreferencesProvider.SetLoggedUserInfo(registerfacade);
                            break;
                        case ValidationError:
                            break;
                        case Error:
                            break;
                    }
                }
            });
        }
    }


    public static void sendPointsOfInterest()
    {
//        List<LocationFacade> locations = new ArrayList<LocationFacade>();
//        LocationFacade locationFacade = new LocationFacade();
//        locationFacade.setLatitude(22.4);
//        locationFacade.setLongitude(34.5);
//        locations.add(locationFacade);
//        locations.add(locationFacade);
//        locations.add(locationFacade);
//        locations.add(locationFacade);




        LocationCollectionFacade locationFacades = new LocationCollectionFacade();
        locationFacades.setLocations(getAllPointsOfInterest());

        UserManager.savePointsOfInterest(locationFacades, new UserManager.SavePointsOfInterestOperationCompletedListener() {
            @Override
            public void OperationCompleted(UserManager.OperationCompletedStatus status) {
                switch (status) {
                    case Success:
                        break;
                    case ValidationError:
                        break;
                    case Error:
                        break;
                }
            }
        });
    }


    private static String propReader() {
        Process process = null;
        try {
            process = new ProcessBuilder().command("/system/bin/getprop")
                    .redirectErrorStream(true).start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        InputStream in = process.getInputStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        StringBuilder log = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("ro.katsuna.version"))
                    log.append(line + "\n");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        process.destroy();
        return log.toString();
    }

    public static ArrayList<LocationFacade> getAllPointsOfInterest(){
        ArrayList<LocationFacade> locations = new ArrayList();

        String[] projection = { latitude,
                longitude
        };

        Cursor cursor = KatsunaInfoServicesApplication.getAppContext().getContentResolver().query(POINTS_OF_INTEREST_URI, projection,null,null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LocationFacade record = new LocationFacade();
                record.setLatitude(Double.valueOf(cursor.getString(0)));
                record.setLongitude(Double.valueOf(cursor.getString(1)));

                locations.add(record);
            } while (cursor.moveToNext());
        }
        return locations;
    }
}
