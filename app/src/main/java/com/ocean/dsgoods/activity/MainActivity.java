package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.MainPageAdapter;
import com.ocean.dsgoods.view.CustomViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.vp_content)
    CustomViewPager vpContent;
    @BindView(R.id.home)
    RadioButton home;
    @BindView(R.id.bill_of_lading)
    RadioButton billOfLading;
    @BindView(R.id.check_waybill)
    RadioButton checkWaybill;
    @BindView(R.id.verify_freight)
    RadioButton verifyFreight;
    @BindView(R.id.mine)
    RadioButton mine;
    @BindView(R.id.rg_group)
    RadioGroup rgGroup;
    private MainPageAdapter adapter;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        adapter = new MainPageAdapter(getSupportFragmentManager());
        vpContent.setAdapter(adapter);
//        vpContent.setOffscreenPageLimit(2);
        vpContent.setOnPageChangeListener(onPagerChangerListener);
        rgGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        home.setChecked(true);
    }

    ViewPager.OnPageChangeListener onPagerChangerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    home.setChecked(true);
                    break;
                case 1:
                    billOfLading.setChecked(true);
                    break;
                case 2:
                    checkWaybill.setChecked(true);
                    break;
                case 3:
                    verifyFreight.setChecked(true);
                    break;
                case 4:
                    mine.setChecked(true);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            setTabSelection(checkedId);
        }
    };

    private void setTabSelection(int checkedId) {
        switch (checkedId) {
            case R.id.home:
                vpContent.setCurrentItem(0, false);
                break;
            case R.id.bill_of_lading:
                vpContent.setCurrentItem(1, false);
                break;
            case R.id.check_waybill:
                vpContent.setCurrentItem(2, false);
                break;
            case R.id.verify_freight:
                vpContent.setCurrentItem(3, false);
                break;
            case R.id.mine:
                vpContent.setCurrentItem(4, false);
                break;
        }
    }
    @Override
    protected void initDatas() {

    }

}
