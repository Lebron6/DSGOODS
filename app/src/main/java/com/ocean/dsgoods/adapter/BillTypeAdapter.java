package com.ocean.dsgoods.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class BillTypeAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public BillTypeAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<Fragment> fragments){
        this.fragments=fragments;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
}
