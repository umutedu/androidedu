package com.bilgeadam.edu.vkfmobil.sl.Base;

import android.content.ContentValues;
import android.content.Context;

import com.bilgeadam.edu.vkfmobil.json.Base.BaseServiceResponseModelContainerJSON;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by umutboz on 29/09/15.
 */
public abstract class BaseServiceConfigurationSL<TModel,TErrorModel>{


    public abstract BaseServiceConfigurationSL<TModel,TErrorModel> getServiceConfiguration();


    protected Context context ;



    protected BasePostServiceSL<TModel,TErrorModel> basePostServiceSL ;

    protected boolean IsGzipEncode=false;
    protected boolean isBasicHttpBinding=false;
    protected ContentValues httpHeaders ;
    private List<Integer> errorStatusCodes = new ArrayList<>(0);


    protected String ContentType;

    protected BaseServiceResponseModelContainerJSON<TModel,TErrorModel> serviceResponseModelContainerJSON;

    public BaseServiceConfigurationSL(Context mContext, BasePostServiceSL<TModel, TErrorModel> basePostServiceSL){
        context = mContext;
        this.basePostServiceSL = basePostServiceSL;
    }
    //private static BaseServiceConfigurationSL instance ;

//    protected BaseServiceConfigurationSL() {
//
//    }

/*    public static BaseServiceConfigurationSL Instance (){
        if(instance==null)
            instance=new BaseServiceConfigurationSL();

        return instance;
    }*/

    public BaseServiceResponseModelContainerJSON<TModel, TErrorModel> getServiceResponseModelContainerJSON() {
        return serviceResponseModelContainerJSON;
    }

    public void setServiceResponseModelContainerJSON(BaseServiceResponseModelContainerJSON<TModel, TErrorModel> responsecontainerjson) {
        this.serviceResponseModelContainerJSON = responsecontainerjson;
    }

    public boolean isGzipEncode() {
        return IsGzipEncode;
    }

    public void setIsGzipEncode(boolean isGzipEncode) {
        IsGzipEncode = isGzipEncode;
    }

    public boolean isBasicHttpBinding() {
        return isBasicHttpBinding;
    }

    public void setIsBasicHttpBinding(boolean isBasicHttpBinding) {
        this.isBasicHttpBinding = isBasicHttpBinding;
    }

    public ContentValues getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(ContentValues httpHeaders) {
        this.httpHeaders = httpHeaders;
    }
    public String getContentType() {
        return ContentType;
    }

    public void setContentType(String contentType) {
        ContentType = contentType;
    }
    public BasePostServiceSL<TModel, TErrorModel> getBasePostServiceSL() {
        return basePostServiceSL;
    }

    public void  addExtraHeader(String key, String value)
    {
        httpHeaders.put(key, value);
    }

    public List<Integer> getErrorStatusCodes() {

        return errorStatusCodes;
    }

    public void setErrorStatusCodes(List<Integer> checkStatusCodes) {
        this.errorStatusCodes = checkStatusCodes;
    }
    //   httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
 //   httpPost.setHeader("Cache-Control","no-cache");
 //   httpPost.setHeader("Accept-Encoding","gzip, deflate");

/*    public List<NameValuePair> add (String key, String value){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(key, value));

        return params;
    }*/

}
