package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.SettingLoadingAdapter;
import com.ocean.dsgoods.tools.RecyclerViewHelper;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by James on 2020/7/15.
 * 设置装车
 */
public class SettingLoadingActivity extends BaseActivity implements TagFlowLayout.OnSelectListener {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.txt_num)
    TextView txtNum;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.txt_car_num)
    TextView txtCarNum;
    @BindView(R.id.tv_car_num)
    TextView tvCarNum;
    @BindView(R.id.txt_driver)
    TextView txtDriver;
    @BindView(R.id.tv_driver)
    TextView tvDriver;
    @BindView(R.id.view_line_t)
    View viewLineT;
    @BindView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    @BindView(R.id.layout_top)
    LinearLayout layoutTop;
    @BindView(R.id.rv_loading)
    RecyclerView rvLoading;
    @BindView(R.id.layout_center)
    LinearLayout layoutCenter;
    @BindView(R.id.btn_scan_counting)
    Button btnScanCounting;
    @BindView(R.id.btn_manual_counting)
    Button btnManualCounting;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.layout_button)
    LinearLayout layoutButton;

    @Override
    protected void initTitle() {

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SettingLoadingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_setting_loading;
    }

    @Override
    protected void initViews() {
        SettingLoadingAdapter adapter = new SettingLoadingAdapter(this);
        RecyclerViewHelper.initRecyclerViewV(this, rvLoading, false, adapter);
        List<String> strings = new ArrayList<>();
        strings.add("132132468515");
        strings.add("132132468515");
        strings.add("132132468515");
        strings.add("132132468515");
        strings.add("132132468515");
        strings.add("132132468515");
        idFlowlayout.setAdapter(new TagAdapter<String>(strings) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(SettingLoadingActivity.this).inflate(R.layout.tv_label,
                        idFlowlayout, false);
                tv.setText(s);
                return tv;
            }
        });
        idFlowlayout.setOnSelectListener(SettingLoadingActivity.this);
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.btn_scan_counting, R.id.btn_manual_counting, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_scan_counting:
                break;
            case R.id.btn_manual_counting:
                ManualCountingActivity.actionStart(this);
                break;
            case R.id.btn_commit:
                break;
        }
    }

    @Override
    public void onSelected(Set<Integer> selectPosSet) {
        Log.e("选中了这些", selectPosSet.toString());
    }
}
