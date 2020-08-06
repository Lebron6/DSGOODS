package com.ocean.dsgoods.activity.createwaybill;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.BaseActivity;
import com.ocean.dsgoods.activity.SelectAddressActivity;
import com.ocean.dsgoods.activity.SelectContractActivity;
import com.ocean.dsgoods.activity.SelectSupplierActivity;
import com.ocean.dsgoods.activity.createbill.FillDeliveryListActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by James on 2020/6/30.
 * 新建运单-填写基本信息
 */
public class WayBillFillBasicInformationActivity extends BaseActivity {


    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.txt_one)
    TextView txtOne;
    @BindView(R.id.txt_two)
    TextView txtTwo;
    @BindView(R.id.txt_three)
    TextView txtThree;
    @BindView(R.id.tv_c)
    TextView tvC;
    @BindView(R.id.txt_supplier)
    TextView txtSupplier;
    @BindView(R.id.tv_supplier)
    TextView tvSupplier;
    @BindView(R.id.iv_down)
    ImageView ivDown;
    @BindView(R.id.layout_select_supplier)
    RelativeLayout layoutSelectSupplier;
    @BindView(R.id.txt_contract)
    TextView txtContract;
    @BindView(R.id.tv_contract)
    TextView tvContract;
    @BindView(R.id.iv_down_o)
    ImageView ivDownO;
    @BindView(R.id.layout_contract)
    LinearLayout layoutContract;
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
    @BindView(R.id.tv_time_start)
    TextView tvTimeStart;
    @BindView(R.id.tv_time_end)
    TextView tvTimeEnd;
    @BindView(R.id.layout_time_t)
    LinearLayout layoutTimeT;
    @BindView(R.id.view_s_line)
    View viewSLine;
    @BindView(R.id.layout_chose_t)
    LinearLayout layoutChoseT;
    @BindView(R.id.tv_time_arrive)
    TextView tvTimeArrive;
    @BindView(R.id.layout_time)
    LinearLayout layoutTime;
    @BindView(R.id.view_order)
    View viewOrder;
    @BindView(R.id.txt_order)
    TextView txtOrder;
    @BindView(R.id.layout_order)
    RelativeLayout layoutOrder;
    @BindView(R.id.view_num)
    View viewNum;
    @BindView(R.id.txt_order_num)
    TextView txtOrderNum;
    @BindView(R.id.layout_num)
    RelativeLayout layoutNum;
    @BindView(R.id.layout_bottom)
    RelativeLayout layoutBottom;
    @BindView(R.id.btn_next)
    Button btnNext;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, WayBillFillBasicInformationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_fill_basic_information;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.layout_contract, R.id.layout_select_supplier, R.id.layout_t_addr, R.id.layout_s_addr, R.id.tv_time_start, R.id.tv_time_end, R.id.tv_time_arrive, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_contract:
                SelectContractActivity.actionStart(this);
                break;
            case R.id.layout_select_supplier:
                SelectSupplierActivity.actionStart(this);
                break;
            case R.id.layout_t_addr:
                SelectAddressActivity.actionStart(this);
                break;
            case R.id.layout_s_addr:
                SelectAddressActivity.actionStart(this);
                break;
            case R.id.tv_time_start:
                break;
            case R.id.tv_time_end:
                break;
            case R.id.tv_time_arrive:
                break;
            case R.id.btn_next:
                FillDeliveryListActivity.actionStart(this);
                break;
        }
    }


}
