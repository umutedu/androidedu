package com.bilgeadam.edu.vkfmobil.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.ImageView;


import com.bilgeadam.edu.vkfmobil.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by umutboz on 26,January,2018
 */

public class FilmDetailActivity extends AppCompatActivity{

    String url;
    Toolbar toolbar;
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_detail_activity);
        imageView = (ImageView)findViewById(R.id.film_detail_poster_img);
        toolbar =(android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);


        initViews();

        if(getIntent().hasExtra("poster"))
        {
            url = getIntent().getStringExtra("poster");


            Picasso.with(this).load(url).noFade().into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    supportStartPostponedEnterTransition();
                }

                @Override
                public void onError() {
                    supportStartPostponedEnterTransition();
                }
            });
        }
    }


    private void initViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("VKF Mobil");
        getSupportActionBar().setSubtitle("Yanınızda");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            finishAfterTransition();
        }
        else
        {
            finish();
        }
        return true;
    }
}
