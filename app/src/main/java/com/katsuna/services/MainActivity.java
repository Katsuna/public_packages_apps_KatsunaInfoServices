package com.katsuna.services;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.katsuna.services.Preferences.PreferencesProvider;
import com.katsuna.services.facade.RegisterFacade;
import com.katsuna.services.httpRequests.HttpManager;
import com.katsuna.services.managers.UserManager;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private  final int period = 1000 * 60 *15;

    public static Context getContext() {
        return applicationContext;
    }
    static Context applicationContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applicationContext = this.getApplicationContext();
        HttpManager.init(this.getApplicationContext());

        RegisterFacade registerFacade = PreferencesProvider.LoggedUserInfo(this);
        if(registerFacade != null && !registerFacade.getToken().isEmpty() && !registerFacade.getUserUniqueId().isEmpty())
        {
            System.out.println("User has already been sign in "+registerFacade.getToken()+" "+registerFacade.getUserUniqueId());

        }
        else
            register();
    }

    private void register() {

        Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());

        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
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
        final Activity activity = this;
        UserManager.register(this, rFacade, new UserManager.RegisterOperationCompletedListener() {
            @Override
            public void OperationCompleted(UserManager.OperationCompletedStatus status, final RegisterFacade registerFacade) {
                switch (status) {
                    case Success:
                        PreferencesProvider.SetLoggedUserInfo(activity,registerFacade);
                        break;
                    case ValidationError:
                        new Thread() {
                            public void run() {
                                try {

                                    final boolean[] success = {false};
                                    while (!success[0]) {

                                        UserManager.register(activity, rFacade, new UserManager.RegisterOperationCompletedListener() {

                                            @Override
                                            public void OperationCompleted(UserManager.OperationCompletedStatus status, RegisterFacade regFacade) {

                                                switch (status) {
                                                    case Success:
                                                        PreferencesProvider.SetLoggedUserInfo(activity,registerFacade);
                                                        success[0] = true;
                                                        break;
                                                    case Error:
                                                        break;
                                                    case ValidationError:
                                                        break;
                                                }

                                            }
                                        });

                                        Thread.sleep(period);
                                    }

                                } catch (InterruptedException v) {
                                    System.out.println(v);
                                }
                            }
                        }.start();
                        break;
                    case Error:
                        new Thread() {
                            public void run() {
                                try {

                                    final boolean[] success = {false};
                                    while (!success[0]) {

                                        UserManager.register(activity, rFacade, new UserManager.RegisterOperationCompletedListener() {

                                            @Override
                                            public void OperationCompleted(UserManager.OperationCompletedStatus status, RegisterFacade regFacade) {

                                                switch (status) {
                                                    case Success:
                                                        success[0] = true;
                                                        break;
                                                    case Error:
                                                        break;
                                                    case ValidationError:
                                                        break;
                                                }

                                            }
                                        });

                                        Thread.sleep(period);
                                    }

                                } catch (InterruptedException v) {
                                    System.out.println(v);
                                }
                            }
                        }.start();
                        break;

                }
            }
        });
    }
}
