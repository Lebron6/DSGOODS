package com.ocean.dsgoods.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.GoodsManagementActivity;
import com.ocean.dsgoods.adapter.EarlyWarningAdapter;
import com.ocean.dsgoods.adapter.NavPagerAdapter;
import com.ocean.dsgoods.adapter.WaitingProcessingAdapter;
import com.ocean.dsgoods.fragment.dynamic.PackagingFragment;
import com.ocean.dsgoods.fragment.dynamic.TransportFragment;
import com.ocean.dsgoods.fragment.dynamic.WarehouseFragment;
import com.ocean.dsgoods.tools.RecyclerViewHelper;
import com.ocean.dsgoods.view.NavitationLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by James on 2020/6/29.
 * 首页
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.layout_waybill_inquiry)
    LinearLayout layoutWaybillInquiry;
    @BindView(R.id.layout_goods_management)
    LinearLayout layoutGoodsManagement;
    @BindView(R.id.layout_inventory_query)
    LinearLayout layoutInventoryQuery;
    @BindView(R.id.layout_system_board)
    LinearLayout layoutSystemBoard;
    @BindView(R.id.rv_early)
    RecyclerView rvEarly;
    @BindView(R.id.layout_pol)
    LinearLayout layoutPol;
    @BindView(R.id.rv_wait)
    RecyclerView rvWait;
    @BindView(R.id.layout_wait)
    LinearLayout layoutWait;
    @BindView(R.id.layout_tips)
    LinearLayout layoutTips;
    @BindView(R.id.nav_tap)
    NavitationLayout navTap;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;
    @BindView(R.id.layout_frag)
    LinearLayout layoutFrag;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        EarlyWarningAdapter adapter=new EarlyWarningAdapter(getActivity());
        RecyclerViewHelper.initRecyclerViewH(getActivity(),rvEarly,adapter);
        WaitingProcessingAdapter waitingProcessingAdapter=new WaitingProcessingAdapter(getActivity());
        RecyclerViewHelper.initRecyclerViewG(getActivity(),rvWait,waitingProcessingAdapter,2);
        String[] strings = {"运输动态", "仓储动态","包装动态"};
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TransportFragment());
        fragments.add(new WarehouseFragment());
        fragments.add(new PackagingFragment());
        NavPagerAdapter viewPagerAdapter2 = new NavPagerAdapter(getChildFragmentManager());
        viewPagerAdapter2.setData( fragments);
        vpFragment.setAdapter(viewPagerAdapter2);
        vpFragment.setCurrentItem(0);
        navTap.setViewPager(getActivity(), strings, vpFragment, R.color.colorMainBlack, R.color.colorMain, 14, 16, 0, 55,true);
        navTap.setBgLine(getActivity(), 0, R.color.colorMain);
        navTap.setNavLine(getActivity(), 2, R.color.colorWhite,0);
    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.iv_scan, R.id.layout_waybill_inquiry, R.id.layout_goods_management, R.id.layout_inventory_query, R.id.layout_system_board, R.id.layout_tips})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                break;
            case R.id.layout_waybill_inquiry:
                break;
            case R.id.layout_goods_management:
                GoodsManagementActivity.actionStart(getActivity());
                break;
            case R.id.layout_inventory_query:
                break;
            case R.id.layout_system_board:
                break;
            case R.id.layout_tips:
                break;
        }
    }
}
