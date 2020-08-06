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
import com.ocean.dsgoods.adapter.SelectContractAdapter;
import com.ocean.dsgoods.tools.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by James on 2020/7/1.
 */
public class SelectAddressActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rv_address)
    RecyclerView rvAddress;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SelectAddressActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_select_address;
    }

    @Override
    protected void initViews() {
        SelectAddressAdapter adapter = new SelectAddressAdapter(this);
        adapter.setOnItemClickLitener(onItemClickLitener);
        RecyclerViewHelper.initRecyclerViewV(this, rvAddress, false, adapter);
    }

    SelectAddressAdapter.OnItemClickLitener onItemClickLitener = new SelectAddressAdapter.OnItemClickLitener() {
        @Override
        public void onItemClick(View view, int position) {
//            ContractDetailsActivity.actionStart(SelectContractActivity.this);
        }
    };

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
