package com.ocean.dsgoods.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.ocean.dsgoods.R;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by James on 2020/6/28.
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_company_num)
    EditText etCompanyNum;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.cb_show_password)
    CheckBox cbShowPassword;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.tv_company_register)
    TextView tvCompanyRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.tv_forget_password, R.id.tv_company_register, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_password:
                ForgetPasswordActivity.actionStart(this);
                break;
            case R.id.tv_company_register:
                CompanyRegisterActivity.actionStart(this);
                break;
            case R.id.btn_login:
                MainActivity.actionStart(this);
                break;
        }
    }
}
