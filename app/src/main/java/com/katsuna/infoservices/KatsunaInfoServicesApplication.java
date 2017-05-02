package com.katsuna.infoservices;

import android.app.Application;
import android.content.Context;

/**
 * Created by cmitatakis on 4/24/2017.
 */

public class KatsunaInfoServicesApplication extends Application {

private static Context mContext;

public static Context getAppContext() {
        return mContext;
        }

public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        }
        }
