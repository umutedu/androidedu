package com.bilgeadam.edu.vkfmobil.json.Base;

import android.content.Context;


import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import com.bilgeadam.edu.vkfmobil.listener.Base.ServiceErrorClientListener;
import com.bilgeadam.edu.vkfmobil.listener.Base.ServicePostClientListener;
import com.bilgeadam.edu.vkfmobil.model.Base.BaseServiceInfoModel;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Umut Boz on 28.09.2015.
 */
public abstract class BaseServiceResponseModelContainerJSON<TModel,TErrorModel> implements JsonDeserializer<TModel> {

    protected boolean hasError;
    protected Context context;

    protected ServicePostClientListener<TModel> servicePostModelServicePostClientListener;
    protected ServiceErrorClientListener<TErrorModel> serviceErrorClientListener;
    protected BaseServiceInfoModel<TModel> responseServiceModel;
    public BaseServiceResponseModelContainerJSON(Context mContext,ServicePostClientListener<TModel> mServicePostModelServicePostClientListener,ServiceErrorClientListener<TErrorModel> mServiceErrorClientListener){
        context = mContext;
        servicePostModelServicePostClientListener = mServicePostModelServicePostClientListener;
        serviceErrorClientListener = mServiceErrorClientListener;
       // servicePostModelServicePostClientListener = mServicePostModelServicePostClientListener;
    }

    public abstract void initServiceResponse(String json,BaseServiceInfoModel<TModel> mResponseServiceModel);
    public abstract TModel getServiceResponseModelFromJSON(String jsonString);


/*
    public TModel getServiceResponseModelFromJSON(String jsonString) {



        try {
            Type type = new TypeToken<TModel>() {
            }.getType();
            Gson gson = new Gson();
            info = gson.fromJson(jsonString, type);
        } catch (Exception ex) {
            info = null;
            //Log.d("BaseServiceErrorContainerJSON JSON PARSER", ex.getMessage());
        }
        *}*/




    public TErrorModel getServiceErrorModelFromJSON(String jsonString) {
        TErrorModel info = null;


        try {
            Type type = new TypeToken<TErrorModel>() {
            }.getType();
            Gson gson = new Gson();
            info = gson.fromJson(jsonString, type);
        } catch (Exception ex) {
            info = null;
            //Log.d("BaseServiceErrorContainerJSON JSON PARSER", ex.getMessage());
        }
        return info;

    }
    public List<TErrorModel> getServiceErrorModelListFromJSON(String jsonString) {
        List<TErrorModel> info = null;


        try {
            Type type = new TypeToken<List<TErrorModel>>() {
            }.getType();
            Gson gson = new Gson();
            info = gson.fromJson(jsonString, type);
        } catch (Exception ex) {
            info = null;
            //Log.d("BaseServiceErrorContainerJSON JSON PARSER", ex.getMessage());
        }
        return info;

    }




}
