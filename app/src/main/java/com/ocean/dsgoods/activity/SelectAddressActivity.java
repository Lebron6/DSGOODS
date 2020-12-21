package com.ocean.dsgoods.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liaoinstan.springview.widget.SpringView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.SelectAddressAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.Address;
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
 * Created by James on 2020/7/1.
 * 选择地址
 */
public class SelectAddressActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rv_address)
    RecyclerView rvAddress;
    public static final String T_ID = "T_ID";
    public static final String TYPE = "type";
    public static final int PARMS = 10010;
    @BindView(R.id.sv_list)
    SpringView svList;
    private String type;
    private String t_id;
    private SelectAddressAdapter adapter;

    public static void actionStartForResult(Activity context, String t_id, String type) {
        Intent intent = new Intent(context, SelectAddressActivity.class);
        intent.putExtra(T_ID, t_id);
        intent.putExtra(TYPE, type);
        context.startActivityForResult(intent, PARMS);
    }

    @Override
    protected void initTitle() {
        TitleManger manger=TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("选择地址");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_select_address;
    }
    private void initSpringViewStyle() {
        svList.setType(SpringView.Type.FOLLOW);
        svList.setListener(onFreshListener);
        svList.setHeader(new SimpleHeader(this));
        svList.setFooter(new SimpleFooter(this));
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
    List<Address.ListBean> listBeans=new ArrayList<>();
    @Override
    protected void initViews() {
        initSpringViewStyle();
        adapter = new SelectAddressAdapter(this);
    }

    @Override
    protected void initDatas() {
        getData();

    }
    public static final String CALLBACK = "callBack";
    private void getData() {
        type = getIntent().getStringExtra(TYPE);
        t_id = getIntent().getStringExtra(T_ID);
        if (type.equals("t")) {
            HttpUtil.createRequest(TAG,BaseUrl.getInstence().take_address()).tAddress(PreferenceUtils.getInstance().getUserToken(), t_id, page+"").enqueue(new Callback<ApiResponse<Address>>() {
                @Override
                public void onResponse(Call<ApiResponse<Address>> call, Response<ApiResponse<Address>> response) {
                    if (svList != null) {
                        svList.onFinishFreshAndLoad();
                    }
                    if (response.body() != null) {
                        if (response.body().getCode() == 1) {
                            if (page == 1) {
                                listBeans.clear();
                                listBeans.addAll(response.body().getData().getList());
                                RecyclerViewHelper.initRecyclerViewV(SelectAddressActivity.this, rvAddress, false, adapter);
                            } else {
                                listBeans.addAll(response.body().getData().getList());
                            }
                            adapter.setDatas(listBeans);

                            adapter.setOnItemClickLitener(new SelectAddressAdapter.OnItemClickLitener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Intent intent = new Intent();
                                    intent.putExtra(CALLBACK, new Gson().toJson(listBeans.get(position)));
                                    setResult(1, intent);
                                    finish();
                                }
                            });
                        } else {
                            ToastUtil.showToast(response.body().getMsg());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<Address>> call, Throwable t) {

                }
            });
        }else if (type.equals("s")){
            HttpUtil.createRequest(BaseUrl.getInstence().shipping_address()).tAddress(PreferenceUtils.getInstance().getUserToken(), t_id, page+"").enqueue(new Callback<ApiResponse<Address>>() {
                @Override
                public void onResponse(Call<ApiResponse<Address>> call, Response<ApiResponse<Address>> response) {
                    if (svList != null) {
                        svList.onFinishFreshAndLoad();
                    }
                    if (response.body() != null) {
                        if (response.body().getCode() == 1) {
                            if (page == 1) {
                                listBeans.clear();
                                listBeans.addAll(response.body().getData().getList());
                                RecyclerViewHelper.initRecyclerViewV(SelectAddressActivity.this, rvAddress, false, adapter);
                            } else {
                                listBeans.addAll(response.body().getData().getList());
                            }
                            adapter.setDatas(listBeans);

                            adapter.setOnItemClickLitener(new SelectAddressAdapter.OnItemClickLitener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Intent intent = new Intent();
                                    intent.putExtra(CALLBACK, new Gson().toJson(listBeans.get(position)));
                                    setResult(2, intent);
                                    finish();
                                }
                            });

                        } else {
                            ToastUtil.showToast(response.body().getMsg());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<Address>> call, Throwable t) {

                }
            });
        }
    }
}
