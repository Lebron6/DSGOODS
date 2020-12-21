package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.ChosePackageAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.ScanGoodInfo;
import com.ocean.dsgoods.entity.UpLoadTest;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.RecyclerViewHelper;
import com.ocean.dsgoods.tools.TitleManger;
import com.ocean.dsgoods.tools.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/11/13.
 * 手动绑定选择包装
 */
public class ManualBindChosePackageActivity extends BaseActivity {
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
    @BindView(R.id.btn_sure)
    Button btnSure;

    public static void actionStart(Context context, String g_id, String wa_id) {
        Intent intent = new Intent(context, ManualBindChosePackageActivity.class);
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
        return R.layout.activity_manual_bind_chose_package;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        getData();
    }

    ScanGoodInfo infoApiResponse;

    private void getData() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().handBindInfo()).handBindInfo(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(G_ID), getIntent().getStringExtra(WA_ID)).enqueue(new Callback<ApiResponse<ScanGoodInfo>>() {
            @Override
            public void onResponse(Call<ApiResponse<ScanGoodInfo>> call, Response<ApiResponse<ScanGoodInfo>> response) {
                infoApiResponse = response.body().getData();
                tvHwmc.setText("货物名称：" + infoApiResponse.getWaybillInfo().getName());
                tvHwxh.setText(infoApiResponse.getWaybillInfo().getGood_type());
                tvHwjs.setText(infoApiResponse.getWaybillInfo().getPnum());
                tvHwsl.setText(infoApiResponse.getWaybillInfo().getNum());
                tvSmjs.setText(infoApiResponse.getWaybillInfo().getAlready_jnum());
                tvSmsl.setText(infoApiResponse.getWaybillInfo().getAlready_num());

                ChosePackageAdapter adapter = new ChosePackageAdapter(ManualBindChosePackageActivity.this);
                adapter.setDatas(infoApiResponse.getList(), getIntent().getStringExtra(G_ID), getIntent().getStringExtra(WA_ID));
                RecyclerViewHelper.initRecyclerViewV(ManualBindChosePackageActivity.this, rvList, false, adapter);
            }

            @Override
            public void onFailure(Call<ApiResponse<ScanGoodInfo>> call, Throwable t) {

            }
        });
    }

int jNum;
    int num;
    @OnClick(R.id.btn_sure)
    public void onViewClicked() {
//        jNum=infoApiResponse.getList().get(0).getNum_sn()
        String listString = new Gson().toJson(infoApiResponse.getList());
        Log.e("上传数据", listString);
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().handBindSave()).handBindSave(PreferenceUtils.getInstance().getUserToken(),
                infoApiResponse.getWaybillInfo().getAlready_jnum(), infoApiResponse.getWaybillInfo().getAlready_num(),
                listString, infoApiResponse.getWaybillInfo().getPnum(), infoApiResponse.getWaybillInfo().getNum()).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("操作成功");
                    finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:操作失败");
            }
        });
    }
}
