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
package com.katsuna.infoservices.httpRequests;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by christosmitatakis on 3/5/17.
 */

public class ResponseWrapper {
    private boolean mStatusCode;
    private String mPayload;
    private String mHttpCode;
    private  ApiResponseCode mApiCode;

    public ResponseWrapper(Boolean statusCode, String payload) {
        mStatusCode = statusCode;
        mPayload = payload;
    }


    public ResponseWrapper(JSONObject jsonObject) throws JSONException {

        mStatusCode = jsonObject.getBoolean(ResponseKeys.ResponseWrapper_r);
        mPayload = jsonObject.getString(ResponseKeys.ResponseWrapper_d);
        mHttpCode = jsonObject.getString(ResponseKeys.ResponseWrapper_HTTP_ResponseCode);
        mApiCode = ApiResponseCode.valueOf(jsonObject.getInt(ResponseKeys.ResponseWrapper_API_ResponseCode));



    }


    public String getmHttpCode() {
        return mHttpCode;
    }

    public void setmHttpCode(String mHttpCode) {
        this.mHttpCode = mHttpCode;
    }

    public ApiResponseCode getmApiCode() {
        return mApiCode;
    }

    public void setmApiCode(ApiResponseCode mApiCode) {
        this.mApiCode = mApiCode;
    }

    public boolean getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(boolean statusCode) {
        mStatusCode = statusCode;
    }

    public String getPayload() {
        return mPayload;
    }

    public void setPayload(String payload) {
        mPayload = payload;
    }


}