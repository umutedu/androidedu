package com.bilgeadam.edu.vkfmobil.sl.Base;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.bilgeadam.edu.vkfmobil.R;
import com.bilgeadam.edu.vkfmobil.common.Base.ServiceUtil;
import com.bilgeadam.edu.vkfmobil.json.Base.BaseServiceResponseModelContainerJSON;
import com.bilgeadam.edu.vkfmobil.listener.Base.ServiceClientListener;
import com.bilgeadam.edu.vkfmobil.listener.Base.ServiceErrorClientListener;
import com.bilgeadam.edu.vkfmobil.model.Base.BaseServiceInfoModel;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;




public class BaseGetServiceClient<TModel, TErrorModel> extends AsyncTask<String, Void, String> {

    private final  String COOKIE_TAG ="Cookie";
    java.net.CookieManager msCookieManager = new java.net.CookieManager();
    /* LOCK CONFIG */
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition tryAgain = lock.newCondition();
    private volatile boolean finished = false;


    /*LISTENER MEMBERS */
    private ArrayList<ServiceClientListener<TModel>> _listeners = new ArrayList<ServiceClientListener<TModel>>();
    private ArrayList<ServiceErrorClientListener<TErrorModel>> errorlisteners = new ArrayList<ServiceErrorClientListener<TErrorModel>>();

    /*BASE MEMBERS */
    private BaseServiceConfigurationSL<TModel, TErrorModel> baseServiceConfiguration;
    protected BaseServiceResponseModelContainerJSON<TModel, TErrorModel> baseServiceResponseModelContainerJSON;

    private boolean isReceiveCookie;
    private String  responseCookieTag;
    List<String> responseCookiesHeader;
    private String requestCookie;

    Boolean serviceStatus = false;
    Context context = null;
    String _serviceName = "";
    String errorMessage = "";
    InputStream inputStream = null;

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    String methodName = null;
    boolean isArrived = false;
    private boolean isQueryString = false;
    private String RequestBody = null;
    private boolean IsGzipEncode = false;
    private String queryString;
    private ContentValues nameValueParams;
    private List<Integer> checkStatusCodes = new ArrayList<>(0);

    public BaseGetServiceClient(Context contextInstance, String serviceName) {

        context = contextInstance;
        _serviceName = serviceName;

    }

    public BaseGetServiceClient(Context contextInstance, String serviceName,
                                ContentValues params) {

        context = contextInstance;
        _serviceName = serviceName;
        nameValueParams = params;

    }

    public String readJSONFeed(String Url, String uriParam) throws Exception {

        String uri = Url;
        errorMessage = "";
        String responseString = "";

        if (uriParam != null && !uriParam.equalsIgnoreCase("")) {

            //uri += uriParam;
            //this.methodName = uriParam;
        }
        HttpURLConnection connection = null;
        try {
            int httpCallTimeout = Integer.parseInt(context.getString(R.string.httpCallTimeout));
            if (isQueryString()) {
                uri += getQueryString();
            } else {
                if (nameValueParams != null && nameValueParams.size() > 0) {
                    String urlParams = "";
                    for (Map.Entry<String, Object> entry : nameValueParams.valueSet()) {
                        if (entry.getValue() == null
                                || entry.getValue().equals("") == true) {
                            urlParams += "/%20";
                        } else {
                            urlParams += "/" + entry.getValue();
                        }
                    }
                    uri += urlParams;

                }
            }
            Log.d(context.getString(R.string.APP_LOG_CAT_NAME), "get servis url : " + uri);
            URL url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            if (baseServiceConfiguration != null) {
                for (Map.Entry<String, Object> entry : baseServiceConfiguration.getHttpHeaders().valueSet()) {

                    connection.setRequestProperty(entry.getKey(), entry.getValue().toString());
                }
                setBaseServiceResponseModelContainerJSON(baseServiceConfiguration.getServiceResponseModelContainerJSON());

                if(requestCookie !=null&& requestCookie.length()>0)
                {
                    connection.setRequestProperty(COOKIE_TAG, requestCookie);
                }

            } else {
                serviceStatus = false;
                errorMessage = "Base Service Configuration has Not Init";
                return errorMessage;
            }
            connection.setRequestProperty("Content-Type", baseServiceConfiguration.getContentType());
            connection.setConnectTimeout(httpCallTimeout);
            connection.setReadTimeout(httpCallTimeout);
            connection.setRequestMethod("GET");
            errorMessage += uri;

            int statusCode = connection.getResponseCode();
            Log.d(context.getString(R.string.APP_LOG_CAT_NAME), uri + " status code : " + statusCode);
            if (statusCode == HttpURLConnection.HTTP_OK) {
                isArrived = true;
                try {
                    inputStream = new BufferedInputStream(connection.getInputStream());
                            /* if(getIS_LARGE_DATA())
			                 {
			                 responseData =ServiceUtil.EncodeGZipStreamLargeData(inputStream);
			                 }
			                 else*/
                    if (baseServiceConfiguration.isGzipEncode()) {
                        responseString  = ServiceUtil.EncodeGZipStream(inputStream);
                    } else {
                        responseString = ServiceUtil.EncodeUTF8InputStream(inputStream);
                    }

                    if(isReceiveCookie)
                    {
                        Map<String, List<String>> headerFields = connection.getHeaderFields();
                        responseCookiesHeader = headerFields.get(responseCookieTag);
                    }
                    serviceStatus = true;

                } catch (Exception ex) {
                    errorMessage += "Web servisden gelen data problemi";
                }
            } else {

                if (baseServiceConfiguration != null) {
                    if (baseServiceConfiguration.getErrorStatusCodes().size() > 0) {
                        for (Integer code : baseServiceConfiguration.getErrorStatusCodes()) {
                            if (statusCode == code) {
                                isArrived = true;
                                serviceStatus = true;
                                inputStream = new BufferedInputStream(connection.getErrorStream());
                                if (baseServiceConfiguration.isGzipEncode()) {
                                    responseString = ServiceUtil.EncodeGZipStream(inputStream);
                                } else {
                                    responseString = ServiceUtil.EncodeUTF8InputStream(inputStream);
                                }
                                break;
                            }
                        }
                    }
                }
                if (!isArrived) {
                    errorMessage += "http Status Code : " + statusCode;
                }
            }
        } catch (Exception ex) {
            errorMessage = ex.getMessage();
        }
        Log.d(context.getString(R.string.APP_LOG_CAT_NAME), uri + " response data : " + responseString);
        return responseString;
    }

