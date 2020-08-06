package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.PackageManagementAdapter;
import com.ocean.dsgoods.adapter.StaffManagementAdapter;
import com.ocean.dsgoods.tools.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by James on 2020/7/22.
 * 员工管理
 */
public class StaffManagementActivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rv_staff)
    RecyclerView rvStaff;
    @BindView(R.id.cb_select_all)
    CheckBox cbSelectAll;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_finish)
    Button btnFinish;
    @BindView(R.id.layout_bottom)
    RelativeLayout layoutBottom;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, StaffManagementActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_staff_management;
    }

    @Override
    protected void initViews() {
        StaffManagementAdapter adapter = new StaffManagementAdapter(this);
        RecyclerViewHelper.initRecyclerViewV(this, rvStaff, false, adapter);
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.btn_delete, R.id.btn_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_delete:
                break;
            case R.id.btn_finish:
                break;
        }
    }
}
