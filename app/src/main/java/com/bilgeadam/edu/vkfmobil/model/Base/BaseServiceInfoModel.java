package com.bilgeadam.edu.vkfmobil.model.Base;

import android.content.ContentValues;

import java.io.InputStream;
import java.util.EventObject;
import java.util.List;


public class BaseServiceInfoModel<T>  extends EventObject  {

	
	/**
	 * Response Service Event Type
	 */
	private static final long serialVersionUID = 1L;

	public BaseServiceInfoModel(Object source, String serviceName, String data, Boolean ServiceStatus, String ErrorMessage) {
		super(source);
		_serviceName= serviceName;
		responseData = data;
		serviceStatus =ServiceStatus;
		setErrorMessage(ErrorMessage);
		
	}
	

	private String _serviceName;
	private String responseData;
	private Boolean serviceStatus;
	private String errorMessage;
	
	private String sendData;
	private String methodName;
	private boolean isArrived;
	private InputStream stream;
	private T ServiceResponseModel;
	private int responseStatusCode=0;
	private boolean isNot200=false;
	private List<String> cookieHeaders;


	private String RequestBody;

	public T getServiceResponseModel()
	{
		return  ServiceResponseModel;
	}

	public  void  setServiceResponseModel(T serviceResponseModel)
	{
		ServiceResponseModel = serviceResponseModel;
	}
	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String reequestData) {
		this.responseData = reequestData;
	}

	public String getServiceName() {
		return _serviceName;
	}

	public void setServiceName(String serviceName) {
		this._serviceName = serviceName;
	}

	public Boolean getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(Boolean serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public void setMethodName(String method) {
		 methodName = method;
	}
	public String getMethodName() {
		return methodName;
	}
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSendData() {
		return sendData;
	}

	public void setSendData(String sendData) {
		this.sendData = sendData;
	}

	ContentValues SenTParamters;
	public void setSentParams(ContentValues SentParamters)
	{
		SenTParamters = SentParamters;
	}
	
	public ContentValues getSentParams()
	{
		return SenTParamters;
	}

	/**
	 * @return the stream
	 */
	public InputStream getStream() {
		return stream;
	}

	/**
	 * @param stream the stream to set
	 */
	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	/**
	 * @return the isArrived
	 */
	public boolean getIsArrived() {
		return isArrived;
	}

	/**
	 * @param isArrived the isArrived to set
	 */
	public void setIsArrived(boolean isArrived) {
		this.isArrived = isArrived;
	}

	public String getRequestBody() {
		return RequestBody;
	}

	public void setRequestBody(String requestBody) {
		RequestBody = requestBody;
	}

	public int getResponseStatusCode() {
		return responseStatusCode;
	}

	public void setResponseStatusCode(int responseStatusCode) {
		this.responseStatusCode = responseStatusCode;
	}

	public boolean isNot200() {
		return isNot200;
	}

	public void setNot200(boolean not200) {
		isNot200 = not200;
	}


	public List<String> getCookieHeaders() {
		return cookieHeaders;
	}

	public void setCookieHeaders(List<String> cookieHeaders) {
		this.cookieHeaders = cookieHeaders;
	}
}
