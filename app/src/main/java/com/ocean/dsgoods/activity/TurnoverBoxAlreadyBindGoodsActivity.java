package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.TurnoverInfo;
import com.ocean.dsgoods.tools.PreferenceUtils;
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
 * 周转箱已绑货
 */
public class TurnoverBoxAlreadyBindGoodsActivity extends BaseActivity {


    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_goods_num)
    TextView tvGoodsNum;
    @BindView(R.id.tv_xh)
    TextView tvXh;
    @BindView(R.id.tv_cc)
    TextView tvCc;
    @BindView(R.id.tv_glsb)
    TextView tvGlsb;
    @BindView(R.id.iv_down)
    ImageView ivDown;
    @BindView(R.id.layout_xzhw)
    RelativeLayout layoutXzhw;
    @BindView(R.id.txt_srs)
    TextView txtSrs;
    @BindView(R.id.et_srs)
    EditText etSrs;
    @BindView(R.id.txt_hwsl)
    TextView txtHwsl;
    @BindView(R.id.et_hwsl)
    EditText etHwsl;
    public static final String NUM = "num";
    public static final String RV_ID = "rv_id";
    @BindView(R.id.tv_hz)
    TextView tvHz;
    @BindView(R.id.tv_hwbh)
    TextView tvHwbh;
    @BindView(R.id.txt_hwmc)
    TextView txtHwmc;
    @BindView(R.id.tv_hwmc)
    TextView tvHwmc;
    @BindView(R.id.layout_hwmc)
    RelativeLayout layoutHwmc;
    @BindView(R.id.btn_jcbd)
    Button btnJcbd;
    @BindView(R.id.btn_bj)
    Button btnBj;

    public static void actionStart(Context context, String num, String rv_id) {
        Intent intent = new Intent(context, TurnoverBoxAlreadyBindGoodsActivity.class);
        intent.putExtra(NUM, num);
        intent.putExtra(RV_ID, rv_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("绑货");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_turnove_box_already_bind_goods;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        getData();
    }


    private void getData() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().enchaseRevolveGet()).enchaseRevolveGet(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(NUM),getIntent().getStringExtra(RV_ID)).enqueue(new Callback<ApiResponse<TurnoverInfo>>() {
            @Override
            public void onResponse(Call<ApiResponse<TurnoverInfo>> call, Response<ApiResponse<TurnoverInfo>> response) {
                if (response.body().getCode() == 1) {
                    tvGoodsNum.setText(response.body().getData().getRv_num());
                    tvXh.setText(response.body().getData().getModel());
                    tvCc.setText(response.body().getData().getSize());
                    tvGlsb.setText("--");//暂无信息
                    tvHz.setText("货主名称：" + response.body().getData().getGoods().getInfo().getS_name());
                    tvHwbh.setText(response.body().getData().getGoods().getInfo().getPro_num());
                    tvHwmc.setText(response.body().getData().getGoods().getInfo().getName());
                    etHwsl.setText(response.body().getData().getGoods().getInfo().getNum());
                    etSrs.setText(response.body().getData().getGoods().getInfo().getTake_num());
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<TurnoverInfo>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取周转箱绑货信息失败");
            }
        });
    }

    @OnClick({R.id.layout_xzhw, R.id.btn_bj, R.id.btn_jcbd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_xzhw:
                break;
            case R.id.btn_jcbd:
                unBind();
                break;
            case R.id.btn_bj:
                if (TextUtils.isEmpty(etHwsl.getText().toString())) {
                    ToastUtil.showToast("请输入货物数量");
                    return;
                }
                if (TextUtils.isEmpty(etSrs.getText().toString())) {
                    ToastUtil.showToast("请输入收容数");
                    return;
                }
                updata();
                break;
        }
    }

    private void unBind() {
        HttpUtil.createRequest(BaseUrl.getInstence().revolveUnbind()).revolveUnbind(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(NUM)
        ).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("解绑成功");
                    finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常：解绑失败");
            }
        });
    }

    private void updata() {
        HttpUtil.createRequest(BaseUrl.getInstence().revolveEditSave()).revolveEditSave(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(NUM),
                etSrs.getText().toString(), etHwsl.getText().toString()).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("修改绑货成功");
                    finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常：修改绑货失败");
            }
        });
    }
}
