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

/**
 * Created by christosmitatakis on 3/5/17.
 */

public class ResponseKeys {

    // ResponseWrapper
    public static final String ResponseWrapper_r = "isSuccessfull";
    public static final String ResponseWrapper_HTTP_ResponseCode = "httpResponseCode";
    public static final String ResponseWrapper_API_ResponseCode = "apiResponseCode";
    public static final String ResponseWrapper_d = "body";

    // UserFacade

    public static final String RegisteFacade_IMEI = "imei";
    public static final String RegisteFacade_MSISDN = "msisdn";
    public static final String RegisteFacade_CountryCode = "countryCode";
    public static final String RegisteFacade_Age = "age";
    public static final String RegisteFacade_Gender = "gender";
    public static final String RegisteFacade_Token = "jwtToken";
    public static final String RegisteFacade_Refresh_Token = "refreshToken";
    public static final String RegisteFacade_Katsuna_Version = "katsunaVersion";
    public static final String RegisteFacade_Model_Number = "modelNumber";

    // UserTimezone Facade

    public static final String RegisteFacade_Timestamp= "dateTimestamp";
    public static final String RegisteFacade_TimeZone = "timezoneValue";

    // Register Facade

    public static final String RegisteFacade_User= "user";
    public static final String RegisteFacade_UserTimezone = "userTimezone";

    // Renew Token

    public static final String RenewToken_User_Id= "id";
    public static final String RenewToken_User_Token= "token";

    // Location Facade

    public static final String LocationFacade_Latitude= "latitude";
    public static final String LocationFacade_Longitude= "longitude";
    public static final String LocationFacade_TimesVisited= "timesVisited";
    public static final String LocationFacade_TotalTime= "totalTime";
    public static final String LocationFacade_Description= "description";
    public static final String LocationFacade_SpeedAverage= "speedAverage";






}
