package com.bilgeadam.edu.vkfmobil.model;

import android.support.v4.app.Fragment;

/**
 * Created by umutboz on 26,January,2018
 */

public class TabFragmentModel {


    private String title;
    private Fragment fragment;

    public TabFragmentModel(String title, Fragment fragment)
    {
        this.setTitle(title);
        this.setFragment(fragment);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
