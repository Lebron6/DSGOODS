package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.AddPackingToBillAdapter;
import com.ocean.dsgoods.adapter.ManualBindPackageAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.callback.OnManualClickImp;
import com.ocean.dsgoods.callback.OnScanClickImp;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.BindWillList;
import com.ocean.dsgoods.entity.ManualBindData;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.RecyclerViewHelper;
import com.ocean.dsgoods.tools.SimpleFooter;
import com.ocean.dsgoods.tools.SimpleHeader;
import com.ocean.dsgoods.tools.TitleManger;
import com.ocean.dsgoods.tools.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/11/12.
 * 手动绑定添加包装
 */
public class ManualBindingPackageActivity extends BaseActivity {

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
    ManualBindPackageAdapter adapter;
    public static final String WA_ID="wa_id";
    public static void actionStart(Context context,String wa_id) {
        Intent intent = new Intent(context, ManualBindingPackageActivity.class);
        intent.putExtra(WA_ID,wa_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger=TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("手工绑定");
        manger.setBack();
        getData();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_manual_binding;
    }

    @Override
    protected void initViews() {
        initSpringViewStyle();
        adapter = new ManualBindPackageAdapter(this);
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

    List<ManualBindData.ListBean> listBeans = new ArrayList<>();

    private void getData() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().handBindList()).handBindList(PreferenceUtils.getInstance().getUserToken(), page + "",getIntent().getStringExtra(WA_ID)).enqueue(new Callback<ApiResponse<ManualBindData>>() {
            @Override
            public void onResponse(Call<ApiResponse<ManualBindData>> call, Response<ApiResponse<ManualBindData>> response) {
                if (svPackage != null) {
                    svPackage.onFinishFreshAndLoad();
                }
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        if (page == 1) {
                            listBeans.clear();
                            listBeans.addAll(response.body().getData().getList());
                            RecyclerViewHelper.initRecyclerViewV(ManualBindingPackageActivity.this, rvPackage, false, adapter);
                        } else {
                            listBeans.addAll(response.body().getData().getList());
                        }
                        adapter.setDatas(listBeans,getIntent().getStringExtra(WA_ID));
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<ManualBindData>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取列表数据失败");
            }
        });
    }

    @Override
    protected void initDatas() {

    }

}
