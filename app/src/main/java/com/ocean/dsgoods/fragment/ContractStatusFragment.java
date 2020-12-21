package com.ocean.dsgoods.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liaoinstan.springview.widget.SpringView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.ContractDetailsActivity;
import com.ocean.dsgoods.adapter.ContarctStatusAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.ContractList;
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
 * Created by James on 2020/7/9.
 * 合同管理
 */
public class ContractStatusFragment extends BaseFragment {
    @BindView(R.id.rv_contarct)
    RecyclerView rvContarct;
    @BindView(R.id.sv_contarct)
    SpringView svContarct;
    private String type;
    ContarctStatusAdapter adapter;

    public ContractStatusFragment() {
    }

    @SuppressLint("ValidFragment")
    public ContractStatusFragment(String type) {
        this.type = type;
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_contarct_status;
    }
    private void initSpringViewStyle() {
        svContarct.setType(SpringView.Type.FOLLOW);
        svContarct.setListener(onFreshListener);
        svContarct.setHeader(new SimpleHeader(getActivity()));
        svContarct.setFooter(new SimpleFooter(getActivity()));
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
    List<ContractList.ListBean> listBeans=new ArrayList<>();
    private void getData() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().constractCheckList()).contractList(PreferenceUtils.getInstance().getUserToken(),type+"",page+"").enqueue(new Callback<ApiResponse<ContractList>>() {
            @Override
            public void onResponse(Call<ApiResponse<ContractList>> call, Response<ApiResponse<ContractList>> response) {
                if (svContarct != null) {
                    svContarct.onFinishFreshAndLoad();
                }
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        if (page == 1) {
                            listBeans.clear();
                            listBeans.addAll(response.body().getData().getList());
                            RecyclerViewHelper.initRecyclerViewV(getActivity(), rvContarct, false, adapter);
                        } else {
                            listBeans.addAll(response.body().getData().getList());
                        }
                        adapter.setDatas(listBeans);

                        adapter.setOnItemClickLitener(new ContarctStatusAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                ContractDetailsActivity.actionStart(getActivity(),listBeans.get(position).getCo_id());
                            }
                        });
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<ContractList>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取合同失败");
            }
        });
    }

    @Override
    protected void initViews() {
        initSpringViewStyle();
        adapter = new ContarctStatusAdapter(getActivity());

    }

    @Override
    protected void initDatas() {
        getData();
    }
}
