package com.bilgeadam.edu.vkfmobil.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Created by ksmacbook on 30/09/16.
 */

public class WebServiceResponseModel {
    private String errors;
    private String resultData;
    private  boolean isSuccess;


    private JsonObject jsonObject;
    private JsonArray jsonArray;

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public JsonArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JsonArray jsonArray) {
        this.jsonArray = jsonArray;
    }
}
