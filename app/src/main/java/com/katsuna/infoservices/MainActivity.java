package com.katsuna.infoservices;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.katsuna.commons.utils.DeviceUtils;

public class MainActivity extends Activity {

    private boolean setupFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        while (!setupFinished) {
//
//            if (DeviceUtils.isUserSetupComplete(getApplicationContext())) {
//                try {
//                    Thread.sleep(1000 * 60 * 10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                super.onCreate(savedInstanceState);
//
//                setupFinished = true;
//                Intent activityIntent = new Intent(getApplicationContext(),PermissionsActivity.class);
//                getApplicationContext().startActivity(activityIntent);
//                finish();
//
//            } else {
//                try {
//                    Thread.sleep(1000 * 60 * 1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        Intent activityIntent = new Intent(getApplicationContext(), ReportingService.class);
        getApplicationContext().startService(activityIntent);
        finish();
    }


}
