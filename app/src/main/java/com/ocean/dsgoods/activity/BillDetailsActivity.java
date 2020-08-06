package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.btn_see_list)
    Button btnSeeList;
    @BindView(R.id.btn_see_car_info)
    Button btnSeeCarInfo;
    @BindView(R.id.layout_button)
    LinearLayout layoutButton;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, BillDetailsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_bill_details;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.btn_updata_bill, R.id.btn_to_void, R.id.iv_copy, R.id.btn_see_list, R.id.btn_see_car_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_updata_bill:
                break;
            case R.id.btn_to_void:
                break;
            case R.id.iv_copy:
                break;
            case R.id.btn_see_list:
                break;
            case R.id.btn_see_car_info:
                BillCarInfoActivity.actionStart(this);
                break;
        }
    }
}
