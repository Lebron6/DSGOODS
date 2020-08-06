package com.ocean.dsgoods.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.ContractManagementAvtivity;
import com.ocean.dsgoods.activity.GoodsManagementActivity;
import com.ocean.dsgoods.activity.PackagingManagementActivity;
import com.ocean.dsgoods.activity.StaffManagementActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by James on 2020/6/29.
 * 个人中心
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.layout_contract_management)
    LinearLayout layoutContractManagement;
    @BindView(R.id.layout_goods_management)
    LinearLayout layoutGoodsManagement;
    @BindView(R.id.layout_packaging_management)
    LinearLayout layoutPackagingManagement;
    @BindView(R.id.layout_staff_management)
    LinearLayout layoutStaffManagement;
    @BindView(R.id.iv_service)
    ImageView ivService;
    @BindView(R.id.layout_call_service)
    RelativeLayout layoutCallService;
    @BindView(R.id.iv_e9)
    ImageView ivE9;
    @BindView(R.id.layout_about_e9)
    LinearLayout layoutAboutE9;
    @BindView(R.id.iv_sett)
    ImageView ivSett;
    @BindView(R.id.layout_sett)
    RelativeLayout layoutSett;
    Unbinder unbinder;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.layout_contract_management, R.id.layout_goods_management, R.id.layout_packaging_management, R.id.layout_staff_management, R.id.layout_call_service, R.id.layout_about_e9, R.id.layout_sett})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_contract_management:
                ContractManagementAvtivity.actionStart(getActivity());
                break;
            case R.id.layout_goods_management:
                GoodsManagementActivity.actionStart(getActivity());
                break;
            case R.id.layout_packaging_management:
                PackagingManagementActivity.actionStart(getActivity());
                break;
            case R.id.layout_staff_management:
                StaffManagementActivity.actionStart(getActivity());
                break;
            case R.id.layout_call_service:
                break;
            case R.id.layout_about_e9:
                break;
            case R.id.layout_sett:
                break;
        }
    }
}
