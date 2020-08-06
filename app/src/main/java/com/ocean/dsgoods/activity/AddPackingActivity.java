package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;

import com.ocean.dsgoods.R;

/**
 * Created by James on 2020/7/21.
 * 添加包装
 */
public class AddPackingActivity extends BaseActivity{

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AddPackingActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_add_packing;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
