package com.example.azwar.finalproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Azwar on 4/4/2017.
 */

public class ArisanPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ArisanPagerAdapter(FragmentManager fm, int NumOfTabs){
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ArisanSayaFragment arisanSayaTab = new ArisanSayaFragment();
                return arisanSayaTab;
            case 1:
                BinaanSayaFragment binaanSayaTab = new BinaanSayaFragment();
                return binaanSayaTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
