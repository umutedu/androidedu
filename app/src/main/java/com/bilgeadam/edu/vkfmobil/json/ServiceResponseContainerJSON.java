package com.bilgeadam.edu.vkfmobil.json;

import android.app.Service;
import android.content.Context;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.bilgeadam.edu.vkfmobil.R;
import com.bilgeadam.edu.vkfmobil.json.Base.BaseServiceResponseModelContainerJSON;
import com.bilgeadam.edu.vkfmobil.listener.Base.ServiceErrorClientListener;
import com.bilgeadam.edu.vkfmobil.listener.Base.ServicePostClientListener;
import com.bilgeadam.edu.vkfmobil.model.Base.BaseServiceErrorModel;
import com.bilgeadam.edu.vkfmobil.model.Base.BaseServiceInfoModel;
import com.bilgeadam.edu.vkfmobil.model.WebServiceErrorModel;
import com.bilgeadam.edu.vkfmobil.model.WebServiceResponseModel;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by ksmacbook on 30/09/16.
 */

public class ServiceResponseContainerJSON extends BaseServiceResponseModelContainerJSON<String,WebServiceErrorModel> {

    String getErrors =null;

    public ServiceResponseContainerJSON(Context context, ServicePostClientListener<String> postClientListener, ServiceErrorClientListener<WebServiceErrorModel> errorClientListener)
    {
        super(context,postClientListener,errorClientListener);
    }

    @Override
    public void initServiceResponse(String json, BaseServiceInfoModel<String> responseServiceModel) {
        String serviceResponseModel = getServiceResponseModelFromJSON(json);
        //WebServiceResponseModel serviceResponseModel = getServiceResponseModelFromJSON(json);

        if(responseServiceModel.getIsArrived()) {
            try {      //Servis 200 dönmüş

                BaseServiceErrorModel<WebServiceErrorModel> baseServiceErrorModel = new BaseServiceErrorModel<WebServiceErrorModel>();
                if (serviceResponseModel == null) {


                    WebServiceErrorModel errorModel = new WebServiceErrorModel();
                    errorModel.setErrorMessage(context.getString(R.string.BrokenData));
                    errorModel.setErrorCode( "" +responseServiceModel.getResponseStatusCode());


                    baseServiceErrorModel.setErrorModel(errorModel);
                    //TODO: hata fırlat
                    serviceErrorClientListener.OnError(baseServiceErrorModel);
                    hasError = true;
                } else {
                    if (serviceResponseModel==null) {
                        //TODO: servisin kendi kendi hata senaryosu


                        WebServiceErrorModel errorModel = new WebServiceErrorModel();
                        String errorArray = getErrors;
                        if(errorArray!=null)
                        {

                            List<WebServiceErrorModel> list = getErrorModelFromJson(errorArray);
                            baseServiceErrorModel.setErrorModel(list.get(0));
                            baseServiceErrorModel.setErrorModelList(list);
                        }
                        else
                        {
                             errorModel.setErrorMessage("Data not Found");
                            errorModel.setErrorCode("999");
                            baseServiceErrorModel.setErrorModel(errorModel);
                        }




                        serviceErrorClientListener.OnError(baseServiceErrorModel);

                        hasError = true;

                    }
                    else{
                        responseServiceModel.setServiceResponseModel(serviceResponseModel);
                        servicePostModelServicePostClientListener.OnPOSTCommit(responseServiceModel);
                    }
                }
            } catch (Exception ex) {

            }
        }
        else
        {
            //Servis erişimi yapılamadı
            hasError = true;
            BaseServiceErrorModel<WebServiceErrorModel> baseServiceErrorModel = new BaseServiceErrorModel<WebServiceErrorModel>();

            WebServiceErrorModel errorModel = new WebServiceErrorModel();
            //errorModel.setResultMessage(responseServiceModel.getErrorMessage());
           // errorModel.setResultCode(responseServiceModel.getErrorMessage());

            baseServiceErrorModel.setErrorModel(errorModel);
            //TODO: hata fırlat
            serviceErrorClientListener.OnError(baseServiceErrorModel);
        }


    }

    @Override
    public String getServiceResponseModelFromJSON(String jsonString) {
       /*
        WebServiceResponseModel info = null;
        try {
            Type type = new TypeToken<WebServiceResponseModel>() {
            }.getType();
            Gson gson = new GsonBuilder().registerTypeAdapter(type,this).create();
            info = gson.fromJson(jsonString, type);
        } catch (Exception ex) {
            info = null;
            //Log.d("BaseServiceErrorContainerJSON JSON PARSER", ex.getMessage());
        }
        */
        return jsonString;
       // return info;
    }
    List<WebServiceErrorModel> getErrorModelFromJson(String json)
    {
        List<WebServiceErrorModel> errorModels = null;

        try
        {
            Type type = new TypeToken<List<WebServiceErrorModel>>() { }.getType();
            Gson gson = new Gson();
            errorModels = gson.fromJson(json, type);
        }
        catch (Exception ex)
        {
            Log.d("getErrorModelFromJson", ex.getMessage());
        }

        return errorModels;
    }


    @Override
    public String deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        WebServiceResponseModel model = new WebServiceResponseModel();
        try
        {

            if (jsonElement == null) {
                return null;
            }

            if(jsonElement.isJsonArray())
            {
                model.setSuccess(true);
                //model.setJsonArray(jsonElement.getAsJsonArray());
                //model.setResultData(jsonElement.toString());
            }
            else
            {
                if(jsonElement.getAsJsonObject().get("errorCode")!= null && !jsonElement.getAsJsonObject().get("errorCode").isJsonNull()&&
                        !jsonElement.getAsJsonObject().get("errorCode").toString().equalsIgnoreCase("null")){
                    model.setErrors(jsonElement.getAsJsonObject().get("errorCode").toString());
                    getErrors=model.getErrors();
                }else
                {


                    if(jsonElement.toString().equals("{}"))
                    {
                        model.setSuccess(true);
                        model.setResultData("");
                    }else
                    {   model.setSuccess(true);
                        model.setJsonObject(jsonElement.getAsJsonObject());
                        model.setResultData(jsonElement.getAsJsonObject().toString());
                    }

                }
            }


            return jsonElement.toString();
        }
        catch (Exception ex)
        {
             Log.d("getResponseModel deserialize",ex.getMessage());
        }

        return null;
    }
}