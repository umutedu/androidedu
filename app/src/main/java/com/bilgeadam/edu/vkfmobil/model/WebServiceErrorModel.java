package com.bilgeadam.edu.vkfmobil.model;

/**
 * Created by ksmacbook on 30/09/16.
 */

public class WebServiceErrorModel {

    public WebServiceErrorModel()
    {

    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorCode;
    private String errorMessage;

    public WebServiceErrorModel(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
