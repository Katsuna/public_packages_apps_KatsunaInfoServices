package com.katsuna.infoservices.Alarms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.katsuna.infoservices.MainActivity;
import com.katsuna.infoservices.ReportingService;

/**
 * Created by cmitatakis on 4/12/2017.
 */

public class StartReportingServerActivity   extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Intent myIntent = new Intent(context, ReportingService.class);
            context.startService(myIntent);
        }

    }
}
