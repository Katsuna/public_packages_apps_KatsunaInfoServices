package com.katsuna.infoservices.facade;

import android.app.Activity;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by cmitatakis on 6/9/2017.
 */

public abstract class CollectionFacade<E> extends ArrayList<E> {

    public abstract Object Serialize();

    public abstract Object Deserialize(Object payload) throws JSONException;

}
