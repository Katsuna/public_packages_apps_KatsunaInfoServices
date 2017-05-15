package com.katsuna.infoservices.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.katsuna.infoservices.KatsunaInfoServicesApplication;
import com.katsuna.infoservices.facade.RegisterFacade;
import com.katsuna.infoservices.facade.UserFacade;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by christosmitatakis on 3/18/17.
 */

//
public class PreferencesProvider {

    private final static String KATSUNA_PREFS_NAME = "KatsunaPrefs";

    static RegisterFacade userFacade;

    public static synchronized RegisterFacade LoggedUserInfo() {
        if (userFacade == null) {
            String prefString = getPreferences().getString(SharedPreferencesConstants.UserInfo, "");
            if (!prefString.isEmpty()) {
                try {
                    userFacade = new RegisterFacade().Deserialize( new JSONObject(prefString));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return userFacade;
    }

    public static synchronized void SetLoggedUserInfo( RegisterFacade regFacade) {
        try {
            getPreferences().edit().putString(SharedPreferencesConstants.UserInfo, regFacade.PreferencesSerialize().toString()).apply();
            userFacade = regFacade;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static SharedPreferences getPreferences() {
        return KatsunaInfoServicesApplication.getAppContext().getSharedPreferences(KATSUNA_PREFS_NAME, Context.MODE_PRIVATE);
    }

}