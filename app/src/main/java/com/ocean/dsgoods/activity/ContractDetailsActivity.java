package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.dialog.RejectContractRemarksDialog;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.ContractDetails;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.TitleManger;
import com.ocean.dsgoods.tools.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    RelativeLayout layoutSeeQuotation;

    public static final String CO_ID = "co_id";
    @BindView(R.id.tv_reject_time)
    TextView tvRejectTime;
    @BindView(R.id.tc_reject_content)
    TextView tcRejectContent;
    @BindView(R.id.layout_t)
    LinearLayout layoutT;
    @BindView(R.id.layout_top)
    LinearLayout layoutTop;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.layout_bh)
    LinearLayout layoutBh;
    @BindView(R.id.layout_tg)
    LinearLayout layoutTg;
    @BindView(R.id.layout_xzht)
    LinearLayout layoutXzht;
    private String constract_sn;
    private String co_id;
    private String q_id;

    public static void actionStart(Context context, String co_id) {
        Intent intent = new Intent(context, ContractDetailsActivity.class);
        intent.putExtra(CO_ID, co_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("合同详情");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_contract_details;
    }

    @Override
    protected void initViews() {
        getContractDetailsData();
    }

    private void getContractDetailsData() {
        co_id = getIntent().getStringExtra(CO_ID);
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().constractCheckInfo()).contractDetails(PreferenceUtils.getInstance().getUserToken(), co_id).enqueue(new Callback<ApiResponse<ContractDetails>>() {
            @Override
            public void onResponse(Call<ApiResponse<ContractDetails>> call, Response<ApiResponse<ContractDetails>> response) {
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        tvContractNum.setText(response.body().getData().getConstract_sn());
                        tvCompanyName.setText(response.body().getData().getName());
                        tvContarctTermOfValidity.setText(response.body().getData().getStartTime() + "-" + response.body().getData().getEndTime());
                        tvReconciliationCycle.setText(response.body().getData().getCycle());
                        tvSettlementMethod.setText(response.body().getData().getP_name());
                        constract_sn=response.body().getData().getConstract_sn();
                        q_id=response.body().getData().getQ_id();
                        switch (response.body().getData().getStatus()) {
                            case "1"://完成
                                layoutT.setVisibility(View.GONE);
                                layoutBottom.setVisibility(View.GONE);
                                break;
                            case "2"://待确认
                                layoutT.setVisibility(View.GONE);
                                layoutBottom.setVisibility(View.VISIBLE);
                                break;
                            case "3"://进行中
                                layoutT.setVisibility(View.GONE);
                                layoutBottom.setVisibility(View.GONE);
                                break;
                            case "4"://驳回
                                layoutT.setVisibility(View.VISIBLE);
                                layoutBottom.setVisibility(View.GONE);
                                tvRejectTime.setText(response.body().getData().getRejectTime());
                                tcRejectContent.setText(response.body().getData().getReject_remarks());
                                break;
                        }
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<ContractDetails>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取合同详情数据失败");
            }
        });
    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.layout_see_quotation, R.id.layout_bh, R.id.layout_tg, R.id.layout_xzht})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_see_quotation:
                QuotationActivity.actionStart(this,q_id);
                break;
            case R.id.layout_bh:
                RejectContractRemarksDialog dialog=new RejectContractRemarksDialog(this,R.style.Theme_AppCompat_Dialog,constract_sn);
                dialog.show();
                break;
            case R.id.layout_tg:
                sure();
                break;
            case R.id.layout_xzht:
                break;
        }
    }

    private void sure() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().constractSure()).constractSure(PreferenceUtils.getInstance().getUserToken(),constract_sn).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body()!=null){
                    if (response.body().getCode()==1){
                        ToastUtil.showToast("通过成功");
                       finish();
                    }else{
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:通过失败");
            }
        });
    }
}
