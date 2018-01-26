package com.bilgeadam.edu.vkfmobil.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bilgeadam.edu.vkfmobil.R;


/**
 * Created by Umut.Boz on 1.11.2016.
 */

public class SharedPreferencesUtility {

    private Context context;
    private SharedPreferences sharedPreferences;

    public SharedPreferencesUtility(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public void setLocationPostion(String latitude, String longitude) {
        SharedPreferences.Editor lastLocation = sharedPreferences.edit();
        lastLocation.putString("latitude", latitude);
        lastLocation.putString("longitude", longitude);
        lastLocation.commit();
    }

    public String getlastLatitude() {
        return sharedPreferences.getString("latitude", context.getString(R.string.defaultLatitude));
    }

    public void setClientId(String clientId) {
        SharedPreferences.Editor lastLocation = sharedPreferences.edit();
        lastLocation.putString("clientId", clientId);
        lastLocation.commit();
    }

    public String getClientId() {
        return sharedPreferences.getString("clientId", "");
    }
 public void setFacebookName(String name) {
        SharedPreferences.Editor lastLocation = sharedPreferences.edit();
        lastLocation.putString("facebookName", name);
        lastLocation.commit();
    }

    public String getFacebookName() {
        return sharedPreferences.getString("facebookName", "");
    }

    public void setMusicScan(int count) {
        SharedPreferences.Editor lastLocation = sharedPreferences.edit();
        lastLocation.putInt("count", count);
        lastLocation.commit();
    }

    public int getMusicScan() {
        return sharedPreferences.getInt("count", 0);
    }

    public String getlastLongitude() {
        return sharedPreferences.getString("longitude", context.getString(R.string.defaultLongitude));
    }

    public void setAppFirstUse(boolean state) {
        SharedPreferences.Editor appUser = sharedPreferences.edit();
        appUser.putBoolean("APP_FIRST_USE", state);
        appUser.commit();
    }

    public boolean getAppFirstUse() {
        return sharedPreferences.getBoolean("APP_FIRST_USE", false);
    }


    /*FIRST LAUNCH STEP */
    public void setFirstLaunchStep(boolean state) {
        SharedPreferences.Editor appUser = sharedPreferences.edit();
        appUser.putBoolean("IS_FIRST_LAUNCH_STEP_FINISH", state);
        appUser.commit();
    }

    public boolean getIsFirstLaunchStepFinish() {
        return sharedPreferences.getBoolean("IS_FIRST_LAUNCH_STEP_FINISH", false);
    }

    public void setSelectedRegion(String selectedRegion) {
        SharedPreferences.Editor appUser = sharedPreferences.edit();
        appUser.putString("SELECTED_REGION", selectedRegion);
        appUser.commit();
    }

    public String getFacebookMe() {
        return sharedPreferences.getString("FACEBOOK_ME", "");
    }

    public void setFacebookMe(String facebookMe) {
        SharedPreferences.Editor appUser = sharedPreferences.edit();
        appUser.putString("FACEBOOK_ME", facebookMe);
        appUser.commit();
    }

    public void setFacebookScan(boolean state) {
        SharedPreferences.Editor appUser = sharedPreferences.edit();
        appUser.putBoolean("FACEBOOK_SCAN", state);
        appUser.commit();
    }

    public boolean getFacebookScan() {
        return sharedPreferences.getBoolean("FACEBOOK_SCAN", false);
    }

    public void setDeviceMusicScan(boolean state) {
        SharedPreferences.Editor appUser = sharedPreferences.edit();
        appUser.putBoolean("DEVICE_MUSIC_SCAN", state);
        appUser.commit();
    }

    public boolean getDeviceMusicScan() {
        return sharedPreferences.getBoolean("DEVICE_MUSIC_SCAN", false);
    }

    public String getSelectedRegion() {
        return sharedPreferences.getString("SELECTED_REGION", "");
    }


    public void setTranLogOnModel(boolean state) {

            SharedPreferences.Editor appUser = sharedPreferences.edit();
            appUser.putBoolean("TRAN_LOGON_MODEL", state);
            appUser.commit();
    }
    public boolean isTranLogOnModel() {

        return sharedPreferences.getBoolean("TRAN_LOGON_MODEL", false);
    }


    public void setLogOnModel(String logOnModelJson) {
        if (logOnModelJson != null && !logOnModelJson.equals("")) {
            SharedPreferences.Editor appUser = sharedPreferences.edit();
            appUser.putString("LOGON_MODEL", logOnModelJson);
            appUser.commit();

            setUserAuthenticationStatus(true);
        } else {
            setUserAuthenticationStatus(false);
        }

    }

    public String getLogOnModel() {
        return sharedPreferences.getString("LOGON_MODEL", null);
    }

    public void setUserAuthenticationStatus(boolean status) {

        SharedPreferences.Editor appUser = sharedPreferences.edit();
        appUser.putBoolean("USER_AUTHENTICATION_STATUS", status);
        appUser.commit();
    }

    public boolean getUserAuthenticationStatus() {

        return sharedPreferences.getBoolean("USER_AUTHENTICATION_STATUS", false);
    }


    public void setLoginModel(String loginModel) {

        SharedPreferences.Editor appUser = sharedPreferences.edit();
        appUser.putString("LOGIN_MODEL", loginModel);
        appUser.commit();
    }

    public String getLoginModel() {

        return sharedPreferences.getString("LOGIN_MODEL", "");
    }

    public void setTransactionCookie(String cookies) {

        SharedPreferences.Editor appUser = sharedPreferences.edit();
        appUser.putString("SET_COOKIE", cookies);
        appUser.commit();
    }

    public String getTransactionCookie() {

        return sharedPreferences.getString("SET_COOKIE", "");
    }
//
}
