package com.katsuna.infoservices.Alarms;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import com.katsuna.infoservices.KatsunaInfoServicesApplication;
import com.katsuna.infoservices.Preferences.PreferencesProvider;

/**
 * Created by cmitatakis on 4/12/2017.
 */

public class ReportingServerAlarm  extends BroadcastReceiver {


    private static final int PERMISSION_ALL = 1;
    String[] PERMISSIONS = { Manifest.permission.READ_PHONE_STATE};


    @Override
    public void onReceive(Context context, Intent intent) {

        if (hasPermissions(KatsunaInfoServicesApplication.getAppContext(), PERMISSIONS)) {

            ReportingServerFunctions.register();

        }

    }

    public void setAlarm(Context context)
    {
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, ReportingServerAlarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 60 * 24, pi); // Millisec * Second * Minute
    }

    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, ReportingServerAlarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

}
