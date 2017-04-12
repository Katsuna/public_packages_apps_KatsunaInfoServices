package com.katsuna.services.httpRequests;

import android.app.Activity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.VolleyError;
import com.katsuna.services.Preferences.PreferencesProvider;

public class TokenRetryPolicy extends DefaultRetryPolicy {


    private final int mMaxNumRetries;
    private Activity activity;
    private static final int socketTimeout = 40000; //30 seconds
    private int mCurrentRetryCount;
    private float mCurrentTimeoutMs;
    private float mBackoffMultiplier;
    private boolean refreshToken = false;


    public TokenRetryPolicy(Activity activity)
    {
        super(socketTimeout, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        mCurrentTimeoutMs = socketTimeout;
        mMaxNumRetries = 3;
        mBackoffMultiplier = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.activity = activity;
    }

    @Override
    public synchronized void retry(VolleyError error) throws VolleyError {


        mCurrentRetryCount++;
        mCurrentTimeoutMs += (mCurrentTimeoutMs * mBackoffMultiplier);
        if (error instanceof AuthFailureError && PreferencesProvider.LoggedUserInfo(activity) != null) {

            try {
                Thread.sleep(5000);
                if(refreshToken)
                    return;
            } catch (InterruptedException e) {
            }

        }

        System.out.println("Current: " + mCurrentRetryCount +"TOTAL: "+  mMaxNumRetries);

        if (!hasAttemptRemaining()) {

//            SessionHandler.LogOff();
//            HTTPManager.getCookieStore().removeAll();
//            PreferencesProvider.Clear(activity);

//            activity.finish();
            throw error;


        }
    }

    @Override
    protected boolean hasAttemptRemaining() {
        return mCurrentRetryCount <= mMaxNumRetries;
    }
}
