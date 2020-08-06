package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ocean.dsgoods.R;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by James on 2020/6/28.
 * 企业注册
 */
public class CompanyRegisterActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.et_company_name)
    EditText etCompanyName;
    @BindView(R.id.tv_company_license)
    TextView tvCompanyLicense;
    @BindView(R.id.layout_upload_license)
    LinearLayout layoutUploadLicense;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_phone_code)
    EditText etPhoneCode;
    @BindView(R.id.tv_phone_code)
    TextView tvPhoneCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.cb_read_status)
    CheckBox cbReadStatus;
    @BindView(R.id.tv_user_agreement)
    TextView tvUserAgreement;
    @BindView(R.id.btn_register_commit)
    Button btnRegisterCommit;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CompanyRegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_company_register;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.layout_upload_license, R.id.tv_phone_code, R.id.tv_user_agreement, R.id.btn_register_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_upload_license:
                break;
            case R.id.tv_phone_code:
                break;
            case R.id.tv_user_agreement:
                WebViewActivity.actionStart(this,"用户协议","http://www.baidu.com");
                break;
            case R.id.btn_register_commit:
                RegisterSuccessActivity.actionStart(this);
                break;
        }
    }
}
