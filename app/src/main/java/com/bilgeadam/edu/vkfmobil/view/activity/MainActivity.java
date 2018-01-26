package com.bilgeadam.edu.vkfmobil.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import com.bilgeadam.edu.vkfmobil.R;
import com.bilgeadam.edu.vkfmobil.common.Base.AndroidLogger;
import com.bilgeadam.edu.vkfmobil.common.LocationManger;
import com.bilgeadam.edu.vkfmobil.json.ModelParserJSON;
import com.bilgeadam.edu.vkfmobil.listener.ServiceListener;
import com.bilgeadam.edu.vkfmobil.model.Base.BaseServiceErrorModel;
import com.bilgeadam.edu.vkfmobil.model.Base.BaseServiceInfoModel;
import com.bilgeadam.edu.vkfmobil.model.TabFragmentModel;
import com.bilgeadam.edu.vkfmobil.model.TiviBuModel;
import com.bilgeadam.edu.vkfmobil.model.WebServiceErrorModel;
import com.bilgeadam.edu.vkfmobil.sl.WebServiceSL;
import com.bilgeadam.edu.vkfmobil.view.adapter.MainPageFragmentsAdapter;
import com.bilgeadam.edu.vkfmobil.view.fragment.SecondFragment;
import com.bilgeadam.edu.vkfmobil.view.fragment.ThirdFragment;
import com.bilgeadam.edu.vkfmobil.view.fragment.TopFilmsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    LocationManger locationManger;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar =(Toolbar)findViewById(R.id.toolbar);
        drawerLayout =(DrawerLayout)findViewById(R.id.drawerLayout);
        tabLayout =(TabLayout)findViewById(R.id.tabLayout);
        viewPager =(ViewPager)findViewById(R.id.viewPager);


        initViews();

        setTabs();

        locationManger = new LocationManger(MainActivity.this);

        if(checkPermission(MainActivity.this))
        {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},1);
        }
        else
        {
            locationManger.startUsingGPS();
        }

    }
    private void initViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("VKF Mobil");
        getSupportActionBar().setSubtitle("Yanınızda");
    }

    private void setTabs() {
      List<TabFragmentModel> tabFragmentModels = new ArrayList<>();
      tabFragmentModels.add(new TabFragmentModel("IMDB TOP",new TopFilmsFragment()));
      tabFragmentModels.add(new TabFragmentModel("SECOND",new SecondFragment()));
      tabFragmentModels.add(new TabFragmentModel("THIRD",new ThirdFragment()));


        MainPageFragmentsAdapter mainPageFragmentsAdapter = new MainPageFragmentsAdapter(getSupportFragmentManager(),tabFragmentModels);
        viewPager.setAdapter(mainPageFragmentsAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    public static boolean checkPermission(final Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode)
        {
            case  1:{

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationManger.startUsingGPS();

                    // TODO: 30/07/2017 map operations/
                    //
                    // goMapActivity();

                } else {
                    Toast.makeText(MainActivity.this, "izin yok", Toast.LENGTH_SHORT).show();
                    // izin verilmedi
                }
                return;



            }

        }



    }
}
