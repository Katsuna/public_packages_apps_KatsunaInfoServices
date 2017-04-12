package com.katsuna.services.httpRequests;

/**
 * Created by christosmitatakis on 3/5/17.
 */

public abstract class KatsunaResponseHandler {

    public abstract void onSuccess(ResponseWrapper response);

    public abstract void onFailure();

}
