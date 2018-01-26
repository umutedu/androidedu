package com.bilgeadam.edu.vkfmobil.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bilgeadam.edu.vkfmobil.R;
import com.bilgeadam.edu.vkfmobil.common.Base.AndroidLogger;
import com.bilgeadam.edu.vkfmobil.json.ModelParserJSON;
import com.bilgeadam.edu.vkfmobil.listener.ServiceListener;
import com.bilgeadam.edu.vkfmobil.model.Base.BaseServiceErrorModel;
import com.bilgeadam.edu.vkfmobil.model.Base.BaseServiceInfoModel;
import com.bilgeadam.edu.vkfmobil.model.TiviBuModel;
import com.bilgeadam.edu.vkfmobil.model.WebServiceErrorModel;
import com.bilgeadam.edu.vkfmobil.sl.WebServiceSL;
import com.bilgeadam.edu.vkfmobil.view.activity.MainActivity;
import com.bilgeadam.edu.vkfmobil.view.adapter.TopFilmsRecyclerAdapter;

import java.util.List;

/**
 * Created by umutboz on 26,January,2018
 */

public class TopFilmsFragment extends Fragment {

    List<TiviBuModel> allFilms;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.topfilms_fragment_layout,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.top_films_fragment_recyclerView);
        initFilmsData();
        return view;
    }


    void initFilmsData()
    {
        WebServiceSL webServiceSL = new WebServiceSL(getActivity(), new ServiceListener<String,WebServiceErrorModel>() {
            @Override
            public void OnComplete(BaseServiceInfoModel<String> onComplete) {
                ModelParserJSON<TiviBuModel> parserJSON = new ModelParserJSON<>();
                allFilms = parserJSON.getListModelFromJSON(onComplete.getServiceResponseModel(),TiviBuModel.class);
                AndroidLogger.writeLog(getActivity(),"test");

                TopFilmsRecyclerAdapter adapter = new TopFilmsRecyclerAdapter(allFilms,getActivity());
                recyclerView.setAdapter(adapter);
                //recyclerView.setLayoutManager(new LinearLayoutManager(new LinearLayout(LinearLayout.VERTICAL)));


            }

            @Override
            public void OnError(BaseServiceErrorModel onError) {

            }
        });
        webServiceSL.getAllList();

    }
}
