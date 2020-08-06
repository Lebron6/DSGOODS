package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.CreatReturnBoxAdapter;
import com.ocean.dsgoods.tools.RecyclerViewHelper;
import butterknife.BindView;

/**
 * Created by James on 2020/7/30.
 * 新建返箱计划
 */
public class NewReturnPlanActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rv_plan)
    RecyclerView rvPlan;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, NewReturnPlanActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_new_return_plan;
    }

    @Override
    protected void initViews() {
        CreatReturnBoxAdapter adapter = new CreatReturnBoxAdapter(this);
        RecyclerViewHelper.initRecyclerViewV(this, rvPlan, false, adapter);
    }

    @Override
    protected void initDatas() {

    }

}
