package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liaoinstan.springview.widget.SpringView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.ContarctStatusAdapter;
import com.ocean.dsgoods.adapter.GoodsManagementAdapter;
import com.ocean.dsgoods.tools.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by James on 2020/7/9.
 * 货物管理
 */
public class GoodsManagementActivity extends BaseActivity {

    @BindView(R.id.rv_goods_m)
    RecyclerView rvGoodsM;
    @BindView(R.id.sv_goods_m)
    SpringView svGoodsM;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, GoodsManagementActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_goods_management;
    }

    @Override
    protected void initViews() {
        GoodsManagementAdapter adapter = new GoodsManagementAdapter(this);
        RecyclerViewHelper.initRecyclerViewV(this, rvGoodsM, false, adapter);
        adapter.setOnItemClickLitener(new GoodsManagementAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
//                ContractDetailsActivity.actionStart(getActivity());
            }
        });
    }

    @Override
    protected void initDatas() {

    }

}
