package com.ocean.dsgoods.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.ocean.dsgoods.fragment.TransportationManagementFragment;
import com.ocean.dsgoods.fragment.CheckWayBillFragment;
import com.ocean.dsgoods.fragment.HomeFragment;
import com.ocean.dsgoods.fragment.MineFragment;
import com.ocean.dsgoods.fragment.NuclearFreightFragment;


public class MainPageAdapter extends FragmentPagerAdapter {


    private HomeFragment homeFragment;
    private TransportationManagementFragment transportationManagementFragment;
    private CheckWayBillFragment checkWayBillFragment;
    private NuclearFreightFragment nuclearFreightFragment;
    private MineFragment mineFragment;

    public MainPageAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            if(homeFragment==null){
                homeFragment = new HomeFragment();
                return homeFragment;
            }else{
                return homeFragment;
            }
        }else if (position==1){
            if(transportationManagementFragment ==null){
                transportationManagementFragment = new TransportationManagementFragment();
                return transportationManagementFragment;
            }else{
                return transportationManagementFragment;
            }
        }else if(position==2){
            if(checkWayBillFragment==null){
                checkWayBillFragment = new CheckWayBillFragment();
                return checkWayBillFragment;
            }else{
                return checkWayBillFragment;
            }
        }else if(position==3){
            if(nuclearFreightFragment==null){
                nuclearFreightFragment = new NuclearFreightFragment();
                return nuclearFreightFragment;
            }else{
                return nuclearFreightFragment;
            }
        }else if(position==4){
            if(mineFragment==null){
                mineFragment = new MineFragment();
                return mineFragment;
            }else{
                return mineFragment;
            }
        }else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }


}
