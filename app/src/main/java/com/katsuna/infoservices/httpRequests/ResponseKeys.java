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
    public static final String RegisteFacade_UserUniqueId = "userUniqueId";
    public static final String RegisteFacade_Token = "token";
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



}
