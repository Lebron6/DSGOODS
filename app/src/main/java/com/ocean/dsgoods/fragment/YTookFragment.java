package com.ocean.dsgoods.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liaoinstan.springview.widget.SpringView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.ShppedDetailsActivity;
import com.ocean.dsgoods.adapter.TookAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.BillList;
import com.ocean.dsgoods.entity.PickupData;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.RecyclerViewHelper;
import com.ocean.dsgoods.tools.SimpleFooter;
import com.ocean.dsgoods.tools.SimpleHeader;
import com.ocean.dsgoods.tools.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/6/30.
 * 我收的-已完成
 */
public class YTookFragment extends BaseFragment {

    @BindView(R.id.rv_bill)
    RecyclerView rvBill;
    @BindView(R.id.sv_bill)
    SpringView svBill;
    Unbinder unbinder;
    TookAdapter adapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_bill_list;
    }

    @Override
    protected void initViews() {
        initSpringViewStyle();
          adapter = new TookAdapter(getActivity(),2);
    }

    private void initSpringViewStyle() {
        svBill.setType(SpringView.Type.FOLLOW);
        svBill.setListener(onFreshListener);
        svBill.setHeader(new SimpleHeader(getActivity()));
        svBill.setFooter(new SimpleFooter(getActivity()));
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

    List<PickupData.ListBean> listBeans = new ArrayList<>();

    private void getData() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().pickupList()).pickupList(PreferenceUtils.getInstance().getUserToken(), page + "", "2").enqueue(new Callback<ApiResponse<PickupData>>() {
            @Override
            public void onResponse(Call<ApiResponse<PickupData>> call, Response<ApiResponse<PickupData>> response) {
                if (svBill != null) {
                    svBill.onFinishFreshAndLoad();
                }
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        if (page == 1) {
                            listBeans.clear();
                            listBeans.addAll(response.body().getData().getList());
                            RecyclerViewHelper.initRecyclerViewV(getActivity(), rvBill, false, adapter);
                        } else {
                            listBeans.addAll(response.body().getData().getList());
                        }
                        adapter.setDatas(listBeans);
                        adapter.setOnItemClickLitener(new TookAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                ShppedDetailsActivity.actionStart(getActivity(),listBeans.get(position).getWa_id(),"2");
                            }
                        });
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<PickupData>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取列表数据失败");
            }
        });
    }

    @Override
    protected void initDatas() {
        getData();
    }

}
