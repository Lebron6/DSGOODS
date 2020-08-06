package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;

import com.ocean.dsgoods.R;

/**
 * Created by James on 2020/7/21.
 * 二维码管理
 */
public class QRCodeManagementActivity extends BaseActivity{


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, QRCodeManagementActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_qrcode_management;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
