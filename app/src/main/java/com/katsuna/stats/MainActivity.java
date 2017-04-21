package com.katsuna.stats;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.katsuna.stats.Alarms.ReportingServerAlarm;
import com.katsuna.stats.httpRequests.HttpManager;
import com.katsuna.stats.httpRequests.TokenRetryPolicy;

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
