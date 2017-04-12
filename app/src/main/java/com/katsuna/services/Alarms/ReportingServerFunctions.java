package com.katsuna.services.Alarms;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.katsuna.services.MainActivity;
import com.katsuna.services.Preferences.PreferencesProvider;
import com.katsuna.services.facade.RegisterFacade;
import com.katsuna.services.managers.UserManager;

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
            System.out.println("User has already been sign in " + registerFacade.getToken() + " " + registerFacade.getUserUniqueId());

        } else {
            Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());

            TelephonyManager manager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
            String countryCode = manager.getSimCountryIso();
            if (countryCode.isEmpty())
                countryCode = Locale.getDefault().getCountry();
            String imei = manager.getDeviceId();
            Date today = Calendar.getInstance().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("Z");
            String gmt = sdf.format(today);
            gmt = gmt.substring(0, Math.min(gmt.length(), 3));

            System.out.println(gmt);

            final RegisterFacade rFacade = new RegisterFacade(Long.parseLong(imei), "305899934E01D29B377E1168C626732501E0670E", countryCode, 30, "male", gmt, timestamp);
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
