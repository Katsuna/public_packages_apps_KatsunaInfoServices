package com.katsuna.infoservices.facade;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by cmitatakis on 6/9/2017.
 */

public class LocationCollectionFacade extends CollectionFacade<LocationFacade> {
    List<LocationFacade> locations;

    public List<LocationFacade> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationFacade> locations) {
        this.locations = locations;
    }

    @Override
    public JSONArray Serialize() {
        JSONArray jsonArray = new JSONArray();

        for(LocationFacade location : locations )
        {
            try {
                jsonArray.put(location.Serialize());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonArray;
    }

    @Override
    public Object Deserialize(Object payload) throws JSONException {
        return null;
    }
}
