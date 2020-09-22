/**
* Copyright (C) 2020 Manos Saratsis
*
* This file is part of Katsuna.
*
* Katsuna is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Katsuna is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Katsuna.  If not, see <https://www.gnu.org/licenses/>.
*/
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
