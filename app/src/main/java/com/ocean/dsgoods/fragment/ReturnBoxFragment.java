package com.ocean.dsgoods.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liaoinstan.springview.widget.SpringView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.ContractDetailsActivity;
import com.ocean.dsgoods.adapter.ContarctStatusAdapter;
import com.ocean.dsgoods.adapter.ReturnBoxAdapter;
import com.ocean.dsgoods.tools.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by James on 2020/7/9.
 * 返箱管理
 */
public class ReturnBoxFragment extends BaseFragment {
    @BindView(R.id.rv_return)
    RecyclerView rvReturn;
    @BindView(R.id.sv_return)
    SpringView svReturn;
    Unbinder unbinder;
    private int type;
    ContarctStatusAdapter adapter;

    public ReturnBoxFragment() {
    }

    @SuppressLint("ValidFragment")
    public ReturnBoxFragment(int type) {
        this.type = type;
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_return_box;
    }

    @Override
    protected void initViews() {
        ReturnBoxAdapter adapter = new ReturnBoxAdapter(getActivity());
        RecyclerViewHelper.initRecyclerViewV(getActivity(), rvReturn, false, adapter);
        adapter.setOnItemClickLitener(new ReturnBoxAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                ContractDetailsActivity.actionStart(getActivity());
            }
        });
    }

    @Override
    protected void initDatas() {

    }

}
