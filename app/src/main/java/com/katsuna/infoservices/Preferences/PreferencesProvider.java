package com.katsuna.infoservices.Preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.katsuna.infoservices.facade.RegisterFacade;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by christosmitatakis on 3/18/17.
 */

//
public class PreferencesProvider {

    private final static String KATSUNA_PREFS_NAME = "KatsunaPrefs";

    static RegisterFacade registerFacade;

    public static synchronized RegisterFacade LoggedUserInfo(Activity context) {
        if (registerFacade == null) {
            String prefString = getPreferences(context).getString(SharedPreferencesConstants.UserInfo, "");
            if (!prefString.isEmpty()) {
                try {
                    registerFacade = new RegisterFacade().Deserialize(context, new JSONObject(prefString));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return registerFacade;
    }

    public static synchronized void SetLoggedUserInfo(Activity context, RegisterFacade regFacade) {
        try {
            getPreferences(context).edit().putString(SharedPreferencesConstants.UserInfo, regFacade.PreferencesSerialize(context).toString()).apply();
            registerFacade = regFacade;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static SharedPreferences getPreferences(Activity context) {
        return context.getSharedPreferences(KATSUNA_PREFS_NAME, Context.MODE_PRIVATE);
    }

}
