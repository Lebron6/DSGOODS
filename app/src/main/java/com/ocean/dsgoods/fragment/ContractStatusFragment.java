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
import com.ocean.dsgoods.adapter.SelectContractAdapter;
import com.ocean.dsgoods.tools.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by James on 2020/7/9.
 * 合同管理
 */
public class ContractStatusFragment extends BaseFragment {
    @BindView(R.id.rv_contarct)
    RecyclerView rvContarct;
    @BindView(R.id.sv_contarct)
    SpringView svContarct;
    Unbinder unbinder;
    private int type;
    ContarctStatusAdapter adapter;

    public ContractStatusFragment() {
    }

    @SuppressLint("ValidFragment")
    public ContractStatusFragment(int type) {
        this.type = type;
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_contarct_status;
    }

    @Override
    protected void initViews() {
        ContarctStatusAdapter adapter = new ContarctStatusAdapter(getActivity());
        RecyclerViewHelper.initRecyclerViewV(getActivity(), rvContarct, false, adapter);
        adapter.setOnItemClickLitener(new ContarctStatusAdapter.OnItemClickLitener() {
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
