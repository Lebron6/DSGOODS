package com.ocean.dsgoods.activity.createbill;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.BaseActivity;
import com.ocean.dsgoods.activity.CommitBillSuccessActivity;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.callback.OnTypeSelectImp;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.BillDetailsData;
import com.ocean.dsgoods.entity.BillUpData;
import com.ocean.dsgoods.entity.TransportationType;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.TitleManger;
import com.ocean.dsgoods.tools.ToastUtil;
import com.ocean.dsgoods.view.TypeSelectWindow;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ocean.dsgoods.activity.createbill.FillDeliveryListActivity.BILL_UP_DATA;

/**
 * Created by James on 2020/6/30.
 * 新建提货单-设置运输要求
 */
public class SetTransportationActivity extends BaseActivity {


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
    @BindView(R.id.tv_service_type)
    TextView tvServiceType;
    @BindView(R.id.layout_service_type)
    LinearLayout layoutServiceType;
    @BindView(R.id.tv_transport_time)
    TextView tvTransportTime;
    @BindView(R.id.layout_transport_time)
    LinearLayout layoutTransportTime;
    @BindView(R.id.tv_transport_prescription)
    TextView tvTransportPrescription;
    @BindView(R.id.layout_transport_prescription)
    LinearLayout layoutTransportPrescription;
    @BindView(R.id.tv_car_requirement)
    TextView tvCarRequirement;
    @BindView(R.id.layout_car_requirement)
    LinearLayout layoutCarRequirement;
    @BindView(R.id.txt_weigth)
    TextView txtWeigth;
    @BindView(R.id.et_weigth)
    EditText etWeigth;
    @BindView(R.id.txt_volume)
    TextView txtVolume;
    @BindView(R.id.et_volume)
    EditText etVolume;
    @BindView(R.id.txt_insurance_value)
    TextView txtInsuranceValue;
    @BindView(R.id.et_insurance_value)
    EditText etInsuranceValue;
    @BindView(R.id.layout_title)
    LinearLayout layoutTitle;
    @BindView(R.id.et_fils)
    EditText etFils;
    @BindView(R.id.layout_remarks)
    LinearLayout layoutRemarks;
    @BindView(R.id.et_remarks)
    EditText etRemarks;
    @BindView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    @BindView(R.id.btn_last)
    Button btnLast;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.layout_button)
    LinearLayout layoutButton;
    @BindView(R.id.tv_settleSty)
    TextView tvSettleSty;
    @BindView(R.id.layout_settleSty)
    LinearLayout layoutSettleSty;
    private BillUpData billUpData;
    private TransportationType transportationType;

    private ArrayAdapter deliveryAdapter;
    private List<String> delivery = new ArrayList<>();
    private ArrayAdapter tsTimeAdapter;
    private List<String> tsTime = new ArrayList<>();
    private ArrayAdapter transportAdapter;
    private List<String> transport = new ArrayList<>();
    private ArrayAdapter needCarAdapter;
    private List<String> needCar = new ArrayList<>();
    private ArrayAdapter settleStyAdapter;
    private List<String> settleSty = new ArrayList<>();

    private TypeSelectWindow deliverWindow;
    private TypeSelectWindow tsTimeWindow;
    private TypeSelectWindow transportWindow;
    private TypeSelectWindow needCarWindow;
    private TypeSelectWindow settleStyWindow;

    public static void actionStart(Context context, String upBillData) {
        Intent intent = new Intent(context, SetTransportationActivity.class);
        intent.putExtra(BILL_UP_DATA, upBillData);
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
        return R.layout.activity_set_transportation;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        getTranspotationDara();

    }

    private void getTranspotationDara() {
        HttpUtil.createRequest(BaseUrl.getInstence().config()).trConfig(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<TransportationType>() {
            @Override
            public void onResponse(Call<TransportationType> call, Response<TransportationType> response) {
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        transportationType = response.body();
                        getData();
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }
            @Override
            public void onFailure(Call<TransportationType> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取运输配置信息失败");
            }
        });
    }

    private void getData() {
        billUpData = new Gson().fromJson(getIntent().getStringExtra(BILL_UP_DATA), BillUpData.class);
        if (TextUtils.isEmpty(billUpData.getDp_id())) {
            //新建提单计划
            for (int i = 0; i < transportationType.getData().getDelivery().size(); i++) {
                delivery.add(transportationType.getData().getDelivery().get(i).getName());
            }
            for (int i = 0; i < transportationType.getData().getTsTime().size(); i++) {
                tsTime.add(transportationType.getData().getTsTime().get(i).getName());
            }
            for (int i = 0; i < transportationType.getData().getTransport_mode().size(); i++) {
                transport.add(transportationType.getData().getTransport_mode().get(i).getName());
            }
            for (int i = 0; i < transportationType.getData().getCar_require().size(); i++) {
                needCar.add(transportationType.getData().getCar_require().get(i).getName());
            }
            for (int i = 0; i < transportationType.getData().getSettleSty().size(); i++) {
                settleSty.add(transportationType.getData().getSettleSty().get(i).getName());
            }
        } else {
            HttpUtil.createRequest(TAG, BaseUrl.getInstence().edit_info()).orderEditDetails(PreferenceUtils.getInstance().getUserToken(), billUpData.getDp_id()).enqueue(new Callback<ApiResponse<BillDetailsData>>() {
                @Override
                public void onResponse(Call<ApiResponse<BillDetailsData>> call, Response<ApiResponse<BillDetailsData>> response) {
                    if (response.body() != null) {
                        if (response.body().getCode() == 1) {
                            for (int i = 0; i < transportationType.getData().getDelivery().size(); i++) {
                                if (transportationType.getData().getDelivery().get(i).getId().equals(response.body().getData().getDelivery())) {
                                    tvServiceType.setText(transportationType.getData().getDelivery().get(i).getName());
                                }
                                delivery.add(transportationType.getData().getDelivery().get(i).getName());
                            }
                            for (int i = 0; i < transportationType.getData().getTsTime().size(); i++) {
                                if (transportationType.getData().getTsTime().get(i).getId().equals(response.body().getData().getTsTime())) {
                                    tvTransportTime.setText(transportationType.getData().getTsTime().get(i).getName());
                                }
                                tsTime.add(transportationType.getData().getTsTime().get(i).getName());
                            }
                            for (int i = 0; i < transportationType.getData().getTransport_mode().size(); i++) {
                                if (transportationType.getData().getTransport_mode().get(i).getId().equals(response.body().getData().getTransport())) {
                                    tvTransportPrescription.setText(transportationType.getData().getTransport_mode().get(i).getName());
                                }
                                transport.add(transportationType.getData().getTransport_mode().get(i).getName());
                            }
                            for (int i = 0; i < transportationType.getData().getCar_require().size(); i++) {
                                if (transportationType.getData().getCar_require().get(i).getId().equals(response.body().getData().getNeedCar())) {
                                    tvCarRequirement.setText(transportationType.getData().getCar_require().get(i).getName());
                                }
                                needCar.add(transportationType.getData().getCar_require().get(i).getName());
                            }
                            for (int i = 0; i < transportationType.getData().getSettleSty().size(); i++) {
                                if (transportationType.getData().getSettleSty().get(i).getId().equals(response.body().getData().getSettleSty())) {
                                    tvSettleSty.setText(transportationType.getData().getSettleSty().get(i).getName());
                                }
                                settleSty.add(transportationType.getData().getSettleSty().get(i).getName());
                            }
                            serviceTypeId = response.body().getData().getDelivery();
                            trTimeId = response.body().getData().getTsTime();
                            trTypeId = response.body().getData().getTransport();
                            carYQId = response.body().getData().getNeedCar();
                            getSettleStyId = response.body().getData().getSettleSty();
                            etInsuranceValue.setText(response.body().getData().getPrice() + "");
                            etFils.setText(response.body().getData().getFileInfo() + "");
                            etRemarks.setText(response.body().getData().getRemarks() + "");

                        } else {
                            ToastUtil.showToast(response.body().getMsg());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<BillDetailsData>> call, Throwable t) {
                    ToastUtil.showToast("网络异常:获取详情信息失败");
                }
            });
        }
        etWeigth.setText(billUpData.getAllWeight()+ "");
        etVolume.setText(billUpData.getAllVolume() + "");
        deliveryAdapter = new ArrayAdapter(SetTransportationActivity.this, R.layout.item_type, R.id.tv_popqusetion, delivery);
        tsTimeAdapter = new ArrayAdapter(SetTransportationActivity.this, R.layout.item_type, R.id.tv_popqusetion, tsTime);
        transportAdapter = new ArrayAdapter(SetTransportationActivity.this, R.layout.item_type, R.id.tv_popqusetion, transport);
        needCarAdapter = new ArrayAdapter(SetTransportationActivity.this, R.layout.item_type, R.id.tv_popqusetion, needCar);
        settleStyAdapter = new ArrayAdapter(SetTransportationActivity.this, R.layout.item_type, R.id.tv_popqusetion, settleSty);
    }

    OnTypeSelectImp deliveryImpl = new OnTypeSelectImp() {
        @Override
        public void select(int postion) {
            tvServiceType.setText(transportationType.getData().getDelivery().get(postion).getName());
            serviceTypeId = transportationType.getData().getDelivery().get(postion).getId();
        }
    };
    OnTypeSelectImp tsTimeImpl = new OnTypeSelectImp() {
        @Override
        public void select(int postion) {
            tvTransportTime.setText(transportationType.getData().getTsTime().get(postion).getName());
            trTimeId = transportationType.getData().getTsTime().get(postion).getId();
        }
    };
    OnTypeSelectImp transportImpl = new OnTypeSelectImp() {
        @Override
        public void select(int postion) {
            tvTransportPrescription.setText(transportationType.getData().getTransport_mode().get(postion).getName());
            trTypeId = transportationType.getData().getTransport_mode().get(postion).getId();
        }
    };
    OnTypeSelectImp needCarImpl = new OnTypeSelectImp() {
        @Override
        public void select(int postion) {
            tvCarRequirement.setText(transportationType.getData().getCar_require().get(postion).getName());
            carYQId = transportationType.getData().getCar_require().get(postion).getId();
        }
    };
    OnTypeSelectImp settleStyImpl = new OnTypeSelectImp() {
        @Override
        public void select(int postion) {
            tvSettleSty.setText(transportationType.getData().getSettleSty().get(postion).getName());
            getSettleStyId = transportationType.getData().getSettleSty().get(postion).getId();
        }
    };

    @OnClick({R.id.layout_settleSty, R.id.layout_service_type, R.id.layout_transport_time, R.id.layout_transport_prescription, R.id.layout_car_requirement, R.id.btn_last, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_service_type:
                deliverWindow = new TypeSelectWindow(this);
                deliverWindow.showView(layoutButton, deliveryAdapter, deliveryImpl);
                break;
            case R.id.layout_settleSty:
                settleStyWindow = new TypeSelectWindow(this);
                settleStyWindow.showView(layoutButton, settleStyAdapter, settleStyImpl);
                break;
            case R.id.layout_transport_time:
                tsTimeWindow = new TypeSelectWindow(this);
                tsTimeWindow.showView(layoutButton, tsTimeAdapter, tsTimeImpl);
                break;
            case R.id.layout_transport_prescription:
                transportWindow = new TypeSelectWindow(this);
                transportWindow.showView(layoutButton, transportAdapter, transportImpl);
                break;
            case R.id.layout_car_requirement:
                needCarWindow = new TypeSelectWindow(this);
                needCarWindow.showView(layoutButton, needCarAdapter, needCarImpl);
                break;
            case R.id.btn_last:
                finish();
                break;
            case R.id.btn_commit:
                if (TextUtils.equals(tvServiceType.getText().toString(), "请选择")) {
                    ToastUtil.showToast("请选择服务方式");
                    return;
                }
                if (TextUtils.equals(tvTransportTime.getText().toString(), "请选择")) {
                    ToastUtil.showToast("请选择运输时效");
                    return;
                }
                if (TextUtils.equals(tvTransportPrescription.getText().toString(), "请选择")) {
                    ToastUtil.showToast("请选择运输方式");
                    return;
                }
                if (TextUtils.equals(tvCarRequirement.getText().toString(), "请选择")) {
                    ToastUtil.showToast("请选择车辆要求");
                    return;
                }
                if (TextUtils.equals(tvSettleSty.getText().toString(), "请选择")) {
                    ToastUtil.showToast("请选择结算方式");
                    return;
                }
                if (TextUtils.isEmpty(etWeigth.getText().toString())) {
                    ToastUtil.showToast("请填写总重量");
                    return;
                }
                if (TextUtils.isEmpty(etVolume.getText().toString())) {
                    ToastUtil.showToast("请填写总体积");
                    return;
                }
                if (TextUtils.isEmpty(etInsuranceValue.getText().toString())) {
                    ToastUtil.showToast("请填写保险声明价值");
                    return;
                }
                if (TextUtils.isEmpty(etRemarks.getText().toString())) {
                    ToastUtil.showToast("请填写备注");
                    return;
                }
                billUpData.setDelivery(serviceTypeId);
                billUpData.setTsTime(trTimeId);
                billUpData.setTransport(trTypeId);
                billUpData.setNeedCar(carYQId);
                billUpData.setSettleSty(getSettleStyId);
                billUpData.setAllWeight(etWeigth.getText().toString());
                billUpData.setAllVolume(etVolume.getText().toString());
                billUpData.setPrice(etInsuranceValue.getText().toString());
                billUpData.setFileInfo(etFils.getText().toString());
                billUpData.setRemarks(etRemarks.getText().toString());
                billUpData.setToken(PreferenceUtils.getInstance().getUserToken());
                commit().enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.body().getCode() == 1) {
                            CommitBillSuccessActivity.actionStart(SetTransportationActivity.this);
                            finish();
                        } else {
                            ToastUtil.showToast(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        ToastUtil.showToast("网络异常:提交失败");
                    }
                });
                break;
        }
    }

    private Call<ApiResponse> commit() {
        if (TextUtils.isEmpty(billUpData.getDp_id())) {
            Call<ApiResponse> creatCall = HttpUtil.createRequest(BaseUrl.getInstence().add_take()).operateAdd(PreferenceUtils.getInstance().getUserToken(), billUpData.getT_id(),
                    billUpData.getCo_id(), billUpData.getW_id(), billUpData.getSa_id(), billUpData.getStartTime(),
                    billUpData.getEndTime(), billUpData.getArrivalTime(), billUpData.getAorder(), billUpData.getYnum(), billUpData.getDelivery(),
                    billUpData.getTsTime(), billUpData.getTransport(), billUpData.getNeedCar(), billUpData.getSettleSty(), billUpData.getAllWeight(),
                    billUpData.getAllVolume(), billUpData.getFileInfo(), billUpData.getRemarks(), billUpData.getPrice(),
                    billUpData.getGoodsList());
            return creatCall;
        } else {
            Call<ApiResponse> editCall = HttpUtil.createRequest(BaseUrl.getInstence().operate_edit()).operateEdit(PreferenceUtils.getInstance().getUserToken(),
                    billUpData.getDp_id(), billUpData.getCo_id(), billUpData.getW_id(), billUpData.getSa_id(), billUpData.getStartTime(),
                    billUpData.getEndTime(), billUpData.getArrivalTime(), billUpData.getAorder(), billUpData.getYnum(), billUpData.getDelivery(),
                    billUpData.getTsTime(), billUpData.getTransport(), billUpData.getNeedCar(), billUpData.getSettleSty(), billUpData.getAllWeight(),
                    billUpData.getAllVolume(), billUpData.getFileInfo(), billUpData.getRemarks(), billUpData.getPrice(),
                    billUpData.getGoodsList());
            return editCall;
        }
    }

    private String serviceTypeId = "";
    private String trTimeId = "";
    private String trTypeId = "";
    private String carYQId = "";
    private String getSettleStyId = "";

}
