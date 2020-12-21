package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.createbill.FillBasicInformationActivity;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.dialog.RejectBillRemarksDialog;
import com.ocean.dsgoods.dialog.RejectContractRemarksDialog;
import com.ocean.dsgoods.dialog.ToVoidBillRemarksDialog;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.BillDetailsData;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.TitleManger;
import com.ocean.dsgoods.tools.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/7/3.
 * 提货单详情
 */
public class BillDetailsActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_reject_time)
    TextView tvRejectTime;
    @BindView(R.id.tc_reject_content)
    TextView tcRejectContent;
    @BindView(R.id.btn_updata_bill)
    Button btnUpdataBill;
    @BindView(R.id.btn_to_void)
    Button btnToVoid;
    @BindView(R.id.layout_t)
    LinearLayout layoutT;
    @BindView(R.id.tv_bill_num)
    TextView tvBillNum;
    @BindView(R.id.iv_copy)
    ImageView ivCopy;
    @BindView(R.id.tv_promiser)
    TextView tvPromiser;
    @BindView(R.id.tv_tv_promiser_num)
    TextView tvTvPromiserNum;
    @BindView(R.id.layout_top)
    LinearLayout layoutTop;
    @BindView(R.id.txt_t)
    TextView txtT;
    @BindView(R.id.txt_s)
    TextView txtS;
    @BindView(R.id.layout_line)
    RelativeLayout layoutLine;
    @BindView(R.id.tv_name_t)
    TextView tvNameT;
    @BindView(R.id.tv_phone_t)
    TextView tvPhoneT;
    @BindView(R.id.tv_addr_t)
    TextView tvAddrT;
    @BindView(R.id.tv_company_t)
    TextView tvCompanyT;
    @BindView(R.id.layout_t_info)
    LinearLayout layoutTInfo;
    @BindView(R.id.line_t)
    View lineT;
    @BindView(R.id.layout_t_addr)
    LinearLayout layoutTAddr;
    @BindView(R.id.tv_name_s)
    TextView tvNameS;
    @BindView(R.id.tv_phone_s)
    TextView tvPhoneS;
    @BindView(R.id.tv_addr_s)
    TextView tvAddrS;
    @BindView(R.id.tv_company_s)
    TextView tvCompanyS;
    @BindView(R.id.layout_s_info)
    LinearLayout layoutSInfo;
    @BindView(R.id.line_s)
    View lineS;
    @BindView(R.id.layout_s_addr)
    LinearLayout layoutSAddr;
    @BindView(R.id.layout_addr)
    RelativeLayout layoutAddr;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_delivery_time)
    TextView tvDeliveryTime;
    @BindView(R.id.tv_delivery_time2)
    TextView tvDeliveryTime2;
    @BindView(R.id.layout_time)
    RelativeLayout layoutTime;
    @BindView(R.id.view_line_2)
    View viewLine2;
    @BindView(R.id.tv_arrival_time)
    TextView tvArrivalTime;
    @BindView(R.id.tv_service_type)
    TextView tvServiceType;
    @BindView(R.id.tv_transport_prescription)
    TextView tvTransportPrescription;
    @BindView(R.id.tv_requirements)
    TextView tvRequirements;
    @BindView(R.id.tv_weigth)
    TextView tvWeigth;
    @BindView(R.id.tv_volume)
    TextView tvVolume;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_file)
    TextView tvFile;
    @BindView(R.id.tv_remarks)
    TextView tvRemarks;
    @BindView(R.id.layout_bottom)
    RelativeLayout layoutBottom;
    @BindView(R.id.layout_button)
    LinearLayout layoutButton;
    @BindView(R.id.layout_see_list)
    LinearLayout layoutSeeList;
    @BindView(R.id.layout_see_map)
    LinearLayout layoutSeeMap;
    @BindView(R.id.layout_reject)
    LinearLayout layoutReject;
    @BindView(R.id.layout_accept)
    LinearLayout layoutAccept;
    private String dp_id;

    public static final String DP_ID = "DP_ID";

    public static void actionStart(Context context, String dp_id) {
        Intent intent = new Intent(context, BillDetailsActivity.class);
        intent.putExtra(DP_ID, dp_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("提货单详情");
        manger.setBack();
        getDetailsData();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_bill_details;
    }

    @Override
    protected void initViews() {

    }

    private void getDetailsData() {
        dp_id = getIntent().getStringExtra(DP_ID);
        if (TextUtils.isEmpty(dp_id)) {//

        } else {
            HttpUtil.createRequest(TAG, BaseUrl.getInstence().take_info()).orderDetails(PreferenceUtils.getInstance().getUserToken(), dp_id).enqueue(new Callback<ApiResponse<BillDetailsData>>() {
                @Override
                public void onResponse(Call<ApiResponse<BillDetailsData>> call, Response<ApiResponse<BillDetailsData>> response) {
                    ApiResponse<BillDetailsData> body = response.body();
                    if (body != null) {
                        if (body.getCode() == 1) {
                            tvBillNum.setText(body.getData().getSerial_num());
                            tvPromiser.setText(body.getData().getName());
                            tvTvPromiserNum.setText(body.getData().getConstract_sn());
                            tvNameT.setText(body.getData().getPickup_address().getLiaison());
                            tvPhoneT.setText(body.getData().getPickup_address().getTel());
                            tvCompanyT.setText(body.getData().getPickup_address().getName());
                            tvAddrT.setText(body.getData().getPickup_address().getAddress());
                            tvNameS.setText(body.getData().getCollect_address().getLiaison());
                            tvPhoneS.setText(body.getData().getCollect_address().getTel());
                            tvCompanyS.setText(body.getData().getCollect_address().getName());
                            tvAddrS.setText(body.getData().getCollect_address().getAddress());
                            tvDeliveryTime.setText(body.getData().getStartTime());
                            tvDeliveryTime2.setText(body.getData().getEndTime());
                            tvServiceType.setText(body.getData().getDelivery());
                            tvTransportPrescription.setText(body.getData().getTsTime());
                            tvRequirements.setText(body.getData().getNeedCar());
                            tvWeigth.setText(body.getData().getAllWeight());
                            tvVolume.setText(body.getData().getAllVolume());
                            tvPrice.setText(body.getData().getPrice());
                            tvFile.setText(body.getData().getFileInfo());
                            tvRemarks.setText(body.getData().getRemarks());
                            tvArrivalTime.setText(body.getData().getArrivalTime());
                            if (response.body().getData().getStatus().equals("1")) {//新建状态
                                if (response.body().getData().getDp_type().equals("1")) {//3PL发布
                                    layoutReject.setVisibility(View.VISIBLE);
                                    layoutSeeMap.setVisibility(View.GONE);
                                    layoutAccept.setVisibility(View.VISIBLE);
                                }
                            }

                            if (response.body().getData().getStatus().equals("7")) {//驳回状态
                                layoutButton.setVisibility(View.GONE);
                                if (response.body().getData().getDp_type().equals("2")) {//我发起的
                                    layoutT.setVisibility(View.VISIBLE);
                                    tvRejectTime.setText(response.body().getData().getOperationTime());
                                    tcRejectContent.setText(response.body().getData().getReject_remarks());
                                } else {//3pl发起的
                                    layoutT.setVisibility(View.GONE);
                                    tvRejectTime.setText(response.body().getData().getOperationTime());
                                    tcRejectContent.setText(response.body().getData().getReject_remarks());
                                }
                            }
                        } else {
                            ToastUtil.showToast(body.getMsg());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<BillDetailsData>> call, Throwable t) {
                    ToastUtil.showToast("网络异常:获取详情信息失败");
                }
            });
        }
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.btn_updata_bill, R.id.btn_to_void, R.id.iv_copy, R.id.layout_reject, R.id.layout_accept, R.id.layout_see_list, R.id.layout_see_map})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_updata_bill://修改提货单
                FillBasicInformationActivity.actionStart(this, dp_id);
                break;
            case R.id.btn_to_void://作废
                ToVoidBillRemarksDialog voidBillRemarksDialog = new ToVoidBillRemarksDialog(this, R.style.Theme_AppCompat_Dialog, dp_id);
                voidBillRemarksDialog.show();
                break;
            case R.id.iv_copy:
                break;
            case R.id.layout_reject://驳回
                RejectBillRemarksDialog dialog = new RejectBillRemarksDialog(this, R.style.Theme_AppCompat_Dialog, dp_id);
                dialog.show();
                break;
            case R.id.layout_accept://通过
                accept();
                break;
            case R.id.layout_see_list://查看货物清单
                break;
            case R.id.layout_see_map:
                BillCarInfoActivity.actionStart(this,dp_id);
                break;
        }
    }


    //通过
    private void accept() {
        HttpUtil.createRequest(BaseUrl.getInstence().pass_order()).orderSure(PreferenceUtils.getInstance().getUserToken(), dp_id).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("提单通过成功");
                    finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:提单通过失败");
            }
        });
    }

}
