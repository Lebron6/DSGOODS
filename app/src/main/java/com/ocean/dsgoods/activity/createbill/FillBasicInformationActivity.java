package com.ocean.dsgoods.activity.createbill;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.BaseActivity;
import com.ocean.dsgoods.activity.SelectAddressActivity;
import com.ocean.dsgoods.activity.SelectContractActivity;
import com.ocean.dsgoods.activity.SelectSupplierActivity;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.BillDetailsData;
import com.ocean.dsgoods.entity.Address;
import com.ocean.dsgoods.entity.BillUpData;
import com.ocean.dsgoods.entity.ContractData;
import com.ocean.dsgoods.entity.SortModelInfo;
import com.ocean.dsgoods.entity.SupplierList;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.TitleManger;
import com.ocean.dsgoods.tools.ToastUtil;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ocean.dsgoods.activity.SelectAddressActivity.CALLBACK;
import static com.ocean.dsgoods.activity.SelectAddressActivity.PARMS;

/**
 * Created by James on 2020/6/30.
 * 新建提货单-填写基本信息
 */
public class FillBasicInformationActivity extends BaseActivity {


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

    public static final String DP_ID = "DP_ID";
    @BindView(R.id.et_aorder)
    EditText etAorder;
    @BindView(R.id.et_ynum)
    EditText etYnum;
    private ApiResponse<BillDetailsData> body = new ApiResponse<>();

