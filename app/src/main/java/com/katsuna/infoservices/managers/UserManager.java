package com.katsuna.infoservices.managers;

import com.katsuna.infoservices.facade.RegisterFacade;
import com.katsuna.infoservices.facade.UserFacade;
import com.katsuna.infoservices.httpRequests.HttpManager;
import com.katsuna.infoservices.httpRequests.KatsunaResponseHandler;
import com.katsuna.infoservices.httpRequests.ResponseWrapper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by christosmitatakis on 3/5/17.
 */

public class UserManager {


    public static void register(RegisterFacade userFacade, final RegisterOperationCompletedListener listener) {
        try {
            HttpManager.RegisterCallback(userFacade, new KatsunaResponseHandler() {
                @Override
                public void onSuccess(ResponseWrapper response) {
                    if (response.getStatusCode() == true) {
                        try {
                            listener.OperationCompleted(OperationCompletedStatus.Success, new RegisterFacade().Deserialize( new JSONObject(response.getPayload())));
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

    public static void renewToken(RegisterFacade userFacade, final RegisterOperationCompletedListener listener) {
        try {
            HttpManager.RenewTokenCallback(userFacade, new KatsunaResponseHandler() {
                @Override
                public void onSuccess(ResponseWrapper response) {
                    if (response.getStatusCode() == true) {
                        try {
                            listener.OperationCompleted(OperationCompletedStatus.Success, new RegisterFacade().Deserialize( new JSONObject(response.getPayload())));
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
