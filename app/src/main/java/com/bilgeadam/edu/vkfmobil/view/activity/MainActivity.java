package com.bilgeadam.edu.vkfmobil.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bilgeadam.edu.vkfmobil.R;
import com.bilgeadam.edu.vkfmobil.common.Base.AndroidLogger;
import com.bilgeadam.edu.vkfmobil.json.ModelParserJSON;
import com.bilgeadam.edu.vkfmobil.listener.ServiceListener;
import com.bilgeadam.edu.vkfmobil.model.Base.BaseServiceErrorModel;
import com.bilgeadam.edu.vkfmobil.model.Base.BaseServiceInfoModel;
import com.bilgeadam.edu.vkfmobil.model.TiviBuModel;
import com.bilgeadam.edu.vkfmobil.model.WebServiceErrorModel;
import com.bilgeadam.edu.vkfmobil.sl.WebServiceSL;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebServiceSL webServiceSL = new WebServiceSL(MainActivity.this, new ServiceListener<String,WebServiceErrorModel>() {
            @Override
            public void OnComplete(BaseServiceInfoModel<String> onComplete) {
                ModelParserJSON<TiviBuModel> parserJSON = new ModelParserJSON<>();
                List<TiviBuModel> list = parserJSON.getListModelFromJSON(onComplete.getServiceResponseModel(),TiviBuModel.class);

                AndroidLogger.writeLog(MainActivity.this,"test");

            }

            @Override
            public void OnError(BaseServiceErrorModel onError) {

            }
        });
        webServiceSL.getAllList();

    }
}
