package com.bilgeadam.edu.vkfmobil.listener.Base;




import com.bilgeadam.edu.vkfmobil.model.Base.BaseServiceInfoModel;

import java.util.EventListener;


public interface ServicePostClientListener<TModel> extends EventListener {

	public void OnPOSTCommit(BaseServiceInfoModel<TModel> responseEvent);


}
