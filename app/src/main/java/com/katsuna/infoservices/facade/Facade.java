package com.katsuna.infoservices.facade;

import android.app.Activity;

import org.json.JSONException;

/**
 * Created by christosmitatakis on 3/5/17.
 */

public abstract class Facade {

    //region Serialization

    public abstract Object Serialize(Activity context) throws JSONException;

    public abstract Object Deserialize(Activity context, Object payload) throws JSONException;


}