package com.bilgeadam.edu.vkfmobil.sl.Base;

import android.content.Context;


import com.bilgeadam.edu.vkfmobil.listener.Base.BaseServiceListener;
import com.bilgeadam.edu.vkfmobil.listener.Base.ServiceClientListener;
import com.bilgeadam.edu.vkfmobil.listener.Base.ServiceErrorClientListener;
import com.bilgeadam.edu.vkfmobil.listener.Base.ServicePostClientListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;




public abstract class BasePostServiceSL<TModel, TErrorModel> implements ServicePostClientListener<TModel>, ServiceClientListener<TModel>, ServiceErrorClientListener<TErrorModel> {


    protected Context context;
    private String serviceUri = "";
    private String serviceGetUri = "";
    private BaseServiceListener<TModel, TErrorModel> serviceListener;

    private ServiceErrorClientListener<TErrorModel> serviceErrorClientListener = null;


    //Base Constructor
    public BasePostServiceSL(Context appContext, BaseServiceListener<TModel, TErrorModel> listener) {
        //Base Constructor
        context = appContext;
        //serviceUri = serviceURL;
        serviceListener = listener;
        addListener(serviceListener);

    }

    public void setOnServiceErrorClientListener(ServiceErrorClientListener<TErrorModel> onServiceErrorClientListener) {
        serviceErrorClientListener = onServiceErrorClientListener;
        addErrorListener(serviceErrorClientListener);
    }

    private List<ServiceErrorClientListener<TErrorModel>> errorlisteners = new CopyOnWriteArrayList<ServiceErrorClientListener<TErrorModel>>();

    public synchronized void addErrorListener(ServiceErrorClientListener<TErrorModel> errorlistener) {
        errorlisteners.add(errorlistener);
    }

    public synchronized void removeErrorListener(ServiceErrorClientListener<TErrorModel> errorlistener) {
        errorlisteners.remove(errorlistener);
    }


    private List<BaseServiceListener<TModel, TErrorModel>> listeners = new CopyOnWriteArrayList<BaseServiceListener<TModel, TErrorModel>>();

    public synchronized void addListener(BaseServiceListener<TModel, TErrorModel> listener) {
        listeners.add(listener);
    }

    public synchronized void removeListener(BaseServiceListener<TModel, TErrorModel> listener) {
        listeners.remove(listener);
    }


    public String getServiceUri() {
        return serviceUri;
    }

    public void setServiceUri(String serviceUri) {
        this.serviceUri = serviceUri;
    }


    public String getServiceGETUri() {
        return serviceUri;
    }

    public void setServiceGETUri(int serviceResUriId) {
        this.serviceUri = context.getString(serviceResUriId);
    }


}
