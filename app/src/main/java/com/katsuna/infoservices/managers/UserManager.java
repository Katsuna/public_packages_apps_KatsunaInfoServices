package com.katsuna.infoservices.managers;

import android.app.Activity;

import com.katsuna.infoservices.facade.RegisterFacade;
import com.katsuna.infoservices.httpRequests.HttpManager;
import com.katsuna.infoservices.httpRequests.KatsunaResponseHandler;
import com.katsuna.infoservices.httpRequests.ResponseWrapper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by christosmitatakis on 3/5/17.
 */

public class UserManager {


    public static void register(final Activity context, RegisterFacade registerFacade, final RegisterOperationCompletedListener listener) {
        try {
            HttpManager.RegisterCallback(context, registerFacade, new KatsunaResponseHandler() {
                @Override
                public void onSuccess(ResponseWrapper response) {
                    if (response.getStatusCode() == true) {
                        try {
                            listener.OperationCompleted(OperationCompletedStatus.Success, new RegisterFacade().Deserialize(context, new JSONObject(response.getPayload())));
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.OperationCompleted(OperationCompletedStatus.Error, null);
                        }
                    }
                    else {
                        listener.OperationCompleted(OperationCompletedStatus.ValidationError, null);
                    }
                }


                @Override
                public void onFailure() {
                    listener.OperationCompleted(OperationCompletedStatus.Error, null);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void renewToken(final Activity context, RegisterFacade registerFacade, final RegisterOperationCompletedListener listener) {
        try {
            HttpManager.RenewTokenCallback(context, registerFacade, new KatsunaResponseHandler() {
                @Override
                public void onSuccess(ResponseWrapper response) {
                    if (response.getStatusCode() == true) {
                        try {
                            listener.OperationCompleted(OperationCompletedStatus.Success, new RegisterFacade().Deserialize(context, new JSONObject(response.getPayload())));
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.OperationCompleted(OperationCompletedStatus.Error, null);
                        }
                    }
                    else {
                        listener.OperationCompleted(OperationCompletedStatus.ValidationError, null);
                    }
                }


                @Override
                public void onFailure() {
                    listener.OperationCompleted(OperationCompletedStatus.Error, null);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public enum OperationCompletedStatus {Success, Error, ValidationError}


    public interface RegisterOperationCompletedListener {
        void OperationCompleted(OperationCompletedStatus status, RegisterFacade organizationalUnitFacade);
    }

}
