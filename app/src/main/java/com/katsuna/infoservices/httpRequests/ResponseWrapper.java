package com.katsuna.infoservices.httpRequests;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by christosmitatakis on 3/5/17.
 */

public class ResponseWrapper {
    private boolean mStatusCode;
    private String mPayload;
    private int mHttpCode;
    private  ApiResponseCode mApiCode;

    public ResponseWrapper(Boolean statusCode, String payload) {
        mStatusCode = statusCode;
        mPayload = payload;
    }


    public ResponseWrapper(JSONObject jsonObject) throws JSONException {

        mStatusCode = jsonObject.getBoolean(ResponseKeys.ResponseWrapper_r);
        mPayload = jsonObject.getString(ResponseKeys.ResponseWrapper_d);
        mHttpCode = jsonObject.getInt(ResponseKeys.ResponseWrapper_HTTP_ResponseCode);
        mApiCode = ApiResponseCode.valueOf(jsonObject.getInt(ResponseKeys.ResponseWrapper_API_ResponseCode));



    }


    public int getmHttpCode() {
        return mHttpCode;
    }

    public void setmHttpCode(int mHttpCode) {
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