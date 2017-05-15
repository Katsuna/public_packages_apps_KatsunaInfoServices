package com.katsuna.infoservices.facade;

import com.katsuna.infoservices.httpRequests.ResponseKeys;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cmitatakis on 5/15/2017.
 */

public class RegisterFacade extends Facade {

    private String userUniqueId;
    private String token;

    private UserFacade user;
    private UserTimezoneFacade userTimezoneFacade;

    public RegisterFacade(UserFacade user, UserTimezoneFacade userTimezoneFacade)
    {
        this.user = user;
        this.userTimezoneFacade = userTimezoneFacade;
    }

    public RegisterFacade() {

    }

    public UserFacade getUser() {
        return user;
    }

    public void setUser(UserFacade user) {
        this.user = user;
    }

    public UserTimezoneFacade getUserTimezoneFacade() {
        return userTimezoneFacade;
    }

    public void setUserTimezoneFacade(UserTimezoneFacade userTimezoneFacade) {
        this.userTimezoneFacade = userTimezoneFacade;
    }

    public String getUserUniqueId() {
        return userUniqueId;
    }

    public void setUserUniqueId(String userUniqueId) {
        this.userUniqueId = userUniqueId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public JSONObject Serialize() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(ResponseKeys.RegisteFacade_User, user.Serialize());
        jsonObject.put(ResponseKeys.RegisteFacade_UserTimezone, userTimezoneFacade.Serialize());

        return jsonObject;
    }

    @Override
    public RegisterFacade Deserialize(Object payload) throws JSONException {
        JSONObject jsonObject = (JSONObject) payload;

        userUniqueId = jsonObject.getString(ResponseKeys.RegisteFacade_UserUniqueId);
        token = jsonObject.getString(ResponseKeys.RegisteFacade_Token);

        return this;
    }

    public JSONObject PreferencesSerialize() throws JSONException {


        JSONObject jsonObject = new JSONObject();

        jsonObject.put(ResponseKeys.RegisteFacade_UserUniqueId, userUniqueId);
        jsonObject.put(ResponseKeys.RegisteFacade_Token, token);

        return jsonObject;
    }

}
