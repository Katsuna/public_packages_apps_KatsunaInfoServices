package com.katsuna.infoservices;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.katsuna.commons.utils.DeviceUtils;

public class MainActivity extends Activity {

    private boolean setupFinished = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        while (!setupFinished) {

            if (DeviceUtils.isUserSetupComplete(getApplicationContext())) {
                super.onCreate(savedInstanceState);
                Intent myIntent = new Intent(this, ReportingService.class);
                setupFinished = true;
                startService(myIntent);
                finish();
            } else {
                try {
                    Thread.sleep(1000 * 60 * 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
