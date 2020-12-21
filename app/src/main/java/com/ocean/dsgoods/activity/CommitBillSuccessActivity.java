package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.tools.AppManager;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.TitleManger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by James on 2020/6/28.
 */
public class CommitBillSuccessActivity extends BaseActivity {


    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.iv_commit_suc)
    ImageView ivCommitSuc;
    @BindView(R.id.layout_c)
    RelativeLayout layoutC;
    @BindView(R.id.btn_home)
    Button btnHome;
    @BindView(R.id.btn_see_bill_details)
    Button btnSeeBillDetails;
    @BindView(R.id.layout_button)
    LinearLayout layoutButton;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CommitBillSuccessActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger=TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("提交成功");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_commit_bill_success;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.btn_home, R.id.btn_see_bill_details})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_home:
                AppManager.getAppManager().AppExit(CommitBillSuccessActivity.this);
                MainActivity.actionStart(this);
                break;
            case R.id.btn_see_bill_details:
                break;
        }
    }
}
