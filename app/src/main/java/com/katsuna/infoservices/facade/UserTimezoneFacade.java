package com.katsuna.infoservices.facade;

import com.katsuna.infoservices.httpRequests.ResponseKeys;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cmitatakis on 5/15/2017.
 */

public class UserTimezoneFacade extends Facade {

    private long dateTimestamp;

    private String timezoneValue;

    public long getDateTimestamp() {
        return dateTimestamp;
    }

    public void setDateTimestamp(long dateTimestamp) {
        this.dateTimestamp = dateTimestamp;
    }

    public String getTimezoneValue() {
        return timezoneValue;
    }

    public void setTimezoneValue(String timezoneValue) {
        this.timezoneValue = timezoneValue;
    }

    @Override
    public JSONObject Serialize() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(ResponseKeys.RegisteFacade_TimeZone, timezoneValue);
        jsonObject.put(ResponseKeys.RegisteFacade_Timestamp, dateTimestamp);
        return jsonObject;
    }

    @Override
    public Object Deserialize(Object payload) throws JSONException {
        return null;
    }
}
