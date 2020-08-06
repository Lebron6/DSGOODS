package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by James on 2020/7/1.
 * 合同详情
 */
public class ContractDetailsActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_contract_num)
    TextView tvContractNum;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_contarct_term_of_validity)
    TextView tvContarctTermOfValidity;
    @BindView(R.id.tv_reconciliation_cycle)
    TextView tvReconciliationCycle;
    @BindView(R.id.tv_settlement_method)
    TextView tvSettlementMethod;
    @BindView(R.id.btn_see_quotation)
    TextView btnSeeQuotation;
    @BindView(R.id.layout_see_quotation)
    LinearLayout layoutSeeQuotation;
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ContractDetailsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_contract_details;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }


    @OnClick(R.id.layout_see_quotation)
    public void onViewClicked() {
    }
}
