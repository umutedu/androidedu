package com.bilgeadam.edu.vkfmobil.sl.Base;


import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.bilgeadam.edu.vkfmobil.R;
import com.bilgeadam.edu.vkfmobil.common.Base.AndroidLogger;
import com.bilgeadam.edu.vkfmobil.common.Base.ServiceUtil;
import com.bilgeadam.edu.vkfmobil.json.Base.BaseServiceResponseModelContainerJSON;
import com.bilgeadam.edu.vkfmobil.listener.Base.ServiceErrorClientListener;
import com.bilgeadam.edu.vkfmobil.listener.Base.ServicePostClientListener;
import com.bilgeadam.edu.vkfmobil.model.Base.BaseServiceInfoModel;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BaseServicePostClientSL<TModel,TErrorModel> extends AsyncTask
<String, Void, String>  {

	private final  String COOKIE_TAG ="Cookie";
	java.net.CookieManager msCookieManager = new java.net.CookieManager();
	/*LISTENER MEMBERS */
	private ArrayList<ServicePostClientListener<TModel>> _listeners = new ArrayList<ServicePostClientListener<TModel>>();
	private ArrayList<ServiceErrorClientListener<TErrorModel>> errorlisteners = new ArrayList<ServiceErrorClientListener<TErrorModel>>();

	/*BASE MEMBERS */
	private BaseServiceConfigurationSL<TModel,TErrorModel> baseServiceConfiguration ;
	protected BaseServiceResponseModelContainerJSON<TModel,TErrorModel> baseServiceResponseModelContainerJSON;


	/*PRIVATE MEMBERS */
	private Boolean serviceStatus =false;
	private Context context=null;
	private String errorMessage ="";
	private String _serviceName ="";
	private String soapInterface="";
	private InputStream inputStream =null;
	private String soapAction="";
	private boolean isArrived =false;
	private int responseStatusCode=0;
	private boolean isNot200=false;

	private boolean isReceiveCookie;
	private String  responseCookieTag;
	List<String> responseCookiesHeader;
	private String requestCookie;


	//		private boolean isBasicHttpBinding=false;
	private String RequestBody=null;
	//	private boolean IsGzipEncode=false;

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	//private boolean IS_LARGE_DATA=false;
	private String methodName = "";
	private String sendData="";

	private ContentValues  SoapBodyParameters;

	/* CONSTRUCTORS*/

	public BaseServicePostClientSL(Context contextInstance,String serviceName,String requestBody)
	{
		context =contextInstance;
		_serviceName =  serviceName;
		setRequestBody(requestBody);

	}

	public BaseServicePostClientSL(Context contextInstance,String serviceName,String SOAPInterface,String soapMethodName,ContentValues soapParams)
	{
		context =contextInstance;
		_serviceName =  serviceName;
		soapInterface = SOAPInterface;
		methodName = soapMethodName;
		SoapBodyParameters = soapParams;
	}

	//CALL SERVICE METHOD

		public String readPostFeed(String Url,String uriParam) throws Exception{

			String uri=Url;
			String responseData="";
			errorMessage ="";

			if (uriParam != null && !uriParam.equalsIgnoreCase("")) {

				uri += uriParam;
				//this.methodName = uriParam;
			}
				HttpURLConnection connection=null;
				try {
					int httpCallTimeout = Integer.parseInt(context.getString(R.string.httpCallTimeout));
					Log.d(context.getString(R.string.APP_LOG_CAT_NAME), "get servis url : " + uri);
					URL url = new URL(uri);
					connection = (HttpURLConnection) url.openConnection();

					if(baseServiceConfiguration!=null)
					{
						connection.setRequestProperty("Content-Type",baseServiceConfiguration.getContentType());

						for(Map.Entry<String, Object> entry : baseServiceConfiguration.getHttpHeaders().valueSet()){
							connection.setRequestProperty(entry.getKey(), entry.getValue().toString());
						}
						setBaseServiceResponseModelContainerJSON(baseServiceConfiguration.getServiceResponseModelContainerJSON());

						if(requestCookie !=null&& requestCookie.length()>0)
						{
							connection.setRequestProperty(COOKIE_TAG, requestCookie);
						}
					}else
					{
						serviceStatus =false;
						errorMessage = "Base Service Configuration has Not Init";
						return errorMessage;
					}

					if(baseServiceConfiguration.isBasicHttpBinding())
					{
					String SOAPRequestXML = "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">\n";
					SOAPRequestXML += "<s:Body>\n";
					SOAPRequestXML += "<"+methodName+ " xmlns=\"http://tempuri.org/\">";

					if(SoapBodyParameters!=null && SoapBodyParameters.size()>0)
					{
						for (Map.Entry<String, Object> entry : SoapBodyParameters.valueSet())
						 {
							SOAPRequestXML += "\n<"+entry.getKey()+">" ;
							SOAPRequestXML += entry.getValue() ;
							SOAPRequestXML += "</"+entry.getKey()+">" ;
						 }
					}
					SOAPRequestXML += "\n</"+methodName+">";
					SOAPRequestXML += "</s:Body>\n";
					SOAPRequestXML += "</s:Envelope>";

					sendData = SOAPRequestXML; errorMessage += uri;
					}
					else
					{
					sendData = RequestBody ==null?"":RequestBody;
						AndroidLogger.writeLog(context,"service request " + sendData);
					//methodName = uriParam;

					}
					connection.setDoOutput(true);
					connection.setDoInput(true);
					connection.setRequestMethod("POST");
					connection.connect();
					OutputStream os = connection.getOutputStream();
					os.write(sendData.getBytes("UTF-8"));
					os.close();
					errorMessage += uri;

					int statusCode = connection.getResponseCode();
					Log.d(context.getString(R.string.APP_LOG_CAT_NAME), uri + " status code : " + statusCode);
					setResponseStatusCode(statusCode);
					if (statusCode == HttpURLConnection.HTTP_OK) {
			        	 isArrived =true;
						try
			    		{
							inputStream = new BufferedInputStream(connection.getInputStream());
			                /* if(getIS_LARGE_DATA())
			                 {
			                 responseData =ServiceUtil.EncodeGZipStreamLargeData(inputStream);
			                 }
			                 else*/
			                 if(baseServiceConfiguration.isGzipEncode())
			                 {
			                	 responseData = ServiceUtil.EncodeGZipStream(inputStream);
			                 }
			                 else
			                 {
			                	 responseData =ServiceUtil.EncodeUTF8InputStream(inputStream);
			                 }
							if(isReceiveCookie)
							{
								Map<String, List<String>> headerFields = connection.getHeaderFields();
								responseCookiesHeader = headerFields.get(responseCookieTag);

							}

							serviceStatus =true;

						} catch (Exception ex) {
							errorMessage += "Web servisden gelen data problemi";
						}
					} else {

						setNot200(true);
						if(baseServiceConfiguration!=null)
						{
							if(baseServiceConfiguration.getErrorStatusCodes().size()>0)
							{
								for (Integer code: baseServiceConfiguration.getErrorStatusCodes()) {
									if(statusCode == code)
									{
										isArrived =true;
										serviceStatus =true;
										inputStream = new BufferedInputStream(connection.getErrorStream());
										if(baseServiceConfiguration.isGzipEncode())
										{
											responseData = ServiceUtil.EncodeGZipStream(inputStream);
										}
										else
										{
											responseData =ServiceUtil.EncodeUTF8InputStream(inputStream);
										}
										break;
									}


								}
							}
						}
						if(!isArrived) {
							errorMessage += "http Status Code : " + statusCode;
						}
					}
				}
				catch (Exception ex) {
					errorMessage = ex.getMessage();
				}

			Log.d(context.getString(R.string.APP_LOG_CAT_NAME), uri + " response data : " + responseData);
	        return responseData;
	    }


	  protected String doInBackground(String... urls)  {

		  String result ="";
		  try
		  {
			  if(urls.length!=1)
			  {
				  result = readPostFeed(urls[0],urls[1]);
			  }
			  else
			  {
				  result = readPostFeed(urls[0],null);
			  }




		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return result;
      }



	  protected void onPostExecute(String result) {

		  this.fireResponseEvent(result);

	  }





	/*ERROr Listener Memebers*/

	  public synchronized void removeServiceClientListener( ServiceErrorClientListener<TErrorModel> l ) {
	        errorlisteners.remove(l);
	    }
	    public synchronized void addErrorErrorServiceClientListener( ServiceErrorClientListener<TErrorModel> l ) {
	        errorlisteners.add(l);
	    }



	  /*  Listener members  */
	     public synchronized void removeServiceClientListener( ServicePostClientListener<TModel> l ) {
	        _listeners.remove(l);
	    }

	public synchronized void addServiceClientListener( ServicePostClientListener<TModel> l ) {
	        _listeners.add(l);
	    }

	    private synchronized void fireResponseEvent(String responseData) {

	        BaseServiceInfoModel<TModel> responseEvent = new BaseServiceInfoModel<TModel>( this,this._serviceName, responseData ,serviceStatus,errorMessage);
	        responseEvent.setStream(inputStream);
	        responseEvent.setSendData(sendData);
	        responseEvent.setIsArrived(isArrived);
	        responseEvent.setSentParams(SoapBodyParameters);
	       	responseEvent.setMethodName(methodName);
			responseEvent.setRequestBody(getRequestBody());
			responseEvent.setResponseStatusCode(getResponseStatusCode());
			responseEvent.setNot200(isNot200());
			responseEvent.setCookieHeaders(responseCookiesHeader);
			baseServiceResponseModelContainerJSON.initServiceResponse(responseData, responseEvent);
	    }



		public String getServiceName()
		{
		return this._serviceName;
		}

		public String getRequestBody() {
			return RequestBody;
		}


		public void setRequestBody(String requestBody) {
			RequestBody = requestBody;
		}


		/* INIT BASE RESPONSE CONTAINER */

		public void setBaseServiceResponseModelContainerJSON(BaseServiceResponseModelContainerJSON<TModel,TErrorModel> mBaseServiceResponseModelContainerJSON)
		{
			baseServiceResponseModelContainerJSON = mBaseServiceResponseModelContainerJSON;
		}


	/* SERVICE CONFIGURATION */
	public BaseServiceConfigurationSL<TModel, TErrorModel> getBaseServiceConfiguration() {
		return baseServiceConfiguration;
	}

	public void setBaseServiceConfiguration(BaseServiceConfigurationSL<TModel, TErrorModel> baseServiceConfiguration) {
		this.baseServiceConfiguration = baseServiceConfiguration;
	}

	public int getResponseStatusCode() {
		return responseStatusCode;
	}

	protected void setResponseStatusCode(int responseStatusCode) {
		this.responseStatusCode = responseStatusCode;
	}

	public boolean isNot200() {
		return isNot200;
	}

	protected void setNot200(boolean not200) {
		isNot200 = not200;
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


