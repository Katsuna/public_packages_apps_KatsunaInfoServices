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
package com.katsuna.infoservices.managers;

import com.katsuna.infoservices.facade.LocationCollectionFacade;
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

    public static void renewToken(final RenewTokenOperationCompletedListener listener) {
        try {
            HttpManager.RenewTokenCallback(new KatsunaResponseHandler() {
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


    public static void savePointsOfInterest(LocationCollectionFacade locationFacades, final SavePointsOfInterestOperationCompletedListener listener) {
        try {
            HttpManager.saveLocations(locationFacades, new KatsunaResponseHandler() {
                @Override
                public void onSuccess(ResponseWrapper response) {
                    if (response.getStatusCode() == true) {
                        try {
                            listener.OperationCompleted(OperationCompletedStatus.Success);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.OperationCompleted(OperationCompletedStatus.Error);
                        }
                    }
                    else {
                        listener.OperationCompleted(OperationCompletedStatus.ValidationError);
                    }
                }


                @Override
                public void onFailure() {
                    listener.OperationCompleted(OperationCompletedStatus.Error);
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

    public interface SavePointsOfInterestOperationCompletedListener {
        void OperationCompleted(OperationCompletedStatus status);
    }

    public interface RenewTokenOperationCompletedListener {
        void OperationCompleted(OperationCompletedStatus status, RegisterFacade organizationalUnitFacade);
    }

}
