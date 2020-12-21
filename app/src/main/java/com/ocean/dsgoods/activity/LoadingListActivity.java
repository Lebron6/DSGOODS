package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.LoadingAdapter;
import com.ocean.dsgoods.adapter.LoadingTitleAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.dialog.ToVoidBillRemarksDialog;
import com.ocean.dsgoods.dialog.ToVoidLoadingRemarksDialog;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.LoadingListData;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.RecyclerViewHelper;
import com.ocean.dsgoods.tools.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/9/1.
 * 装车清单
 */
public class LoadingListActivity extends BaseActivity {
    public static final String ID = "ID";
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.layout_accept)
    LinearLayout layoutAccept;
    @BindView(R.id.layout_reject)
    LinearLayout layoutReject;
    @BindView(R.id.rv_title)
    RecyclerView rvTitle;
    @BindView(R.id.rv_list)
    RecyclerView rvList;


    public static void actionStart(Context context, String id) {
        Intent intent = new Intent(context, LoadingListActivity.class);
        intent.putExtra(ID, id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_loading_list;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().loadingGoodsList()).loadingGoodsList(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(ID)).enqueue(new Callback<ApiResponse<LoadingListData>>() {
            @Override
            public void onResponse(Call<ApiResponse<LoadingListData>> call, Response<ApiResponse<LoadingListData>> response) {
                if (response.body().getCode() == 1) {
                    if (response.body().getData().getStatus().equals("1")) {
                        layoutAccept.setVisibility(View.VISIBLE);
                        layoutReject.setVisibility(View.VISIBLE);
                    } else {
                        layoutAccept.setVisibility(View.GONE);
                        layoutReject.setVisibility(View.GONE);
                    }

                    LoadingTitleAdapter adapter = new LoadingTitleAdapter(LoadingListActivity.this);
                    adapter.setDatas(response.body().getData());
                    RecyclerViewHelper.initRecyclerViewH(LoadingListActivity.this, rvTitle, false, adapter);

                    LoadingAdapter quotationAdapter = new LoadingAdapter(LoadingListActivity.this);
                    quotationAdapter.setDatas(response.body().getData());
                    RecyclerViewHelper.initRecyclerViewV(LoadingListActivity.this, rvList, false, quotationAdapter);
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<LoadingListData>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取数据失败");
            }
        });
    }


    @OnClick({R.id.layout_accept, R.id.layout_reject})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_accept:
                accept();
                break;
            case R.id.layout_reject:
                ToVoidLoadingRemarksDialog voidBillRemarksDialog = new ToVoidLoadingRemarksDialog(this, R.style.Theme_AppCompat_Dialog, getIntent().getStringExtra(ID));
                voidBillRemarksDialog.show();
                break;
        }
    }

    private void accept() {

        HttpUtil.createRequest(BaseUrl.getInstence().affirmTake()).affirmTake(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(ID),"1","").enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("通过成功");
                    finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:通过失败");
            }
        });
    }

}
