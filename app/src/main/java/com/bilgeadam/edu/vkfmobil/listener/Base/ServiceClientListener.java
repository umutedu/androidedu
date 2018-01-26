package com.bilgeadam.edu.vkfmobil.listener.Base;




import com.bilgeadam.edu.vkfmobil.model.Base.BaseServiceInfoModel;

import java.util.EventListener;


public interface ServiceClientListener<T> extends EventListener {

	public void OnGETReceive(BaseServiceInfoModel<T> responseEvent);

}
