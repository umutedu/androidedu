package com.bilgeadam.edu.vkfmobil.sl;

import android.content.Context;

import com.bilgeadam.edu.vkfmobil.listener.ServiceListener;
import com.bilgeadam.edu.vkfmobil.model.Base.BaseServiceErrorModel;
import com.bilgeadam.edu.vkfmobil.model.Base.BaseServiceInfoModel;
import com.bilgeadam.edu.vkfmobil.model.WebServiceErrorModel;
import com.bilgeadam.edu.vkfmobil.model.WebServiceResponseModel;
import com.bilgeadam.edu.vkfmobil.sl.Base.BaseGetServiceClient;
import com.bilgeadam.edu.vkfmobil.sl.Base.BasePostServiceSL;
import com.bilgeadam.edu.vkfmobil.sl.Base.BaseServicePostClientSL;


/**
 * Created by 02483564 on 6.10.2016.
 */
public class WebServiceSL extends BasePostServiceSL<String, WebServiceErrorModel> {

    ServiceListener<String, WebServiceErrorModel> biletixServiceListener;

    static final String COOKIES_HEADER = "Set-Cookie";
    ServiceUrlSL serviceUrlSL;

    public WebServiceSL(Context appContext, ServiceListener listener) {
        super(appContext, listener);
        biletixServiceListener = listener;
        serviceUrlSL = new ServiceUrlSL();
        setOnServiceErrorClientListener(this);
    }


    BaseGetServiceClient<String, WebServiceErrorModel> getClientSL(String queryString,
                                                                                    String methodName) {
        WebServiceConfigurationSL serviceConfiguration = new WebServiceConfigurationSL(context, this);
        BaseGetServiceClient<String, WebServiceErrorModel> get = new BaseGetServiceClient<String, WebServiceErrorModel>(context, "WebServiceSL");
        setServiceUri(serviceUrlSL.getURL(methodName));
        get.addServiceClientListener(this);
        get.addErrorErrorServiceClientListener(this);
        get.setIsQueryString(true);
        get.setQueryString(queryString);
        get.setMethodName(methodName);
        get.setBaseServiceConfiguration(serviceConfiguration.getServiceConfiguration());
        return get;
    }

    BaseServicePostClientSL<String, WebServiceErrorModel> postClientSL(String requestModel,
                                                                                        String methodName) {
        WebServiceConfigurationSL serviceConfiguration = new WebServiceConfigurationSL(context, this);
        BaseServicePostClientSL<String, WebServiceErrorModel> postClient = new BaseServicePostClientSL
                <String, WebServiceErrorModel>(context, "BiletixToolSL", "");
        setServiceUri(serviceUrlSL.getURL(methodName));
        postClient.addServiceClientListener(this);
        postClient.addErrorErrorServiceClientListener(this);
        postClient.setRequestBody(requestModel);
        postClient.setMethodName(methodName);
        postClient.setBaseServiceConfiguration(serviceConfiguration.getServiceConfiguration());
        return postClient;
    }


    //================================================================================
    // General
    //================================================================================

    public void getAllList() {

        String queryString = "?language=tr-TR&recursiveCategories=true&serviceIds=TEST_OFFER&serviceIds=TAP&serviceIds=SVODSINEMAHEMEN&serviceIds=SVODDIZI&serviceIds=SVODGENEL&serviceIds=TVOD&serviceIds=SVODBELGESEL&serviceIds=SVODEGITIM&serviceIds=SVOD_TEST&serviceIds=SVODSPOR&serviceIds=SVODFILM&ebcs=MOBIL&categoryId=%2FSecizle%2FFilm%2FTumfilmler";
        BaseGetServiceClient<String, WebServiceErrorModel> get = getClientSL(queryString, ConstantsSL.offerings);
        get.execute(getServiceUri());
    }





    //================================================================================
    // Customer
    //================================================================================

/*
    public void login(LoginRequestModel model, String queryString) {
        ModelParserJSON parserJSON = new ModelParserJSON();
        String requestModel = parserJSON.getRequestModelJsonString(model);

        //set login model on shraredpreferences
        SharedPreferencesUtility sharedPreferencesUtility = new SharedPreferencesUtility(context);
        sharedPreferencesUtility.setLoginModel(requestModel);

        BaseServicePostClientSL<WebServiceResponseModel, WebServiceErrorModel> postClient = postClientSL(requestModel, ConstantsSL.LoginMethodName);
        postClient.execute(getServiceUri(), queryString);
    }
    */






    //================================================================================
    // Transaction
    //================================================================================


/*
    public void transactionLogin(LoginRequestModel model, String queryString) {

        ModelParserJSON parserJSON = new ModelParserJSON();
        String requestModel = parserJSON.getRequestModelJsonString(model);
        //set login model on shraredpreferences
        SharedPreferencesUtility sharedPreferencesUtility = new SharedPreferencesUtility(context);
        sharedPreferencesUtility.setLoginModel(requestModel);


        BaseServicePostClientSL<WebServiceResponseModel, WebServiceErrorModel> postClient = postClientSL(requestModel, ConstantsSL.TransactionLoginMethodName);
        postClient.setRequestCookie(new SharedPreferencesUtility(context).getTransactionCookie());
        postClient.isReceiveCookie(true);
        postClient.setResponseCookieTag(COOKIES_HEADER);
        postClient.execute(getServiceUri(), queryString);
    }

    public void transactionSetDeliveryOption(SetDeliveryOptionRequestModel model, String queryString) {
        ModelParserJSON parserJSON = new ModelParserJSON();
        String requestModel = parserJSON.getRequestModelJsonString(model);
        BaseServicePostClientSL<WebServiceResponseModel, WebServiceErrorModel> postClient = postClientSL(requestModel, ConstantsSL.TransactionSetDeliveryOptionMethodName);
        postClient.setRequestCookie(new SharedPreferencesUtility(context).getTransactionCookie());
        postClient.execute(getServiceUri(), queryString);
    }
    */



    //================================================================================
    // Resend
    //================================================================================



    //================================================================================
    // Device
    //================================================================================





    @Override
    public void OnGETReceive(BaseServiceInfoModel<String> responseEvent) {
        biletixServiceListener.OnComplete(responseEvent);
    }

    @Override
    public void OnError(BaseServiceErrorModel<WebServiceErrorModel> responseBaseServiceErrorModel) {
        biletixServiceListener.OnError(responseBaseServiceErrorModel);
    }

    @Override
    public void OnPOSTCommit(BaseServiceInfoModel<String> responseEvent) {
        //biletixServiceListener.didComplete(getModelFromJSON(responseString,clazz));
        biletixServiceListener.OnComplete(responseEvent);
    }
}
