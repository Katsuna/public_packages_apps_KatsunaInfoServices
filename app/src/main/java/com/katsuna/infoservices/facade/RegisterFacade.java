package com.katsuna.infoservices.facade;

import com.katsuna.infoservices.httpRequests.ResponseKeys;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;


/**
 * Created by christosmitatakis on 3/5/17.
 */

public class RegisterFacade extends Facade
{

    private Long imei;
    private String msisdn;
    private String userUniqueId;
    private String token;

    public String getUserUniqueId() {
        return userUniqueId;
    }

    public void setUserUniqueId(String userUniqueId) {
        this.userUniqueId = userUniqueId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Long getImei() {
        return imei;
    }

    public void setImei(Long imei) {
        this.imei = imei;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getKatsunaVersion() {
        return katsunaVersion;
    }

    public void setKatsunaVersion(String katsunaVersion) {
        this.katsunaVersion = katsunaVersion;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    private String countryCode;
    private int age;
    private String gender;
    private String timezone;
    private String katsunaVersion;
    private String modelNumber;

    private Long timestamp;

    public RegisterFacade()
    {

    }

    public RegisterFacade (Long imei, String msisdn, String countryCode, int age, String gender, String timezone, Timestamp timestamp, String katsunaVersion, String modelNumber)
    {
        this.imei = imei;
        this.msisdn = msisdn;
        this.countryCode = countryCode;
        this.age = age;
        this.gender = gender;
        this.timezone = timezone;
        this.timestamp = (Long) timestamp.getTime();
        this.katsunaVersion = katsunaVersion;
        this.modelNumber = modelNumber;

    }

    @Override
    public JSONObject Serialize() throws JSONException {


        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ResponseKeys.RegisteFacade_IMEI, imei);
        jsonObject.put(ResponseKeys.RegisteFacade_MSISDN, msisdn);
        jsonObject.put(ResponseKeys.RegisteFacade_CountryCode, countryCode);
        jsonObject.put(ResponseKeys.RegisteFacade_Age, age);
        jsonObject.put(ResponseKeys.RegisteFacade_Gender, gender);
        jsonObject.put(ResponseKeys.RegisteFacade_TimeZone, timezone);
        jsonObject.put(ResponseKeys.RegisteFacade_Timestamp, timestamp);
        jsonObject.put(ResponseKeys.RegisteFacade_Katsuna_Version, katsunaVersion);
        jsonObject.put(ResponseKeys.RegisteFacade_Model_Number, modelNumber);

        return jsonObject;
    }

    @Override
    public RegisterFacade Deserialize(Object payload) throws JSONException {
        JSONObject jsonObject = (JSONObject) payload;

        userUniqueId = jsonObject.getString(ResponseKeys.RegisteFacade_UserUniqueId);
        token = jsonObject.getString(ResponseKeys.RegisteFacade_Token);

        return this;
    }

    public JSONObject PreferencesSerialize() throws JSONException {


        JSONObject jsonObject = new JSONObject();

        jsonObject.put(ResponseKeys.RegisteFacade_UserUniqueId, userUniqueId);
        jsonObject.put(ResponseKeys.RegisteFacade_Token, token);

        return jsonObject;
    }
}
