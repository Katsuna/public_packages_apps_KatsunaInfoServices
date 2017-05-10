package com.katsuna.infoservices;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = new Intent(this, ReportingService.class);
        startService(myIntent);
        finish();
    }

}
