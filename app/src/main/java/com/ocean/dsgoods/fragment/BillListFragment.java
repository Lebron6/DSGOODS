package com.ocean.dsgoods.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.liaoinstan.springview.widget.SpringView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.BillDetailsActivity;
import com.ocean.dsgoods.adapter.BillAdapter;
import com.ocean.dsgoods.tools.RecyclerViewHelper;
import com.ocean.dsgoods.tools.SimpleFooter;
import com.ocean.dsgoods.tools.SimpleHeader;
import butterknife.BindView;

/**
 * Created by James on 2020/6/29.
 * 提货单列表
 */
public class BillListFragment extends BaseFragment {

    @BindView(R.id.rv_bill)
    RecyclerView rvBill;
    @BindView(R.id.sv_bill)
    SpringView svBill;
    private int type;
    private BillAdapter adapter;

    @SuppressLint("ValidFragment")
    public BillListFragment(int type) {
        this.type = type;
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
        adapter=new BillAdapter(getActivity());
        adapter.setOnItemClickLitener(onItemClickLitener);
        RecyclerViewHelper.initRecyclerViewV(getActivity(),rvBill,false,adapter);
    }

    BillAdapter.OnItemClickLitener onItemClickLitener=new BillAdapter.OnItemClickLitener() {
        @Override
        public void onItemClick(View view, int position) {
            BillDetailsActivity .actionStart(getActivity());
        }
    };

    private void initSpringViewStyle() {
        svBill.setType(SpringView.Type.FOLLOW);
        svBill.setListener(onFreshListener);
        svBill.setHeader(new SimpleHeader(getActivity()));
        svBill.setFooter(new SimpleFooter(getActivity()));
    }

    SpringView.OnFreshListener onFreshListener = new SpringView.OnFreshListener() {
        @Override
        public void onRefresh() {
//            page = 1;
//            getNoticeIndex();
        }

        @Override
        public void onLoadmore() {
//            page = page++;
//            getNoticeIndex();
        }
    };

    @Override
    protected void initDatas() {

    }

}
