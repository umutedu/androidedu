package com.bilgeadam.edu.vkfmobil.listener.Base;


import com.bilgeadam.edu.vkfmobil.model.Base.BaseServiceErrorModel;

public interface ServiceErrorClientListener<TErrorModel> {

	public void OnError(BaseServiceErrorModel<TErrorModel> responseBaseServiceErrorModel);
}
