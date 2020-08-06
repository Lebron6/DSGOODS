package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;

import com.ocean.dsgoods.R;

/**
 * Created by James on 2020/6/28.
 */
public class CommitBillSuccessActivity extends BaseActivity {


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CommitBillSuccessActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

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
}
