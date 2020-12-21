package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.SettingResult;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.TitleManger;
import com.ocean.dsgoods.tools.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/8/5.
 * 绑定新邮箱
 */
public class BindNewEmailGetCodeActivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_now_email_num)
    TextView tvNowEmailNum;
    @BindView(R.id.et_email_num)
    EditText etEmailNum;
    @BindView(R.id.btn_next)
    Button btnNext;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, BindNewEmailGetCodeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_bind_new_email_get_code;
    }

    @Override
    protected void initViews() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().getInfo()).settinInfo(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<ApiResponse<SettingResult>>() {
            @Override
            public void onResponse(Call<ApiResponse<SettingResult>> call, Response<ApiResponse<SettingResult>> response) {
                if (response.body().getCode() == 1) {
                    if (TextUtils.isEmpty(response.body().getData().getEmail())){
                        tvNowEmailNum.setText("");
                    }else{
                        tvNowEmailNum.setText("当前邮箱地址："+response.body().getData().getEmail());
                    }
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<SettingResult>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取个人数据失败");
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etEmailNum.getText().toString())) {
            ToastUtil.showToast("请输入邮箱");
            return;
        }

commit();
    }

    private void commit() {
        btnNext.setEnabled(false);
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().sendMail()).sendEmailCode(PreferenceUtils.getInstance().getUserToken(), etEmailNum.getText().toString()).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        ToastUtil.showToast("验证码已发送,注意查收");
                        BindNewEmailActivity.actionStart(BindNewEmailGetCodeActivity.this,etEmailNum.getText().toString());
                        finish();

                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                        btnNext.setEnabled(true);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:验证码发送失败");
                btnNext.setEnabled(true);
            }
        });
    }

}
