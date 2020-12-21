package com.ocean.dsgoods.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liaoinstan.springview.widget.SpringView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.SelectAddressAdapter;
import com.ocean.dsgoods.adapter.SortAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.SupplierList;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ocean.dsgoods.activity.SelectAddressActivity.CALLBACK;
import static com.ocean.dsgoods.activity.SelectAddressActivity.PARMS;

/**
 * Created by James on 2020/7/29.
 * 选择供应商
 */
public class SelectSupplierActivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.filter_edit)
    EditText filterEdit;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.lv_supplier)
    ListView lvSupplier;
    @BindView(R.id.sv_list)
    SpringView svList;

    private SortAdapter adapter;

    public static void actionStartForResult(Activity context) {
        Intent intent = new Intent(context, SelectSupplierActivity.class);
        context.startActivityForResult(intent, PARMS);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("选择供应商");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_select_supplier;
    }

    @Override
    protected void initViews() {
        adapter = new SortAdapter(this, listBeans);
        lvSupplier.setAdapter(adapter);
        initSpringViewStyle();
    }

    @Override
    protected void initDatas() {
        getData();
    }

    @OnClick(R.id.tv_search)
    public void onViewClicked() {
        if (TextUtils.isEmpty(filterEdit.getText().toString())) {
            ToastUtil.showToast("请输入搜索内容");
            return;
        }
        search();
    }

    private void search() {
        page = 1;
        HttpUtil.createRequest(BaseUrl.getInstence().supplier_list()).getSupplier(PreferenceUtils.getInstance().getUserToken(), page + "", filterEdit.getText().toString()).enqueue(new Callback<ApiResponse<SupplierList>>() {
            @Override
            public void onResponse(Call<ApiResponse<SupplierList>> call, Response<ApiResponse<SupplierList>> response) {
                if (response.body().getCode() == 1) {
                    if (svList != null) {
                        svList.onFinishFreshAndLoad();
                    }
                    if (response.body() != null) {
                        if (response.body().getCode() == 1) {
                            if (page == 1) {
                                listBeans.clear();
                                listBeans.addAll(response.body().getData().getList());
                            } else {
                                listBeans.addAll(response.body().getData().getList());
                            }
                            adapter.updateListView(listBeans);
                        } else {
                            ToastUtil.showToast(response.body().getMsg());
                        }
                    }
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<SupplierList>> call, Throwable t) {
                ToastUtil.showToast("网络异常:查询供应商失败");
            }
        });
    }

    private void initSpringViewStyle() {
        svList.setType(SpringView.Type.FOLLOW);
        svList.setListener(onFreshListener);
        svList.setHeader(new SimpleHeader(this));
        svList.setFooter(new SimpleFooter(this));
    }

    List<SupplierList.ListBean> listBeans = new ArrayList<>();
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

    private void getData() {

        HttpUtil.createRequest(BaseUrl.getInstence().supplier_list()).getSupplier(PreferenceUtils.getInstance().getUserToken(), page + "").enqueue(new Callback<ApiResponse<SupplierList>>() {
            @Override
            public void onResponse(Call<ApiResponse<SupplierList>> call, Response<ApiResponse<SupplierList>> response) {
                if (response.body().getCode() == 1) {
                    if (svList != null) {
                        svList.onFinishFreshAndLoad();
                    }
                    if (response.body() != null) {
                        if (response.body().getCode() == 1) {
                            if (page == 1) {
                                listBeans.clear();
                                listBeans.addAll(response.body().getData().getList());
                            } else {
                                listBeans.addAll(response.body().getData().getList());
                            }
                            adapter.updateListView(listBeans);
                            lvSupplier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent intent = new Intent();
                                    intent.putExtra(CALLBACK, new Gson().toJson(listBeans.get(i)));
                                    setResult(3, intent);
                                    finish();
                                }
                            });
                        } else {
                            ToastUtil.showToast(response.body().getMsg());
                        }
                    }
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<SupplierList>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取供应商失败");
            }
        });

    }

}
