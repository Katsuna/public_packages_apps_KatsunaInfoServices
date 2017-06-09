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
