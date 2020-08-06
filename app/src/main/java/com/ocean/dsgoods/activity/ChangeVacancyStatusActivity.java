package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.ChangeVacancyStatusAdapter;
import com.ocean.dsgoods.tools.RecyclerViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by James on 2020/7/28.
 * 返箱空置状态
 */
public class ChangeVacancyStatusActivity extends BaseActivity {

    ChangeVacancyStatusAdapter adapter;
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
    @BindView(R.id.btn_change_status)
    Button btnChangeStatus;
    @BindView(R.id.layout_bottom)
    RelativeLayout layoutBottom;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ChangeVacancyStatusActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_change_vacancy_status;
    }

    @Override
    protected void initViews() {
adapter=new ChangeVacancyStatusAdapter(this);
        RecyclerViewHelper.initRecyclerViewV(this,rvStaff,false,adapter);
    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.btn_change_status)
    public void onViewClicked() {
    }
}
