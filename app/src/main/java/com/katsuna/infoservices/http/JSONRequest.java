package com.katsuna.infoservices.http;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.katsuna.infoservices.logging.Logger;

import org.json.JSONObject;



public class JSONRequest extends JsonObjectRequest {

    public JSONRequest(int method, final String url, String requestBody, final RequestSuccessListener requestSuccessListener, final RequestErrorListener requestErrorListener) {

        super(method, url, requestBody, new Response.Listener<JSONObject>() {
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
        });


    }



    public JSONRequest(final String url, final RequestSuccessListener requestSuccessListener, final RequestErrorListener requestErrorListener) {
        super(url, new Response.Listener<JSONObject>() {
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
        });
    }

    public JSONRequest(int method, final String url, final RequestSuccessListener requestSuccessListener, final RequestErrorListener requestErrorListener) {
        super(method, url, new Response.Listener<JSONObject>() {
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
        });
    }

    public JSONRequest(int method, final String url, JSONObject jsonRequest, final RequestSuccessListener requestSuccessListener, final RequestErrorListener requestErrorListener) {
        super(method, url, jsonRequest, new Response.Listener<JSONObject>() {
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
        });
    }

    public JSONRequest(final String url, JSONObject jsonRequest, final RequestSuccessListener requestSuccessListener, final RequestErrorListener requestErrorListener) {
        super(url, jsonRequest, new Response.Listener<JSONObject>() {
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
        });
    }

    /**
     * Callback interface for delivering parsed responses.
     */
    public interface RequestSuccessListener {
        /**
         * Called when a response is received.
         */
        void onResponse(JSONObject response);
    }

    /**
     * Callback interface for delivering error responses.
     */
    public interface RequestErrorListener {
        /**
         * Callback method that an error has been occurred with the
         * provided error code and optional user-readable message.
         */
        void onErrorResponse(VolleyError error);
    }
}
