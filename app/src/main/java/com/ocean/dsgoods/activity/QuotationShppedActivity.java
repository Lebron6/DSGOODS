package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.QuotationShppedAdapter;
import com.ocean.dsgoods.adapter.QuotationShppedTitleAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.ShppedQuotationData;
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
 * 我发运的-货物清单
 */
public class QuotationShppedActivity extends BaseActivity {
    public static final String WA_ID = "Q_ID";
    public static final String TYPE = "type";
    @BindView(R.id.rv_title)
    RecyclerView rvTitle;
    @BindView(R.id.layout_title_type)
    LinearLayout layoutTitleType;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_weigth)
    TextView tvWeigth;
    @BindView(R.id.tv_volume)
    TextView tvVolume;
    @BindView(R.id.tv_jnum)
    TextView tvJnum;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;

    public static void actionStart(Context context, String wa_id, String type) {
        Intent intent = new Intent(context, QuotationShppedActivity.class);
        intent.putExtra(WA_ID, wa_id);
        intent.putExtra(TYPE, type);
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
        return R.layout.activity_shpped_quotation;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().waybillGoods()).waybillGoods(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(TYPE), getIntent().getStringExtra(WA_ID), "1").enqueue(new Callback<ApiResponse<ShppedQuotationData>>() {
            @Override
            public void onResponse(Call<ApiResponse<ShppedQuotationData>> call, Response<ApiResponse<ShppedQuotationData>> response) {
                if (response.body().getCode() == 1) {
                    QuotationShppedTitleAdapter adapter = new QuotationShppedTitleAdapter(QuotationShppedActivity.this);
                    adapter.setDatas(response.body().getData());
                    RecyclerViewHelper.initRecyclerViewH(QuotationShppedActivity.this, rvTitle, false, adapter);

                    QuotationShppedAdapter quotationAdapter = new QuotationShppedAdapter(QuotationShppedActivity.this);
                    quotationAdapter.setDatas(response.body().getData());
                    RecyclerViewHelper.initRecyclerViewV(QuotationShppedActivity.this, rvList, false, quotationAdapter);
               tvVolume.setText("总体积："+response.body().getData().getTotal_info().getAllVolume());
               tvWeigth.setText("总重量："+response.body().getData().getTotal_info().getAllWeight());
               tvJnum.setText("件数："+response.body().getData().getTotal_info().getGoodsJnum());
               tvNum.setText("货物数量："+response.body().getData().getTotal_info().getGoodsNum());


                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<ShppedQuotationData>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取数据失败");
            }
        });
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
