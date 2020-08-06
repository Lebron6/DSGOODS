package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.NavPagerAdapter;
import com.ocean.dsgoods.fragment.ReturnBoxFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by James on 2020/7/9.
 * 返箱管理
 */
public class ReturnBoxAvtivity extends BaseActivity {


    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rb_all)
    RadioButton rbAll;
    @BindView(R.id.rb_using)
    RadioButton rbUsing;
    @BindView(R.id.rb_null)
    RadioButton rbNull;
    @BindView(R.id.rb_wait)
    RadioButton rbWait;
    @BindView(R.id.rg_contract)
    RadioGroup rgContract;
    @BindView(R.id.vp_return_box)
    ViewPager vpReturnBox;
    @BindView(R.id.btn_Return_empty)
    Button btnReturnEmpty;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ReturnBoxAvtivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_return_box;
    }

    @Override
    protected void initViews() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ReturnBoxFragment(0));
        fragments.add(new ReturnBoxFragment(1));
        fragments.add(new ReturnBoxFragment(2));
        fragments.add(new ReturnBoxFragment(3));

        NavPagerAdapter viewPagerAdapter = new NavPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.setData(fragments);
        vpReturnBox.setAdapter(viewPagerAdapter);
        rgContract.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_all:
                        vpReturnBox.setCurrentItem(0, false);
                        break;
                    case R.id.rb_using:
                        vpReturnBox.setCurrentItem(1, false);
                        break;
                    case R.id.rb_null:
                        vpReturnBox.setCurrentItem(2, false);
                        break;
                    case R.id.rb_wait:
                        vpReturnBox.setCurrentItem(3, false);
                        break;
                }
            }
        });
        vpReturnBox.setOnPageChangeListener(onPagerChangerListener);
        rbAll.setChecked(true);
    }

    ViewPager.OnPageChangeListener onPagerChangerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    rbAll.setChecked(true);
                    break;
                case 1:
                    rbUsing.setChecked(true);
                    break;
                case 2:
                    rbNull.setChecked(true);
                    break;
                case 3:
                    rbWait.setChecked(true);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.btn_Return_empty)
    public void onViewClicked() {
        ChangeVacancyStatusActivity.actionStart(this);
    }
}
