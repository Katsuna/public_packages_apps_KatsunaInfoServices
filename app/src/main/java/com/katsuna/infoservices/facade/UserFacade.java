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
package com.katsuna.infoservices.facade;

import com.katsuna.infoservices.httpRequests.ResponseKeys;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;


/**
 * Created by christosmitatakis on 3/5/17.
 */

public class UserFacade extends Facade
{

    private Long imei;
    private String msisdn;

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
    private String katsunaVersion;
    private String modelNumber;

    public UserFacade()
    {

    }

    public UserFacade(Long imei, String msisdn, String countryCode, int age, String gender, String katsunaVersion, String modelNumber)
    {
        this.imei = imei;
        this.msisdn = msisdn;
        this.countryCode = countryCode;
        this.age = age;
        this.gender = gender;
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
        jsonObject.put(ResponseKeys.RegisteFacade_Katsuna_Version, katsunaVersion);
        jsonObject.put(ResponseKeys.RegisteFacade_Model_Number, modelNumber);

        return jsonObject;
    }

    @Override
    public UserFacade Deserialize(Object payload) throws JSONException {
        return null;
    }

}
