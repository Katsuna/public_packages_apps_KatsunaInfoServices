package com.katsuna.infoservices.Alarms;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.jaredrummler.android.device.DeviceName;
import com.katsuna.infoservices.Preferences.PreferencesProvider;
import com.katsuna.infoservices.facade.RegisterFacade;
import com.katsuna.infoservices.managers.UserManager;
import com.katsuna.infoservices.MainActivity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by cmitatakis on 4/12/2017.
 */

public class ReportingServerFunctions {

    public static  void register() {


        final Activity activity = MainActivity.getActivity();

        RegisterFacade registerFacade = PreferencesProvider.LoggedUserInfo(activity);
        if (registerFacade != null && !registerFacade.getToken().isEmpty() && !registerFacade.getUserUniqueId().isEmpty()) {
            String deviceName = DeviceName.getDeviceName();
            String reqString = Build.MANUFACTURER
                    + " " + Build.MODEL + " " + Build.VERSION.RELEASE
                    + " " + Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName();
            System.out.println("User has already been sign in " + registerFacade.getToken() + " " + registerFacade.getUserUniqueId() + " name: " + deviceName);

            System.out.println(reqString);

        } else {
            Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());

            TelephonyManager manager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
            String countryCode = manager.getSimCountryIso();
            if (countryCode.isEmpty())
                countryCode = Locale.getDefault().getCountry();
            String imei = manager.getDeviceId();
            String imsi = manager.getSubscriberId();
            Date today = Calendar.getInstance().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("Z");
            String gmt = sdf.format(today);
            gmt = gmt.substring(0, Math.min(gmt.length(), 3));




            final RegisterFacade rFacade = new RegisterFacade(Long.parseLong(imei), imsi, countryCode, 30, "male", gmt, timestamp);
            UserManager.register(activity, rFacade, new UserManager.RegisterOperationCompletedListener() {
                @Override
                public void OperationCompleted(UserManager.OperationCompletedStatus status, final RegisterFacade registerFacade) {
                    switch (status) {
                        case Success:
                            PreferencesProvider.SetLoggedUserInfo(activity, registerFacade);
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
}
