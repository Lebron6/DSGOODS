package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.callback.OnTypeSelectImp;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.TurnoverInfo;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.TitleManger;
import com.ocean.dsgoods.tools.ToastUtil;
import com.ocean.dsgoods.view.TypeSelectWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/11/13.
 * 扫周转箱绑货
 */
public class ScanTurnoverBoxActivity extends BaseActivity {


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
    @BindView(R.id.btn_keep)
    Button btnKeep;
    public static final String NUM = "num";
    public static final String RV_ID = "rv_id";
    @BindView(R.id.tv_xzhw)
    TextView tvXzhw;

    public static void actionStart(Context context, String num, String rv_id) {
        Intent intent = new Intent(context, ScanTurnoverBoxActivity.class);
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
        return R.layout.activity_scan_turnove_box;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        getData();
    }

    List<TurnoverInfo.GoodsBean.ListBean> listBeans;

    private void getData() {
        Log.e("测试","rv_num="+ getIntent().getStringExtra(NUM));
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().enchaseRevolveGet()).enchaseRevolveGet(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(NUM),getIntent().getStringExtra(RV_ID)).enqueue(new Callback<ApiResponse<TurnoverInfo>>() {
            @Override
            public void onResponse(Call<ApiResponse<TurnoverInfo>> call, Response<ApiResponse<TurnoverInfo>> response) {
                if (response.body().getCode() == 1) {
                    if (response.body().getData().getGoods().isHas_goods() == true) {//已绑货
                        TurnoverBoxAlreadyBindGoodsActivity.actionStart(ScanTurnoverBoxActivity.this,getIntent().getStringExtra(NUM),getIntent().getStringExtra(RV_ID));
                    finish();

                    } else {
                        tvGoodsNum.setText(response.body().getData().getRv_num());
                        tvXh.setText(response.body().getData().getModel());
                        tvCc.setText(response.body().getData().getSize());
                        tvGlsb.setText(response.body().getData().getFuselage());
                        brands = new ArrayList<>();
                        listBeans = response.body().getData().getGoods().getList();
                        for (int i = 0; i < listBeans.size(); i++) {
                            brands.add(listBeans.get(i).getName());
                        }
                        brandAdapter = new ArrayAdapter(ScanTurnoverBoxActivity.this, R.layout.item_type, R.id.tv_popqusetion, brands);
                    }


                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<TurnoverInfo>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取周转箱信息失败");
            }
        });
    }

    private ArrayAdapter brandAdapter;
    private List<String> brands;
    private String g_id;

    @OnClick({R.id.layout_xzhw, R.id.btn_keep})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_xzhw:
                TypeSelectWindow brandWindow = new TypeSelectWindow(this);
                brandWindow.showView(btnKeep, brandAdapter, typeImpl);
                break;
            case R.id.btn_keep:
                if (TextUtils.equals(tvXzhw.getText().toString(), "请选择")) {
                    ToastUtil.showToast("请选择货物");
                    return;
                }
                if (TextUtils.isEmpty(etHwsl.getText().toString())) {
                    ToastUtil.showToast("请输入货物数量");
                    return;
                }
                bind();
                break;
        }
    }

    private void bind() {
        HttpUtil.createRequest(BaseUrl.getInstence().revolveGoodsSave()).revolveGoodsSave(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(NUM)
                , g_id, etSrs.getText().toString(), etHwsl.getText().toString()).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("绑货成功");
                    finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常：绑货失败");
            }
        });
    }

    OnTypeSelectImp typeImpl = new OnTypeSelectImp() {
        @Override
        public void select(int postion) {
            tvXzhw.setText(brands.get(postion));
            g_id = listBeans.get(postion).getG_id();
            etSrs.setText(listBeans.get(postion).getTake_num());
        }
    };

}
