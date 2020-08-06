package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.SelectAddressAdapter;
import com.ocean.dsgoods.adapter.SelectGoodsAdapter;
import com.ocean.dsgoods.tools.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by James on 2020/7/1.
 * 选择货物
 */
public class SelectGoodsActivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SelectGoodsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_select_goods;
    }

    @Override
    protected void initViews() {
        SelectGoodsAdapter adapter = new SelectGoodsAdapter(this);
        adapter.setOnItemClickLitener(onItemClickLitener);
        RecyclerViewHelper.initRecyclerViewV(this, rvGoods, false, adapter);
    }

    SelectGoodsAdapter.OnItemClickLitener onItemClickLitener = new SelectGoodsAdapter.OnItemClickLitener() {
        @Override
        public void onItemClick(View view, int position) {
//            ContractDetailsActivity.actionStart(SelectContractActivity.this);
        }
    };

    @Override
    protected void initDatas() {

    }
}
