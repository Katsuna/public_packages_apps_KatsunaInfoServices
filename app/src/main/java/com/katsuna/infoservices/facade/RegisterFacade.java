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
 * Created by cmitatakis on 5/15/2017.
 */

public class RegisterFacade extends Facade {

    private String refreshToken;
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

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

        refreshToken = jsonObject.getString(ResponseKeys.RegisteFacade_Refresh_Token);
        token = jsonObject.getString(ResponseKeys.RegisteFacade_Token);

        return this;
    }

    public JSONObject PreferencesSerialize() throws JSONException {


        JSONObject jsonObject = new JSONObject();

        jsonObject.put(ResponseKeys.RegisteFacade_Refresh_Token, refreshToken);
        jsonObject.put(ResponseKeys.RegisteFacade_Token, token);

        return jsonObject;
    }

}
