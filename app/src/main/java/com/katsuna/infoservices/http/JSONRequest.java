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
package com.katsuna.infoservices.http;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.katsuna.infoservices.logging.Logger;

import org.json.JSONObject;



public class JSONRequest extends JsonObjectRequest {

//    public JSONRequest(int method, final String url, String requestBody, final RequestSuccessListener requestSuccessListener, final RequestErrorListener requestErrorListener) {
//
//        super(method, url, new JSONObject(requestBody), new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Logger.Log("Response from: " + url + ": " + response);
//                if (requestSuccessListener != null)
//                    requestSuccessListener.onResponse(response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Logger.Log("Error from: " + url + ": " + error.getLocalizedMessage());
//                if (requestErrorListener != null)
//                    requestErrorListener.onErrorResponse(error);
//            }
//        });
//
//
//    }
//
//
//
//    public JSONRequest(final String url, final RequestSuccessListener requestSuccessListener, final RequestErrorListener requestErrorListener) {
//        super(url, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Logger.Log("Response from: " + url + ": " + response);
//                if (requestSuccessListener != null)
//                    requestSuccessListener.onResponse(response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Logger.Log("Error from: " + url + ": " + error.getLocalizedMessage());
//                if (requestErrorListener != null)
//                    requestErrorListener.onErrorResponse(error);
//            }
//        });
//    }
//
//    public JSONRequest(int method, final String url, final RequestSuccessListener requestSuccessListener, final RequestErrorListener requestErrorListener) {
//        super(method, url, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Logger.Log("Response from: " + url + ": " + response);
//                if (requestSuccessListener != null)
//                    requestSuccessListener.onResponse(response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Logger.Log("Error from: " + url + ": " + error.getLocalizedMessage());
//                if (requestErrorListener != null)
//                    requestErrorListener.onErrorResponse(error);
//            }
//        });
//    }

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
