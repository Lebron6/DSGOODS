package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.liaoinstan.springview.widget.SpringView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.AlreadyAddGoodsAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.AlreadyAddGoods;
import com.ocean.dsgoods.entity.ScanResult;
import com.ocean.dsgoods.entity.ScanUpdata;
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
import io.github.xudaojie.qrcodelib.CaptureActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/11/12.
 * 已添加包装的货物列表
 */
public class AlreadyAddGoodsListActivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rv_package)
    RecyclerView rvPackage;
    @BindView(R.id.sv_package)
    SpringView svPackage;
    AlreadyAddGoodsAdapter adapter;

    public static String WA_ID = "wa_id";
    @BindView(R.id.tv_scan)
    TextView tvScan;

    public static void actionStart(Context context, String t_id) {
        Intent intent = new Intent(context, AlreadyAddGoodsListActivity.class);
        intent.putExtra(WA_ID, t_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("已添加货物列表");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_already_add_goods_list;
    }

    @Override
    protected void initViews() {
        initSpringViewStyle();
        adapter = new AlreadyAddGoodsAdapter(this);
    }

    private void initSpringViewStyle() {
        svPackage.setType(SpringView.Type.FOLLOW);
        svPackage.setListener(onFreshListener);
        svPackage.setHeader(new SimpleHeader(this));
        svPackage.setFooter(new SimpleFooter(this));
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

    List<AlreadyAddGoods.DataBean> listBeans = new ArrayList<>();

    private void getData() {
        HttpUtil.createRequest(BaseUrl.getInstence().wayBillBindGoodList()).wayBillBindGoodList(PreferenceUtils.getInstance().getUserToken(), page + "", getIntent().getStringExtra(WA_ID)).enqueue(new Callback<AlreadyAddGoods>() {
            @Override
            public void onResponse(Call<AlreadyAddGoods> call, Response<AlreadyAddGoods> response) {
                if (svPackage != null) {
                    svPackage.onFinishFreshAndLoad();
                }
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        if (page == 1) {
                            listBeans.clear();
                            listBeans.addAll(response.body().getData());
                            RecyclerViewHelper.initRecyclerViewV(AlreadyAddGoodsListActivity.this, rvPackage, false, adapter);
                        } else {
                            listBeans.addAll(response.body().getData());
                        }
                        adapter.setDatas(listBeans, getIntent().getStringExtra(WA_ID));
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            //
            @Override
            public void onFailure(Call<AlreadyAddGoods> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取列表数据失败");
            }
        });
    }

    @Override
    protected void initDatas() {
        getData();
    }

    private static final int CK = 2;

    @OnClick(R.id.tv_scan)
    public void onViewClicked() {
        Intent ykIntent = new Intent(this, CaptureActivity.class);
        startActivityForResult(ykIntent, CK);
    }

    /**
     * 处理二维码扫描结果
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }
            if (requestCode == CK) {
                String s = bundle.getString("result");
                Log.e("result",s);
                ScanResult scanResult = new Gson().fromJson(s, ScanResult.class);
                Call<ScanUpdata> resultCall;
                if (scanResult.getType().equals("normal")) {
                    resultCall = HttpUtil.createRequest(BaseUrl.getInstence().scanQr()).scanPk(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(WA_ID),
                            scanResult.getNum(), scanResult.getType());
                } else {
                    resultCall = HttpUtil.createRequest(BaseUrl.getInstence().scanQr()).scanRv(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(WA_ID),
                             scanResult.getNum(), scanResult.getType());
                }
                resultCall.enqueue(new Callback<ScanUpdata>() {
                    @Override
                    public void onResponse(Call<ScanUpdata> call, Response<ScanUpdata> response) {
                        if (response.body().getCode() == 1 && response.body().getData().getStatus_type().equals("1")) {
                            ToastUtil.showToast("绑定成功");
                        } else {
                            ToastUtil.showToast(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Call<ScanUpdata> call, Throwable t) {
                        ToastUtil.showToast("网络异常:扫码绑定失败");
                    }
                });
            }
        }
    }
}
