package com.bilgeadam.edu.vkfmobil.sl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 02483564 on 6.10.2016.
 */
public class ServiceUrlSL {

    public String Platform  = "Live"; // Live or Test, UAT
//?language=tr-TR&recursiveCategories=true&serviceIds=TEST_OFFER&serviceIds=TAP&serviceIds=SVODSINEMAHEMEN&serviceIds=SVODDIZI&serviceIds=SVODGENEL&serviceIds=TVOD&serviceIds=SVODBELGESEL&serviceIds=SVODEGITIM&serviceIds=SVOD_TEST&serviceIds=SVODSPOR&serviceIds=SVODFILM&ebcs=MOBIL&categoryId=%2FSecizle%2FFilm%2FTumfilmler
    public String TEST_ROOT_URL = "https://ott.mvp.tivibu.com.tr/";
    public String UAT_ROOT_URL = "";
    public String LIVE_ROOT_URL = "https://ott.mvp.tivibu.com.tr/";


    /*

    VOD detay sayfası için de şunu kullanabiliriz:
https://ott.mvp.tivibu.com.tr/?vodId=93562&language=tr-TR
     */

    protected Map<String, String> testMethods = new HashMap<String, String>();
    protected Map<String, String> liveMethods = new HashMap<String, String>();

    private static ServiceUrlSL mInstance = new ServiceUrlSL();

    public static ServiceUrlSL getInstance(){
        return mInstance;
    }

    public ServiceUrlSL() {
        setMethods();
    }

    public void setMethods() {
        testMethods.put(ConstantsSL.offerings, "iap-dataapi/public/vod/offerings");
        testMethods.put(ConstantsSL.vodDetails,"ott-public/portal-coreapps-backend-iap-war/vod/vodDetails.ajax");

        liveMethods.put(ConstantsSL.offerings, "iap-dataapi/public/vod/offerings");
        liveMethods.put(ConstantsSL.vodDetails, "ott-public/portal-coreapps-backend-iap-war/vod/vodDetails.ajax");



    }

    public String getURL(String methodName) {
        if (Platform.equals("Test")) {
            return TEST_ROOT_URL + testMethods.get(methodName);
        } else if (Platform.equals("UAT")) {
            return UAT_ROOT_URL + liveMethods.get(methodName);
        } else if (Platform.equals("Live")) {
            return LIVE_ROOT_URL + liveMethods.get(methodName);
        }
        return "";
    }

    public String getURL(String methodName, String parameters) {
        String url = getURL(methodName);
        //parameters yapısı güzenlenecek
        return url + parameters;
    }
}
