package com.katsuna.infoservices.httpRequests;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.katsuna.infoservices.KatsunaInfoServicesApplication;
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

}
