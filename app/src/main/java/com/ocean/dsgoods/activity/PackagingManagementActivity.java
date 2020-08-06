package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.GoodsManagementAdapter;
import com.ocean.dsgoods.adapter.PackageManagementAdapter;
import com.ocean.dsgoods.tools.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by James on 2020/7/9.
 * 包装管理
 */
public class PackagingManagementActivity extends BaseActivity {


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

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, PackagingManagementActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_packaing_management;
    }

    @Override
    protected void initViews() {
        PackageManagementAdapter adapter = new PackageManagementAdapter(this);
        RecyclerViewHelper.initRecyclerViewV(this, rvPackage, false, adapter);
        adapter.setOnItemClickLitener(new PackageManagementAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
//                ContractDetailsActivity.actionStart(getActivity());
            }
        });
    }

    @Override
    protected void initDatas() {

    }
}
