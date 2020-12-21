package com.ocean.dsgoods.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liaoinstan.springview.widget.SpringView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.BillDetailsActivity;
import com.ocean.dsgoods.adapter.BillAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.BillList;
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
 * 提货单列表
 */
public class BillListFragment extends BaseFragment {

    @BindView(R.id.rv_bill)
    RecyclerView rvBill;
    @BindView(R.id.sv_bill)
    SpringView svBill;
    private String statusStr;
    private BillAdapter adapter;

    @SuppressLint("ValidFragment")
    public BillListFragment(String statusStr) {
        this.statusStr = statusStr;
    }

    public BillListFragment() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_bill_list;
    }

    @Override
    protected void initViews() {
        initSpringViewStyle();
        adapter = new BillAdapter(getActivity());

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

    List<BillList.ListBean> listBeans = new ArrayList<>();

    private void getData() {
        String status = null;
        switch (statusStr) {
            case "全部":
                status = "0";
                break;
            case "新建":
                status = "1";
                break;
            case "确认":
                status = "2";
                break;
            case "调度":
                status = "3";
                break;
            case "出发":
                status = "4";
                break;
            case "到达":
                status = "5";
                break;
            case "装车":
                status = "6";
                break;
            case "驳回":
                status = "7";
                break;
        }
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().take_list()).take_list(PreferenceUtils.getInstance().getUserToken(), page + "",  "1", status + "").enqueue(new Callback<ApiResponse<BillList>>() {
            @Override
            public void onResponse(Call<ApiResponse<BillList>> call, Response<ApiResponse<BillList>> response) {
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
                        adapter.setOnItemClickLitener(new BillAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                BillDetailsActivity.actionStart(getActivity(), listBeans.get(position).getDp_id());
                            }
                        });

                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<BillList>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取列表数据失败");
            }
        });
    }

    @Override
    protected void initDatas() {
        getData();
    }

}
