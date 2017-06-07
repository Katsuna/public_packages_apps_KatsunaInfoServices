package com.katsuna.infoservices.httpRequests;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.katsuna.infoservices.Preferences.PreferencesProvider;
import com.katsuna.infoservices.facade.RegisterFacade;
import com.katsuna.infoservices.facade.UserFacade;
import com.katsuna.infoservices.managers.UserManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TokenRetryPolicy extends DefaultRetryPolicy {


    private final int mMaxNumRetries;
    private static final int socketTimeout = 40000; //30 seconds
    private int mCurrentRetryCount;
    private float mCurrentTimeoutMs;
    private float mBackoffMultiplier;
    private boolean refreshToken = false;


    public TokenRetryPolicy()
    {
        super(socketTimeout, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        mCurrentTimeoutMs = socketTimeout;
        mMaxNumRetries = 3;
        mBackoffMultiplier = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    }

    @Override
    public synchronized void retry(VolleyError error) throws VolleyError {


        mCurrentRetryCount++;
        mCurrentTimeoutMs += (mCurrentTimeoutMs * mBackoffMultiplier);
        RegisterFacade userFacade = PreferencesProvider.LoggedUserInfo();
        if (error instanceof AuthFailureError && userFacade != null) {

            UserManager.renewToken( new UserManager.RenewTokenOperationCompletedListener() {
                @Override
                public void OperationCompleted(UserManager.OperationCompletedStatus status, RegisterFacade userFacade) {

                    switch (status) {
                        case Success:
                            PreferencesProvider.SetLoggedUserInfo(userFacade);
                            refreshToken = true;
                            break;
                        case ValidationError:
                            break;
                        case Error:
                            break;
                    }

                }
            });


            System.out.println("Current: " + mCurrentRetryCount + "TOTAL: " + mMaxNumRetries);

            if (!hasAttemptRemaining()) {

//            SessionHandler.LogOff();
//            HTTPManager.getCookieStore().removeAll();
//            PreferencesProvider.Clear(activity);

//            activity.finish();
                throw error;


            }
        }
    }

    @Override
    protected boolean hasAttemptRemaining() {
        return mCurrentRetryCount <= mMaxNumRetries;
    }
}
