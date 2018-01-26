package com.bilgeadam.edu.vkfmobil.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bilgeadam.edu.vkfmobil.model.TabFragmentModel;
import com.bilgeadam.edu.vkfmobil.view.fragment.SecondFragment;
import com.bilgeadam.edu.vkfmobil.view.fragment.ThirdFragment;
import com.bilgeadam.edu.vkfmobil.view.fragment.TopFilmsFragment;

import java.util.List;

/**
 * Created by umutboz on 26,January,2018
 */

public class MainPageFragmentsAdapter extends FragmentStatePagerAdapter{

    List<TabFragmentModel> fragmentModels;

    public MainPageFragmentsAdapter(FragmentManager fm ,List<TabFragmentModel> fragmentModels) {
        super(fm);
        this.fragmentModels = fragmentModels;
    }

    @Override
    public Fragment getItem(int position) {
        TabFragmentModel fragmentModel = null;

        switch (position)
        {
            case  0:
            {
                fragmentModel = fragmentModels.get(0);
                return (TopFilmsFragment)fragmentModel.getFragment();
            }
            case  1:
            {
                fragmentModel = fragmentModels.get(1);
                return (SecondFragment)fragmentModel.getFragment();
            }
            case  2:
            {
                fragmentModel = fragmentModels.get(2);
                return (ThirdFragment)fragmentModel.getFragment();
            }
            default:
            {
                fragmentModel = fragmentModels.get(0);
                return (TopFilmsFragment)fragmentModel.getFragment();
            }


        }


    }

    @Override
    public int getCount() {
        return fragmentModels.size();
    }
}
