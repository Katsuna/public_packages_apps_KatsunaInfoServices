package com.katsuna.infoservices.http;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.katsuna.infoservices.Preferences.PreferencesProvider;
import com.katsuna.infoservices.facade.RegisterFacade;
import com.katsuna.infoservices.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cmitatakis on 6/7/2017.
 */

public class JSONRequestWithHeaders {

    public static JsonObjectRequest JSONRequestWithRenewToken(int method, final String url, JSONObject requestBody, final JSONRequest.RequestSuccessListener requestSuccessListener, final JSONRequest.RequestErrorListener requestErrorListener) {


        final RegisterFacade userFacade = PreferencesProvider.LoggedUserInfo();


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.Log("Response from: " + url + ": " + response);
                        if (requestSuccessListener != null)
                            requestSuccessListener.onResponse(response);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.Log("Error from: " + url + ": " + error.getLocalizedMessage());
                if (requestErrorListener != null)
                    requestErrorListener.onErrorResponse(error);
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("authorization", "Bearer " + userFacade.getRefreshToken());

                return headers;
            }

        };


        return jsonObjReq;

    }

    public static JsonObjectRequest JSONRequestWithToken(int method, final String url, String requestBody, final JSONRequest.RequestSuccessListener requestSuccessListener, final JSONRequest.RequestErrorListener requestErrorListener) throws JSONException {


        final RegisterFacade userFacade = PreferencesProvider.LoggedUserInfo();


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, new JSONObject(requestBody),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.Log("Response from: " + url + ": " + response);
                        if (requestSuccessListener != null)
                            requestSuccessListener.onResponse(response);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.Log("Error from: " + url + ": " + error.getLocalizedMessage());
                if (requestErrorListener != null)
                    requestErrorListener.onErrorResponse(error);
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("authorization", "Bearer " + userFacade.getToken());

                return headers;
            }

        };


        return jsonObjReq;

    }

}
