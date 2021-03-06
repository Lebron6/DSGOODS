package com.ocean.dsgoods.fragment;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.NavPagerAdapter;
import com.ocean.dsgoods.view.NavitationScrollLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by James on 2020/6/30.
 * 我发运的-未完成
 */
public class NShippedFragment extends BaseFragment {
    @BindView(R.id.tab_bill)
    NavitationScrollLayout tabBill;
    @BindView(R.id.vp_content)
    ViewPager vpContent;

    public NShippedFragment() {
    }
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_bill_type;
    }

    @Override
    protected void initViews() {
        List<Fragment> fragments=new ArrayList<>();
        String[] titles = new String[6];
        titles[0]="全部";
        titles[1]="受理";
        titles[2]="驳回";
        titles[3]="分配";
        titles[4]="途中";
        titles[5]="回单确认";
        for (int i = 0; i <titles.length ; i++) {
            fragments.add(new ShoppedListFragment(titles[i]));
        }
        NavPagerAdapter viewPagerAdapter = new NavPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.setData(fragments);
        vpContent.setAdapter(viewPagerAdapter);
        tabBill.setViewPager(getActivity(), titles, vpContent, R.color.colorGray, R.color.colorMain, 14, 14, 17, true, R.color.colorMain, 0f, 15f, 15f, 60);
        tabBill.setNavLine(getActivity(), 2, R.color.colorMain);
    }

    @Override
    protected void initDatas() {

    }

}
