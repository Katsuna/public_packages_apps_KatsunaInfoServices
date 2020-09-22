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

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.katsuna.infoservices.KatsunaInfoServicesApplication;
import com.katsuna.infoservices.facade.LocationCollectionFacade;
import com.katsuna.infoservices.facade.RegisterFacade;
import com.katsuna.infoservices.facade.UserFacade;
import com.katsuna.infoservices.http.HTTPCookieStore;
import com.katsuna.infoservices.http.HTTPManagerBase;
import com.katsuna.infoservices.http.JSONRequest;
import com.katsuna.infoservices.http.JSONRequestWithHeaders;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by christosmitatakis on 3/5/17.
 */

public class HttpManager extends HTTPManagerBase {

    private static CookieStore mCookieStore;
    private static CookieManager mCookieManager;

    private static synchronized CookieStore getCookieStore() {
        if (mCookieStore == null) {
            Set<String> cookies = new HashSet<>();
            cookies.add("Katsuna_SessionId");
            cookies.add("Katsuna.WebApp.Cookie");
            mCookieStore = new HTTPCookieStore(KatsunaInfoServicesApplication.getAppContext(), cookies);
        }
        return mCookieStore;
    }

    private static synchronized CookieManager getCookieManager() {
        if (mCookieManager == null)
            mCookieManager = new CookieManager(getCookieStore(), CookiePolicy.ACCEPT_ORIGINAL_SERVER);
        return mCookieManager;
    }

    //region Request Enhancing

    protected static void enhanceAndExecuteRequest( Request<?> request) {

        CookieHandler.setDefault(getCookieManager());

        execute(request);
    }
    //endregion


    public static void RegisterCallback(RegisterFacade userFacade, final KatsunaResponseHandler responseHandler) throws JSONException {

        JSONObject params = new JSONObject();

        params =  userFacade.Serialize();

        enhanceAndExecuteRequest( new JSONRequest(
                Request.Method.POST,
                ServerConstants.WebServer + ServerConstants.Register ,
                params,
                new JSONRequest.RequestSuccessListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            responseHandler.onSuccess(new ResponseWrapper(response));
                            System.out.println(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            responseHandler.onFailure();
                        }
                    }
                },
                new JSONRequest.RequestErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseHandler.onFailure();
                    }
                }
        ));
    }

    public static void RenewTokenCallback(final KatsunaResponseHandler responseHandler) throws JSONException {

        JSONObject params = new JSONObject();

        enhanceAndExecuteRequest(JSONRequestWithHeaders.JSONRequestWithRenewToken( Request.Method.POST,
                ServerConstants.WebServer  + ServerConstants.RenewToken  , params,
                new JSONRequest.RequestSuccessListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            responseHandler.onSuccess(new ResponseWrapper(response));
                            System.out.println(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            responseHandler.onFailure();
                        }
                    }
                },
                new JSONRequest.RequestErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseHandler.onFailure();
                    }
                }));


    }

    public static void saveLocations(LocationCollectionFacade locationFacades, final KatsunaResponseHandler responseHandler) throws JSONException {

        String params = new String(locationFacades.Serialize().toString());


        enhanceAndExecuteRequest(JSONRequestWithHeaders.JSONRequestWithToken( Request.Method.POST,
                ServerConstants.WebServer  + ServerConstants.SavePointsOfInterest  , params,
                new JSONRequest.RequestSuccessListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            responseHandler.onSuccess(new ResponseWrapper(response));
                            System.out.println(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            responseHandler.onFailure();
                        }
                    }
                },
                new JSONRequest.RequestErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseHandler.onFailure();
                    }
                }));
    }

}
