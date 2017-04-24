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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
            String[] katsunaVersion = propReader().split(" ");
            System.out.println(katsunaVersion[1]);





            final RegisterFacade rFacade = new RegisterFacade(Long.parseLong(imei), imsi, countryCode, 30, "male", gmt, timestamp, katsunaVersion[1]);
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
}
