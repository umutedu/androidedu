package com.bilgeadam.edu.vkfmobil.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bilgeadam.edu.vkfmobil.R;

/**
 * Created by umutboz on 26,January,2018
 */

public class ThirdFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.third_fragment_layout,container,false);
        return view;
    }


}
