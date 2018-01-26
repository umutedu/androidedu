package com.bilgeadam.edu.vkfmobil.sl;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;


import com.bilgeadam.edu.vkfmobil.json.ServiceResponseContainerJSON;
import com.bilgeadam.edu.vkfmobil.model.WebServiceErrorModel;
import com.bilgeadam.edu.vkfmobil.model.WebServiceResponseModel;
import com.bilgeadam.edu.vkfmobil.sl.Base.BasePostServiceSL;
import com.bilgeadam.edu.vkfmobil.sl.Base.BaseServiceConfigurationSL;

import java.util.Arrays;
import java.util.UUID;


/**
 * Created by ksmacbook on 30/09/16.
 */

public class WebServiceConfigurationSL extends BaseServiceConfigurationSL <String,WebServiceErrorModel> {


    static final String COOKIES_HEADER = "Set-Cookie";
    public WebServiceConfigurationSL(Context mContext, BasePostServiceSL<String, WebServiceErrorModel> basePostServiceSL) {
        super(mContext, basePostServiceSL);

    }

    @Override
    public BaseServiceConfigurationSL<String, WebServiceErrorModel> getServiceConfiguration() {
        ContentValues params = new ContentValues();
        //params.put("Content-Type", "application/x-www-form-urlencoded");
        //params.put("deviceUid", getDeviceUID(this.context));

        this.setHttpHeaders(params);
        this.setIsGzipEncode(false);
        this.setIsBasicHttpBinding(false);


        //set biletix http codes
        this.setErrorStatusCodes(Arrays.asList(500,301));
        this.setContentType("application/json");
        //TODO REsponse container JSON'ı set et
        ServiceResponseContainerJSON biletixServiceResponseContainerJSON = new ServiceResponseContainerJSON(context,basePostServiceSL,basePostServiceSL);
        this.setServiceResponseModelContainerJSON(biletixServiceResponseContainerJSON);
        //this.getBasePostServiceSL().addErrorListener(this.getBasePostServiceSL());
        //this.getBasePostServiceSL().addListener(this.getBasePostServiceSL());
        return  this;
    }

    public String getDeviceUID(Context mContext){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String deviceUid = preferences.getString("deviceUid", "");
        try {
            if (deviceUid.isEmpty()) {

                deviceUid = Build.SERIAL;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("deviceUid", deviceUid);
                editor.commit();


            }else{
                if (deviceUid == null || deviceUid.equalsIgnoreCase("")) {
                    deviceUid = UUID.randomUUID().toString();
                }
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("deviceUid", deviceUid);
                editor.commit();
            }

        } catch (Exception ex) {
            if (deviceUid == null || deviceUid.equalsIgnoreCase("")) {
                deviceUid = UUID.randomUUID().toString();
            }
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("deviceUid", deviceUid);
            editor.commit();
        }


        return deviceUid;

    }




}
