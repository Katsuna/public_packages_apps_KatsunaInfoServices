package com.katsuna.services;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.katsuna.services.Alarms.ReportingServerAlarm;
import com.katsuna.services.Preferences.PreferencesProvider;
import com.katsuna.services.facade.RegisterFacade;
import com.katsuna.services.httpRequests.HttpManager;
import com.katsuna.services.httpRequests.TokenRetryPolicy;
import com.katsuna.services.managers.UserManager;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {

    public static Context getContext() {
        return applicationContext;
    }
    static Context applicationContext;
    private static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_main);
        applicationContext = this.getApplicationContext();
        HttpManager.init(this.getApplicationContext(), new TokenRetryPolicy(this));

        ReportingServerAlarm reportingServerAlarm = new ReportingServerAlarm();
        reportingServerAlarm.setAlarm(this);
    }


    public static Activity getActivity()
    {
        return activity;
    }

}
