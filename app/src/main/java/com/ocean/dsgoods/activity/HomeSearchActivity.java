package com.ocean.dsgoods.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by James on 2020/11/23.
 * 首页搜索
 */
public class HomeSearchActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_home_search;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.back, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                break;
            case R.id.tv_search:
                break;
        }
    }
}
