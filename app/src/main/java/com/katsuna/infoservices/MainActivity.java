package com.katsuna.infoservices;

import android.app.Activity;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.katsuna.infoservices.Alarms.ReportingServerAlarm;
import com.katsuna.infoservices.httpRequests.HttpManager;
import com.katsuna.infoservices.httpRequests.TokenRetryPolicy;
import com.katsuna.stats.R;

public class MainActivity extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        HttpManager.init(this.getApplicationContext(), new TokenRetryPolicy());

        ReportingServerAlarm reportingServerAlarm = new ReportingServerAlarm();
        reportingServerAlarm.setAlarm(this);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
