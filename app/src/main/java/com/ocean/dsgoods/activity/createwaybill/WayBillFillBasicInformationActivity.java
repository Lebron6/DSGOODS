package com.ocean.dsgoods.activity.createwaybill;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.BaseActivity;
import com.ocean.dsgoods.activity.BillDetailsActivity;
import com.ocean.dsgoods.adapter.BillAdapter;
import com.ocean.dsgoods.adapter.WillBillOneAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.AddInitOne;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.BillList;
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
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sv_list)
    SpringView svList;
    private WillBillOneAdapter adapter;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, WayBillFillBasicInformationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger=TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("新建运单-中转");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_way_bill_fill_basic_information;
    }

    @Override
    protected void initViews() {
        adapter = new WillBillOneAdapter(this);
        initSpringViewStyle();
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

    List<AddInitOne.ListBean> listBeans = new ArrayList<>();

    private void getData() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().addInitOne()).addInitOne(PreferenceUtils.getInstance().getUserToken(), page + "").enqueue(new Callback<ApiResponse<AddInitOne>>() {
            @Override
            public void onResponse(Call<ApiResponse<AddInitOne>> call, Response<ApiResponse<AddInitOne>> response) {
                if (svList != null) {
                    svList.onFinishFreshAndLoad();
                }
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        if (page == 1) {
                            listBeans.clear();
                            listBeans.addAll(response.body().getData().getList());
                            RecyclerViewHelper.initRecyclerViewV(WayBillFillBasicInformationActivity.this, rvList, false, adapter);
                        } else {
                            listBeans.addAll(response.body().getData().getList());
                        }

                        adapter.setDatas(listBeans);
                        adapter.setOnItemClickLitener(new WillBillOneAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
                            }
                        });
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<AddInitOne>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取列表数据失败");
            }
        });
    }

    @Override
    protected void initDatas() {
        getData();
    }
}
