package com.ocean.dsgoods.activity.createbill;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liaoinstan.springview.widget.SpringView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.BaseActivity;
import com.ocean.dsgoods.activity.SelectContractActivity;
import com.ocean.dsgoods.adapter.FillBillAdapter;
import com.ocean.dsgoods.adapter.SelectContractAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.BillUpData;
import com.ocean.dsgoods.entity.ContractData;
import com.ocean.dsgoods.entity.GoodList;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.RecyclerViewHelper;
import com.ocean.dsgoods.tools.SimpleFooter;
import com.ocean.dsgoods.tools.SimpleHeader;
import com.ocean.dsgoods.tools.TitleManger;
import com.ocean.dsgoods.tools.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ocean.dsgoods.activity.SelectAddressActivity.CALLBACK;

/**
 * Created by James on 2020/6/30.
 * 新建提货单-填写提货清单
 */
public class FillDeliveryListActivity extends BaseActivity {

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
    @BindView(R.id.rv_fill_bill)
    RecyclerView rvFillBill;
    @BindView(R.id.layout_button)
    LinearLayout layoutButton;
    @BindView(R.id.btn_last)
    Button btnLast;
    @BindView(R.id.btn_next)
    Button btnNext;
    public static final String BILL_UP_DATA = "BILL_UP_DATA";
    BillUpData billUpData;
    @BindView(R.id.sv_list)
    SpringView svList;
    private FillBillAdapter adapter;

    public static void actionStart(Context context, String upBillData) {
        Intent intent = new Intent(context, FillDeliveryListActivity.class);
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
        return R.layout.activity_fill_delivery_list;
    }

    private void initSpringViewStyle() {
        svList.setType(SpringView.Type.FOLLOW);
        svList.setListener(onFreshListener);
        svList.setHeader(new SimpleHeader(this));
        svList.setFooter(new SimpleFooter(this));
    }

    private int page = 1;
    SpringView.OnFreshListener onFreshListener = new SpringView.OnFreshListener() {
        @Override
        public void onRefresh() {
            page = 1;
            getData();
        }

        @Override
        public void onLoadmore() {
            page = ++page;
            getData();
        }
    };

    List<GoodList.ListBean> listBeans = new ArrayList<>();

    private void getData() {
        billUpData = new Gson().fromJson(getIntent().getStringExtra(BILL_UP_DATA), BillUpData.class);
            HttpUtil.createRequest(BaseUrl.getInstence().goods_list()).index_goods_list(PreferenceUtils.getInstance().getUserToken(), billUpData.getDp_id(), page + "", billUpData.getW_id()).enqueue(new Callback<ApiResponse<GoodList>>() {
                @Override
                public void onResponse(Call<ApiResponse<GoodList>> call, Response<ApiResponse<GoodList>> response) {
                    if (svList != null) {
                        svList.onFinishFreshAndLoad();
                    }
                    if (response.body() != null) {
                        if (response.body().getCode() == 1) {
                            if (page == 1) {
                                listBeans.clear();
                                listBeans.addAll(response.body().getData().getList());
                                RecyclerViewHelper.initRecyclerViewV(FillDeliveryListActivity.this, rvFillBill, false, adapter);
                            } else {
                                listBeans.addAll(response.body().getData().getList());
                            }
                            adapter.setDatas(listBeans);

                        } else {
                            ToastUtil.showToast(response.body().getMsg());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<GoodList>> call, Throwable t) {
                    ToastUtil.showToast("网络异常:获取列表数据失败");
                }
            });
    }

    @Override
    protected void initViews() {
        initSpringViewStyle();
        adapter = new FillBillAdapter(this);
        getData();
    }

    @Override
    protected void initDatas() {

    }

    private Double allWeigth = 0.0;
    private Double allVolume = 0.0;

    @OnClick({R.id.btn_last, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_last:
                finish();
                break;
            case R.id.btn_next:
                List<BillUpData.Good> goods = new ArrayList<>();
                if (listBeans != null && listBeans.size() > 0) {
                    allWeigth=0.0;
                    allVolume=0.0;
                    for (int i = 0; i < listBeans.size(); i++) {
                        if (listBeans.get(i).getType() == 1) {
                            BillUpData.Good good = new BillUpData.Good();
                            good.setG_id(listBeans.get(i).getG_id());
                            good.setNum(listBeans.get(i).getSingleNum());
                            good.setPnum(listBeans.get(i).getSinglePnum());
                            good.setThg_id(listBeans.get(i).getId());
                            allWeigth = allWeigth + listBeans.get(i).getSingleWeigth();
                            allVolume = allVolume + listBeans.get(i).getSingleVolume();
                            Log.e("单一重量=", listBeans.get(i).getSingleWeigth() + "");
                            Log.e("单一体积=", listBeans.get(i).getSingleVolume() + "");
                            goods.add(good);
                        }
                    }
                    billUpData.setAllVolume(allVolume + "");
                    billUpData.setAllWeight(allWeigth + "");
                    Log.e("总重量=", allWeigth + "");
                    Log.e("总体积=", allVolume + "");
                    billUpData.setGoodsList(new Gson().toJson(goods));
                    Log.e("第二阶段数据列表", new Gson().toJson(goods));
                    Log.e("第二阶段数据", new Gson().toJson(billUpData));
                    SetTransportationActivity.actionStart(this, new Gson().toJson(billUpData));
                } else {
                    ToastUtil.showToast("暂无代操作车辆");
                }
                break;
        }
    }
}
