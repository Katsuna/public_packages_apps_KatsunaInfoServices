package com.katsuna.infoservices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.katsuna.infoservices.Alarms.ReportingServerAlarm;
import com.katsuna.infoservices.httpRequests.HttpManager;
import com.katsuna.infoservices.httpRequests.TokenRetryPolicy;

/**
 * Created by cmitatakis on 5/2/2017.
 */

public class ReportingService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        HttpManager.init(this.getApplicationContext(), new TokenRetryPolicy());

        ReportingServerAlarm reportingServerAlarm = new ReportingServerAlarm();
        reportingServerAlarm.setAlarm(this);
        return START_STICKY;
    }
}
