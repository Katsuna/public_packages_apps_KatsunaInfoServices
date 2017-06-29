package com.katsuna.infoservices;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.katsuna.infoservices.Preferences.PreferencesProvider;
import com.katsuna.infoservices.facade.RegisterFacade;

public class PermissionsActivity extends Activity {

    private static final int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.READ_PHONE_STATE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final RegisterFacade userFacade = PreferencesProvider.LoggedUserInfo();


        if (!hasPermissions(this, PERMISSIONS) && (userFacade == null )  ) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        } else {

            Intent myIntent = new Intent(this, ReportingService.class);
            startService(myIntent);
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_ALL: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent myIntent = new Intent(this, ReportingService.class);
                    startService(myIntent);
                    finish();

                }
                else
                {
                    finish();
                }
                return;
            }

        }
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