    protected String doInBackground(String... urls) {

        String result = "";
        try {
            result = readJSONFeed(urls[0], getQueryString());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    protected void onPostExecute(String result) {

        _fireResponseEvent(result);

    }

    public String getServiceMethodName() {
        return this.methodName;
    }

    public String getServiceName() {
        return this._serviceName;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

	/* Listener members */

	/* ERROr Listener Memebers */

    public synchronized void removeServiceClientListener(
            ServiceErrorClientListener l) {
        errorlisteners.remove(l);
    }

    public synchronized void addErrorErrorServiceClientListener(
            ServiceErrorClientListener<TErrorModel> l) {
        errorlisteners.add(l);
    }

    public synchronized void removeServiceClientListener(
            ServiceClientListener<TModel> l) {
        _listeners.remove(l);
    }

    public synchronized void addServiceClientListener(ServiceClientListener<TModel> l) {
        _listeners.add(l);
    }

    private synchronized void _fireResponseEvent(String responseData) {
        BaseServiceInfoModel<TModel> responseEvent = new BaseServiceInfoModel<TModel>(
                this, this._serviceName, responseData, serviceStatus,
                errorMessage);
        responseEvent.setMethodName(getServiceMethodName());
        responseEvent.setIsArrived(isArrived);
        responseEvent.setSentParams(nameValueParams);
        responseEvent.setCookieHeaders(responseCookiesHeader);
        baseServiceResponseModelContainerJSON.initServiceResponse(responseData, responseEvent);

    }
	/* INIT BASE RESPONSE CONTAINER */

    public void setBaseServiceResponseModelContainerJSON(BaseServiceResponseModelContainerJSON<TModel, TErrorModel> mBaseServiceResponseModelContainerJSON) {
        baseServiceResponseModelContainerJSON = mBaseServiceResponseModelContainerJSON;
    }


    /* SERVICE CONFIGURATION */
    public BaseServiceConfigurationSL<TModel, TErrorModel> getBaseServiceConfiguration() {
        return baseServiceConfiguration;
    }

    public void setBaseServiceConfiguration(BaseServiceConfigurationSL<TModel, TErrorModel> baseServiceConfiguration) {
        this.baseServiceConfiguration = baseServiceConfiguration;
    }

    public boolean isQueryString() {
        return isQueryString;
    }

    public void setIsQueryString(boolean isQueryString) {
        this.isQueryString = isQueryString;
    }

    public boolean isIsGzipEncode() {
        return IsGzipEncode;
    }

    public void setIsGzipEncode(boolean isGzipEncode) {
        IsGzipEncode = isGzipEncode;
    }

    public List<Integer> getCheckStatusCodes() {
        return checkStatusCodes;
    }

    public void setCheckStatusCodes(List<Integer> checkStatusCodes) {
        this.checkStatusCodes = checkStatusCodes;
    }

    public void isReceiveCookie(boolean setCookie) {
        isReceiveCookie = setCookie;
    }

    public void setResponseCookieTag(String cookieTag) {
        this.responseCookieTag = cookieTag;
    }

    public void setRequestCookie(String requestCookie) {
        this.requestCookie = requestCookie;
    }

}
