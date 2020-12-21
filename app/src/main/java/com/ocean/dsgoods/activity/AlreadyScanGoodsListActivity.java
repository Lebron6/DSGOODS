package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.BindPackageScanAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.ScanGoodInfo;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.RecyclerViewHelper;
import com.ocean.dsgoods.tools.TitleManger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/11/13.
 * 已扫码货物清单
 */
public class AlreadyScanGoodsListActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_hwmc)
    TextView tvHwmc;
    @BindView(R.id.tv_hwxh)
    TextView tvHwxh;
    @BindView(R.id.tv_hwjs)
    TextView tvHwjs;
    @BindView(R.id.tv_smjs)
    TextView tvSmjs;
    @BindView(R.id.tv_hwsl)
    TextView tvHwsl;
    @BindView(R.id.tv_smsl)
    TextView tvSmsl;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    public static String G_ID = "g_id";
    public static String WA_ID = "wa_id";

    public static void actionStart(Context context, String g_id, String wa_id) {
        Intent intent = new Intent(context, AlreadyScanGoodsListActivity.class);
        intent.putExtra(G_ID, g_id);
        intent.putExtra(WA_ID, wa_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("已扫码货物清单");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_already_scan_goods_list;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        getData();
    }

    private void getData() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().scanGoodInfo()).scanGoodInfo(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(G_ID), getIntent().getStringExtra(WA_ID)).enqueue(new Callback<ApiResponse<ScanGoodInfo>>() {
            @Override
            public void onResponse(Call<ApiResponse<ScanGoodInfo>> call, Response<ApiResponse<ScanGoodInfo>> response) {
                tvHwmc.setText("货物名称：" + response.body().getData().getWaybillInfo().getName());
                tvHwxh.setText(response.body().getData().getWaybillInfo().getGood_type());
                tvHwjs.setText(response.body().getData().getWaybillInfo().getPnum());
                tvHwsl.setText(response.body().getData().getWaybillInfo().getNum());
                tvSmjs.setText(response.body().getData().getWaybillInfo().getAlready_jnum());
                tvSmsl.setText(response.body().getData().getWaybillInfo().getAlready_num());

                BindPackageScanAdapter adapter = new BindPackageScanAdapter(AlreadyScanGoodsListActivity.this);
                adapter.setDatas(response.body().getData().getList(),getIntent().getStringExtra(G_ID),getIntent().getStringExtra(WA_ID));
                RecyclerViewHelper.initRecyclerViewV(AlreadyScanGoodsListActivity.this, rvList, false, adapter);
            }

            @Override
            public void onFailure(Call<ApiResponse<ScanGoodInfo>> call, Throwable t) {

            }
        });
    }
}
