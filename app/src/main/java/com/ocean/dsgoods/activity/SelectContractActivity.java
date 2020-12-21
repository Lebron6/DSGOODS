package com.ocean.dsgoods.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liaoinstan.springview.widget.SpringView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.SelectAddressAdapter;
import com.ocean.dsgoods.adapter.SelectContractAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.Address;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.ContractData;
import com.ocean.dsgoods.entity.ContractList;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ocean.dsgoods.activity.SelectAddressActivity.CALLBACK;
import static com.ocean.dsgoods.activity.SelectAddressActivity.PARMS;

/**
 * Created by James on 2020/7/1.
 * 选择合同
 */
public class SelectContractActivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rv_contarct)
    RecyclerView rvContarct;
    @BindView(R.id.sv_list)
    SpringView svList;
    private SelectContractAdapter adapter;
    public static final String T_ID = "T_ID";
    public static void actionStartForResult(Activity context,String t_id) {
        Intent intent = new Intent(context, SelectContractActivity.class);
        intent.putExtra(T_ID,t_id);
        context.startActivityForResult(intent, PARMS);
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

    List<ContractData.ListBean> listBeans = new ArrayList<>();

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("选择合同");
        manger.setBack();
        getData();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_select_contract;
    }

    @Override
    protected void initViews() {
        initSpringViewStyle();
        adapter = new SelectContractAdapter(this);
    }


    @Override
    protected void initDatas() {

    }

    private void getData() {
       String t_id = getIntent().getStringExtra(T_ID);
            HttpUtil.createRequest(TAG,BaseUrl.getInstence().contract_list()).selectContract(PreferenceUtils.getInstance().getUserToken(), t_id, page+"").enqueue(new Callback<ApiResponse<ContractData>>() {
                @Override
                public void onResponse(Call<ApiResponse<ContractData>> call, Response<ApiResponse<ContractData>> response) {
                    if (svList != null) {
                        svList.onFinishFreshAndLoad();
                    }
                    if (response.body() != null) {
                        if (response.body().getCode() == 1) {
                            if (page == 1) {
                                listBeans.clear();
                                listBeans.addAll(response.body().getData().getList());
                                RecyclerViewHelper.initRecyclerViewV(SelectContractActivity.this, rvContarct, false, adapter);
                            } else {
                                listBeans.addAll(response.body().getData().getList());
                            }
                            adapter.setDatas(listBeans);

                            adapter.setOnItemClickLitener(new SelectContractAdapter.OnItemClickLitener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Intent intent = new Intent();
                                    intent.putExtra(CALLBACK, new Gson().toJson(listBeans.get(position)));
                                    setResult(4, intent);
                                    finish();
                                }
                            });
                        } else {
                            ToastUtil.showToast(response.body().getMsg());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<ContractData>> call, Throwable t) {

                }
            });

    }
}
