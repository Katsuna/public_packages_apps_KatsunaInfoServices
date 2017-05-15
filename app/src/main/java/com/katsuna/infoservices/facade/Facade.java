package com.katsuna.infoservices.facade;

import org.json.JSONException;

/**
 * Created by christosmitatakis on 3/5/17.
 */

public abstract class Facade {

    //region Serialization

    public abstract Object Serialize() throws JSONException;

    public abstract Object Deserialize(Object payload) throws JSONException;


}