    public static void actionStart(Context context, String dp_id) {
        Intent intent = new Intent(context, FillBasicInformationActivity.class);
        intent.putExtra(DP_ID, dp_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("新建提货计划");
        manger.setBack();
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
        if (TextUtils.isEmpty(getIntent().getStringExtra(DP_ID))) {//新建

        } else {//编辑
            HttpUtil.createRequest(TAG, BaseUrl.getInstence().edit_info()).orderEditDetails(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(DP_ID)).enqueue(new Callback<ApiResponse<BillDetailsData>>() {
                @Override
                public void onResponse(Call<ApiResponse<BillDetailsData>> call, Response<ApiResponse<BillDetailsData>> response) {
                    body = response.body();
                    if (body != null) {
                        if (body.getCode() == 1) {
                            tvSupplier.setText(body.getData().getName());
                            tvContract.setText(body.getData().getConstract_sn());
                            tvNameT.setText(body.getData().getPickup_address().getLiaison());
                            tvPhoneT.setText(body.getData().getPickup_address().getTel());
                            tvCompanyT.setText(body.getData().getPickup_address().getName());
                            tvAddrT.setText(body.getData().getPickup_address().getAddress());
                            tvNameS.setText(body.getData().getCollect_address().getLiaison());
                            tvPhoneS.setText(body.getData().getCollect_address().getTel());
                            tvCompanyS.setText(body.getData().getCollect_address().getName());
                            tvAddrS.setText(body.getData().getCollect_address().getAddress());
                            tvTimeStart.setText(body.getData().getStartTime());
                            tvTimeEnd.setText(body.getData().getEndTime());
                            tvTimeArrive.setText(body.getData().getArrivalTime());
                            etAorder.setText(body.getData().getAorder());
                            etYnum.setText(body.getData().getYnum());
                            t_id = body.getData().getT_id();
                            co_id = body.getData().getCo_id();
                            sa_id = body.getData().getSa_id();
                            w_id = body.getData().getW_id();
                            startTime = body.getData().getStartTime();
                            endTime = body.getData().getEndTime();
                            arrveTime = body.getData().getArrivalTime();
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

    private String t_id = "";//供应商id
    private String w_id = "";//发货id
    private String co_id = "";//合同id
    private String sa_id = "";//收货id
    private int startYear, startMonth, startDay;
    private int endYear, endMonth, endDay;
    private int arrveYear, arrveMonth, arrveDay;
    private String startTime = "";
    private String endTime = "";
    private String arrveTime = "";

    @OnClick({R.id.layout_contract, R.id.layout_select_supplier, R.id.layout_t_addr, R.id.layout_s_addr, R.id.tv_time_start, R.id.tv_time_end, R.id.tv_time_arrive, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_contract:
                SelectContractActivity.actionStartForResult(this, t_id);
                break;
            case R.id.layout_select_supplier:
                SelectSupplierActivity.actionStartForResult(this);
                break;
            case R.id.layout_t_addr:
                SelectAddressActivity.actionStartForResult(this, t_id, "t");
                break;
            case R.id.layout_s_addr:
                SelectAddressActivity.actionStartForResult(this, t_id, "s");
                break;
            case R.id.tv_time_start:
                final Calendar ca = Calendar.getInstance();
                startYear = ca.get(Calendar.YEAR);
                startMonth = ca.get(Calendar.MONTH);
                startDay = ca.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(FillBasicInformationActivity.this, R.style.MyDatePickerDialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                startTime = (year + "-" + (monthOfYear + 1)
                                        + "-" + dayOfMonth);
                                tvTimeStart.setText(startTime);
                            }
                        }, startYear, startMonth, startDay).show();
                break;
            case R.id.tv_time_end:
                final Calendar cae = Calendar.getInstance();
                endYear = cae.get(Calendar.YEAR);
                endMonth = cae.get(Calendar.MONTH);
                endDay = cae.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(FillBasicInformationActivity.this, R.style.MyDatePickerDialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                endTime = (year + "-" + (monthOfYear + 1)
                                        + "-" + dayOfMonth);
                                tvTimeEnd.setText(endTime);
                            }
                        }, endYear, endMonth, endDay).show();
                break;
            case R.id.tv_time_arrive:
                final Calendar cac = Calendar.getInstance();
                arrveYear = cac.get(Calendar.YEAR);
                arrveMonth = cac.get(Calendar.MONTH);
                arrveDay = cac.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(FillBasicInformationActivity.this, R.style.MyDatePickerDialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                arrveTime = (year + "-" + (monthOfYear + 1)
                                        + "-" + dayOfMonth);
                                tvTimeArrive.setText(arrveTime);
                            }
                        }, arrveYear, arrveMonth, arrveDay).show();
                break;
            case R.id.btn_next:
                BillUpData upData = new BillUpData();
                if (!TextUtils.isEmpty(getIntent().getStringExtra(DP_ID))) {//编辑提货单，不可修改供应商
                    upData.setDp_id(getIntent().getStringExtra(DP_ID));
                } else {//新建提货单，需选择供应商
                    if (TextUtils.isEmpty(t_id)) {
                        ToastUtil.showToast("请选择供应商");
                        return;
                    }
                }
                if (TextUtils.isEmpty(co_id)) {
                    ToastUtil.showToast("请选择合同");
                    return;
                }

                if (TextUtils.isEmpty(sa_id)) {
                    ToastUtil.showToast("请选择收货地址");
                    return;
                }
                if (TextUtils.isEmpty(w_id)) {
                    ToastUtil.showToast("请选择发货地址");
                    return;
                }
                if (TextUtils.isEmpty(startTime)) {
                    ToastUtil.showToast("请选择提货开始时间");
                    return;
                }
                if (TextUtils.isEmpty(endTime)) {
                    ToastUtil.showToast("请选择提货结束时间");
                    return;
                }
                if (TextUtils.isEmpty(arrveTime)) {
                    ToastUtil.showToast("请选择要求到达时间");
                    return;
                }
//                if (TextUtils.isEmpty(etAorder.getText().toString())) {
//                    ToastUtil.showToast("请输入关联订单号");
//                    return;
//                }
                if (TextUtils.isEmpty(etYnum.getText().toString())) {
                    ToastUtil.showToast("请输入原始订单号");
                    return;
                }
                upData.setW_id(w_id);
                upData.setCo_id(co_id);
                upData.setSa_id(sa_id);
                upData.setT_id(t_id);
                upData.setStartTime(startTime);
                upData.setEndTime(endTime);
                upData.setArrivalTime(arrveTime);
                upData.setAorder(etAorder.getText().toString());
                upData.setYnum(etYnum.getText().toString());
                FillDeliveryListActivity.actionStart(this, new Gson().toJson(upData));
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case PARMS:
                if (resultCode == 1) {//提货地址
                    if (intent != null && intent.getExtras() != null) {
                        Bundle bundle = intent.getExtras();
                        String result = bundle.getString(CALLBACK);
                        Log.e("返回的提货地址", result);
                        if (!TextUtils.isEmpty(result)) {
                            Address.ListBean data = new Gson().fromJson(result, Address.ListBean.class);
                            w_id = data.getW_id();
                            tvNameT.setText(data.getLiaison());
                            tvPhoneT.setText(data.getTel());
                            tvCompanyT.setText(data.getName());
                            tvAddrT.setText(data.getAddress());
                        }
                    }

                } else if (resultCode == 2) {//收货地址
                    if (intent != null && intent.getExtras() != null) {
                        Bundle bundle = intent.getExtras();
                        String result = bundle.getString(CALLBACK);
                        Log.e("返回的收货地址", result);
                        if (!TextUtils.isEmpty(result)) {
                            Address.ListBean data = new Gson().fromJson(result, Address.ListBean.class);
                            sa_id = data.getSa_id();
                            tvNameS.setText(data.getLiaison());
                            tvPhoneS.setText(data.getTel());
                            tvCompanyS.setText(data.getName());
                            tvAddrS.setText(data.getAddress());
                        }

                    }
                } else if (resultCode == 3) {//选择供应商
                    if (intent != null && intent.getExtras() != null) {
                        Bundle bundle = intent.getExtras();
                        String result = bundle.getString(CALLBACK);
                        Log.e("返回的供应商信息", result);
                        if (!TextUtils.isEmpty(result)) {
                            SupplierList.ListBean sortModelInfo = new Gson().fromJson(result, SupplierList.ListBean.class);
                            t_id = sortModelInfo.getT_id();
                            tvSupplier.setText(sortModelInfo.getName());
                        }
                    }
                } else if (resultCode == 4) {//选择合同信息
                    if (intent != null && intent.getExtras() != null) {
                        Bundle bundle = intent.getExtras();
                        String result = bundle.getString(CALLBACK);
                        Log.e("返回的合同信息", result);
                        if (!TextUtils.isEmpty(result)) {
                            ContractData.ListBean bean = new Gson().fromJson(result, ContractData.ListBean.class);
                            co_id = bean.getCo_id();
                            tvContract.setText(bean.getConstract_sn());
                        }
                    }
                }

                break;
        }
    }
}
