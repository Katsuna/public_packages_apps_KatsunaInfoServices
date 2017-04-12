package com.katsuna.services.http;

import org.json.JSONObject;


public abstract class HTTPRequestHandler {

    public abstract void onSuccess(JSONObject response);

    public abstract void onFailure();
}
