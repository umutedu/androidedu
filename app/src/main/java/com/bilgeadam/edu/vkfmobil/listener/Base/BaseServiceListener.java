package com.bilgeadam.edu.vkfmobil.listener.Base;



import com.bilgeadam.edu.vkfmobil.model.Base.BaseServiceErrorModel;
import com.bilgeadam.edu.vkfmobil.model.Base.BaseServiceInfoModel;

import java.util.EventListener;


public interface BaseServiceListener<TModel,TErrorModel> extends EventListener {
	
	public void OnComplete(BaseServiceInfoModel<TModel> onComplete);
	public void OnError(BaseServiceErrorModel<TErrorModel> onError);

}
