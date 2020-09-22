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

import com.katsuna.infoservices.httpRequests.ResponseKeys;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cmitatakis on 6/9/2017.
 */

public class LocationFacade extends Facade {

    private double latitude, longitude, totalTime, speedAverage;
    int timesVisited;
    String description;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public double getSpeedAverage() {
        return speedAverage;
    }

    public void setSpeedAverage(double speedAverage) {
        this.speedAverage = speedAverage;
    }

    public int getTimesVisited() {
        return timesVisited;
    }

    public void setTimesVisited(int timesVisited) {
        this.timesVisited = timesVisited;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public JSONObject Serialize() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(ResponseKeys.LocationFacade_Latitude, latitude);
        jsonObject.put(ResponseKeys.LocationFacade_Longitude, longitude);
        if(description != null)
            jsonObject.put(ResponseKeys.LocationFacade_Description, description);
        jsonObject.put(ResponseKeys.LocationFacade_SpeedAverage, speedAverage);
        if(timesVisited != 0)
        jsonObject.put(ResponseKeys.LocationFacade_TimesVisited, timesVisited);
        if(totalTime != 0)
            jsonObject.put(ResponseKeys.LocationFacade_TotalTime, totalTime);


        return jsonObject;
    }

    @Override
    public Object Deserialize(Object payload) throws JSONException {
        return null;
    }
}
