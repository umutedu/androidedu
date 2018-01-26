package com.bilgeadam.edu.vkfmobil.model.Base;

import java.util.Date;
import java.util.List;

public class BaseServiceErrorModel<T> {

		private String ErrorCode;
		private String Description;
		private Date ErrorDate;
		
		private T ErrorModel;
		private List<T> ErrorModelList;
		
		public String getErrorCode() {
			return ErrorCode;
		}
		public void setErrorCode(String errorCode) {
			ErrorCode = errorCode;
		}
		public String getDescription() {
			return Description;
		}
		public void setDescription(String description) {
			Description = description;
		}
		public Date getErrorDate() {
			return ErrorDate;
		}
		public void setErrorDate(Date errorDate) {
			ErrorDate = errorDate;
		}
		public T getErrorModel() {
			return ErrorModel;
		}
		public void setErrorModel(T errorModel) {
			ErrorModel = errorModel;
		}
		public List<T> getErrorModelList() {
		return ErrorModelList;
	}
		public void setErrorModelList(List<T> errorList) {
		ErrorModelList = errorList;
	}
}
