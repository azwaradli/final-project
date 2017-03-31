package com.example.azwar.finalproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Azwar on 3/31/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                InformasiProdukFragment informasiTab = new InformasiProdukFragment();
                return informasiTab;
            case 1:
                UlasanProdukFragment ulasanTab = new UlasanProdukFragment();
                return ulasanTab;
            case 2:
                TanyaProdukFragment tanyaTab = new TanyaProdukFragment();
                return tanyaTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
