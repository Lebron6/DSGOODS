package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
 * 扫普通箱绑货
 */
public class ScanBoxActivity extends BaseActivity {

    public static final String PKID = "pk_id";
    public static final String NUM = "num";
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
    @BindView(R.id.tv_bzmc)
    TextView tvBzmc;
    @BindView(R.id.tv_xzhw)
    TextView tvXzhw;
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

    public static void actionStart(Context context, String num,String pk_id) {
        Intent intent = new Intent(context, ScanBoxActivity.class);
        intent.putExtra(NUM, num);
        intent.putExtra(PKID, pk_id);
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
        return R.layout.activity_scan_box;
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
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().enchasePackingGet()).enchasePackingGet(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(NUM), getIntent().getStringExtra(PKID)).enqueue(new Callback<ApiResponse<TurnoverInfo>>() {
            @Override
            public void onResponse(Call<ApiResponse<TurnoverInfo>> call, Response<ApiResponse<TurnoverInfo>> response) {
                if (response.body().getCode() == 1) {
                    if (response.body().getData().getGoods().isHas_goods() == true) {//已绑货
                        BoxAlreadyBindGoodsActivity.actionStart(ScanBoxActivity.this, getIntent().getStringExtra(NUM),getIntent().getStringExtra(PKID));
                        finish();

                    } else {
                        tvGoodsNum.setText(response.body().getData().getPk_num());
                        tvXh.setText(response.body().getData().getModel());
                        tvCc.setText(response.body().getData().getSize());
                        tvBzmc.setText(response.body().getData().getName());//暂无信息
                        brands = new ArrayList<>();
                        listBeans = response.body().getData().getGoods().getList();
                        for (int i = 0; i < listBeans.size(); i++) {
                            brands.add(listBeans.get(i).getName());
                        }
                        brandAdapter = new ArrayAdapter(ScanBoxActivity.this, R.layout.item_type, R.id.tv_popqusetion, brands);
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
                if (TextUtils.isEmpty(etSrs.getText().toString())) {
                    ToastUtil.showToast("请输入收容数");
                    return;
                }
                bind();
                break;
        }
    }

    private void bind() {
        HttpUtil.createRequest(BaseUrl.getInstence().packingGoodsSave()).packingGoodsSave(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(NUM)
                , g_id, getIntent().getStringExtra(PKID), etHwsl.getText().toString()).enqueue(new Callback<ApiResponse>() {
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
            g_id = listBeans.get(postion).getG_id();   etSrs.setText(listBeans.get(postion).getTake_num());
        }
    };

}
