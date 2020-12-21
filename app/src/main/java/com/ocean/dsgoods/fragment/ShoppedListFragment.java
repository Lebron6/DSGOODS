package com.ocean.dsgoods.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liaoinstan.springview.widget.SpringView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.BillDetailsActivity;
import com.ocean.dsgoods.activity.ShppedDetailsActivity;
import com.ocean.dsgoods.adapter.BillAdapter;
import com.ocean.dsgoods.adapter.ShippedAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.BillList;
import com.ocean.dsgoods.entity.WayList;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.RecyclerViewHelper;
import com.ocean.dsgoods.tools.SimpleFooter;
import com.ocean.dsgoods.tools.SimpleHeader;
import com.ocean.dsgoods.tools.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/6/29.
 */
public class ShoppedListFragment extends BaseFragment {

    @BindView(R.id.rv_bill)
    RecyclerView rvBill;
    @BindView(R.id.sv_bill)
    SpringView svBill;
    private String statusStr;
    private ShippedAdapter adapter;

    @SuppressLint("ValidFragment")
    public ShoppedListFragment(String statusStr) {
        this.statusStr = statusStr;
    }

    public ShoppedListFragment() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_bill_list;
    }

    @Override
    protected void initViews() {
        initSpringViewStyle();
        adapter = new ShippedAdapter(getActivity(), 1);

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

    List<WayList.ListBean> listBeans = new ArrayList<>();

    private void getData() {
        String status = null;
        switch (statusStr) {
            case "全部":
                status = "";
                break;
            case "受理":
                status = "1";
                break;
            case "驳回":
                status = "3";
                break;
            case "分配":
                status = "2";
                break;
            case "途中":
                status = "5";
                break;
            case "回单确认":
                status = "12";
                break;
        }
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().waybillList()).waybillList(PreferenceUtils.getInstance().getUserToken(), page + "", "1", status + "").enqueue(new Callback<ApiResponse<WayList>>() {
            @Override
            public void onResponse(Call<ApiResponse<WayList>> call, Response<ApiResponse<WayList>> response) {
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
                        adapter.setOnItemClickLitener(new ShippedAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                ShppedDetailsActivity.actionStart(getActivity(),listBeans.get(position).getWa_id(),"1");
                            }
                        });
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<WayList>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取列表数据失败");
            }
        });
    }

    @Override
    protected void initDatas() {
        getData();
    }

}
