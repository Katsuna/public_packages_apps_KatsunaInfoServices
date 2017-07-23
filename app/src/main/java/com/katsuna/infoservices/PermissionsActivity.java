package com.katsuna.infoservices;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.katsuna.commons.utils.Log;
import com.katsuna.infoservices.Preferences.PreferencesProvider;
import com.katsuna.infoservices.facade.RegisterFacade;

public class PermissionsActivity extends Activity {

    private static final int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.READ_PHONE_STATE};
    private static final String TAG = "PermissionsActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();
    }

    private void checkPermissions() {
        final RegisterFacade userFacade = PreferencesProvider.LoggedUserInfo();

        if (!hasPermissions(this, PERMISSIONS) && (userFacade == null )) {
            Log.d(TAG, "missing permissions " );
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        } else {
            Log.d(TAG, "permissions granted!" );
            Intent activityIntent = new Intent(getApplicationContext(), ReportingService.class);
            getApplicationContext().startService(activityIntent);
            closeActivity(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        boolean permissionsGranted = false;
        switch (requestCode) {
            case PERMISSION_ALL: {
                Log.d(TAG, "onRequestPermissionsResult permissions accepted: " + grantResults.length);
                if(hasPermissions(this, PERMISSIONS)) {
                    permissionsGranted = true;
                    Intent activityIntent = new Intent(getApplicationContext(), ReportingService.class);
                    getApplicationContext().startService(activityIntent);
                }

                break;
            }
        }
        Log.e(TAG, "onRequestPermissionsResult finishing");
        closeActivity(permissionsGranted);
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

    private void closeActivity(boolean permissionsGranted) {
        Log.d(TAG, "closing Activity: " + permissionsGranted);
        Intent intent = new Intent();
        intent.putExtra("permissionsGranted", permissionsGranted);
        setResult(RESULT_OK, intent);
        finish();
    }

}
