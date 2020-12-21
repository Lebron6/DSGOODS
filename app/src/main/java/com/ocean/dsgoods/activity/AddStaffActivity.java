package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.RuleAdapter;
import com.ocean.dsgoods.adapter.StaffManagementAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.StaffAddInit;
import com.ocean.dsgoods.entity.StaffList;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.RecyclerViewHelper;
import com.ocean.dsgoods.tools.TitleManger;
import com.ocean.dsgoods.tools.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/8/28.
 * 添加员工
 */
public class AddStaffActivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.layout_cph)
    LinearLayout layoutCph;
    @BindView(R.id.et_xm)
    EditText etXm;
    @BindView(R.id.layout_xszh)
    LinearLayout layoutXszh;
    @BindView(R.id.et_sjh)
    EditText etSjh;
    @BindView(R.id.layout_ccspsj)
    LinearLayout layoutCcspsj;
    @BindView(R.id.et_dlmm)
    EditText etDlmm;
    @BindView(R.id.layout_zdzzzl)
    LinearLayout layoutZdzzzl;
    @BindView(R.id.et_yx)
    EditText etYx;
    @BindView(R.id.layout_cx)
    LinearLayout layoutCx;
    @BindView(R.id.et_bm)
    EditText etBm;
    @BindView(R.id.layout_zzs)
    LinearLayout layoutZzs;
    @BindView(R.id.et_gw)
    EditText etGw;
    @BindView(R.id.layout_gps)
    LinearLayout layoutGps;
    @BindView(R.id.rb_m)
    RadioButton rbM;
    @BindView(R.id.rb_w)
    RadioButton rbW;
    @BindView(R.id.rg_sex)
    RadioGroup rgSex;
    @BindView(R.id.layout_check)
    LinearLayout layoutCheck;
    @BindView(R.id.layout_top)
    LinearLayout layoutTop;
    @BindView(R.id.layout_center)
    LinearLayout layoutCenter;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.tv_keep_and_go)
    TextView tvKeepAndGo;
    @BindView(R.id.btn_keep)
    Button btnKeep;
    @BindView(R.id.layout_bottom_1)
    LinearLayout layoutBottom1;
    private RuleAdapter adapter;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AddStaffActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("添加员工");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_add_staff;
    }

    StaffAddInit body;

    @Override
    protected void initViews() {
        getRuleData();
    }

    private void getRuleData() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().initUser()).staffAddInit(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<StaffAddInit>() {
            @Override
            public void onResponse(Call<StaffAddInit> call, Response<StaffAddInit> response) {
                body = response.body();
                if (body.getCode() == 1) {
                    adapter = new RuleAdapter(AddStaffActivity.this);
                    adapter.setDatas(body);
                    RecyclerViewHelper.initRecyclerViewV(AddStaffActivity.this, rvList, false, adapter);
                } else {
                    ToastUtil.showToast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Call<StaffAddInit> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取员工列表失败");
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.tv_keep_and_go, R.id.btn_keep})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_keep_and_go:
                if (TextUtils.isEmpty(etXm.getText().toString())) {
                    ToastUtil.showToast("请输入姓名");
                    return;
                }
                if (TextUtils.isEmpty(etSjh.getText().toString())) {
                    ToastUtil.showToast("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(etDlmm.getText().toString())) {
                    ToastUtil.showToast("请输入登录密码");
                    return;
                }
                if (TextUtils.isEmpty(etYx.getText().toString())) {
                    ToastUtil.showToast("请输入邮箱");
                    return;
                }
                if (TextUtils.isEmpty(etBm.getText().toString())) {
                    ToastUtil.showToast("请输入部门");
                    return;
                }
                if (TextUtils.isEmpty(etGw.getText().toString())) {
                    ToastUtil.showToast("请输入岗位");
                    return;
                }
                commit(0);
                break;
            case R.id.btn_keep:
                commit(1);
                break;
        }
    }

    String auth;

    private void commit(final int type) {
        if (body != null && body.getData() != null && body.getData().size() > 0) {
            for (int i = 0; i < body.getData().size(); i++) {
                if (body.getData().get(i).getSelectStatus() == 1) {
                    auth = body.getData().get(i).getId();
                }
            }
        }
if(TextUtils.isEmpty(auth)){
    ToastUtil.showToast("请选择权限");
    return;
}
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().staffAdd()).staffAdd(PreferenceUtils.getInstance().getUserToken(), etXm.getText().toString(), etSjh.getText().toString(), etYx.getText().toString(), etDlmm.getText().toString(), rbM.isChecked() == true ? "1" : "2", etBm.getText().toString(), etGw.getText().toString(), "", auth).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("添加成功");
                    if (type == 0) {
                        AddStaffActivity.actionStart(AddStaffActivity.this);
                    }
                    finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:添加失败");
            }
        });
    }

}
