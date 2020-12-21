package com.ocean.dsgoods.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.CarApi;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.RegisterResult;
import com.ocean.dsgoods.tools.TitleManger;
import com.ocean.dsgoods.tools.ToastUtil;
import com.ocean.dsgoods.tools.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        TitleManger manger=TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("注册");
        manger.setBack();
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
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;

    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;

    @OnClick({R.id.layout_upload_license, R.id.tv_phone_code, R.id.tv_user_agreement, R.id.btn_register_commit,R.id.tv_to_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_upload_license:
                // 3、调用从图库选取图片方法
                //权限判断
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到相册
                    gotoPhoto();
                }
                break;
            case R.id.tv_phone_code:
                if (TextUtils.isEmpty(etPhoneNum.getText().toString())) {
                    ToastUtil.showToast("请输入手机号码");
                    return;
                }
                if (!Utils.isMobileNO(etPhoneNum.getText().toString())) {
                    ToastUtil.showToast("手机号码格式错误");
                    return;
                }
                getPhoneCode(etPhoneNum.getText().toString());
                break;
            case R.id.tv_user_agreement:
                WebViewActivity.actionStart(this,"用户协议","http://www.baidu.com");
                break;
            case R.id.btn_register_commit:
                if (TextUtils.isEmpty(etCompanyName.getText().toString())) {
                    ToastUtil.showToast("请输入公司名称");
                    return;
                }

                if (TextUtils.isEmpty(etPhoneNum.getText().toString())) {
                    ToastUtil.showToast("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(etPassword.getText().toString())) {
                    ToastUtil.showToast("请输入密码");
                    return;
                }
                if (TextUtils.isEmpty(etPhoneCode.getText().toString())) {
                    ToastUtil.showToast("请输入验证码");
                    return;
                }
                if (cbReadStatus.isChecked() == false) {
                    ToastUtil.showToast("请阅读并勾选用户注册协议");
                    return;
                }
                register();
                break;
            case R.id.tv_to_login:
                PasswordLoginActivity.actionStart(this);
                break;

        }
    }
    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Log.d("evan", "*****************打开图库********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }

    /**
     * 打开截图界面
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", 0);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }
    private String cropImagePath;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    cropImagePath = Utils.getRealFilePathFromUri(this, uri);
                    tvCompanyLicense.setText(cropImagePath.substring(cropImagePath.lastIndexOf("/") + 1));
                }
                break;
        }
    }


    private void register() {

        CarApi carApi = HttpUtil.createRequest(TAG, BaseUrl.getInstence().getUserRegisterUrl());
        Call<ApiResponse<RegisterResult>> register;
        if (TextUtils.isEmpty(cropImagePath)) {
            register = carApi.register(etCompanyName.getText().toString(),etPhoneNum.getText().toString(),etPhoneCode.getText().toString()
                    ,etPassword.getText().toString());
        } else {
            Map<String, RequestBody> params = new HashMap<>();
            //以下参数是伪代码，参数需要换成自己服务器支持的
            params.put("company_name", convertToRequestBody(etCompanyName.getText().toString()));
            params.put("phone", convertToRequestBody(etPhoneNum.getText().toString()));
            params.put("code", convertToRequestBody(etPhoneCode.getText().toString()));
            params.put("password", convertToRequestBody(etPassword.getText().toString()));
            File file = new File(cropImagePath);
            MultipartBody.Part part = filesToMultipartBodyPart(file);
            register = carApi.register(params, part);
        }
        register.enqueue(new Callback<ApiResponse<RegisterResult>>() {
            @Override
            public void onResponse(Call<ApiResponse<RegisterResult>> call, Response<ApiResponse<RegisterResult>> response) {
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        CompanyRegisterCommitSuccessActivity.actionStart(CompanyRegisterActivity.this, response.body().getData().getCompany_no());
                        finish();
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<RegisterResult>> call, Throwable t) {
                ToastUtil.showToast("网络异常:上传失败");
            }
        });
    }

    private RequestBody convertToRequestBody(String param) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), param);
        return requestBody;
    }

    private MultipartBody.Part filesToMultipartBodyPart(File file) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("license", file.getName(), requestBody);
        return part;
    }
    private void getPhoneCode(String phoneNum) {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().sendSMS()).sendSMS(phoneNum).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    TimeCount time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
                    time.start();// 开始计时
                    ToastUtil.showToast("验证码已发送，注意查收");
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取验证码失败");
            }
        });
    }

    // /* 定义一个倒计时的内部类 */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // 计时过程显示
            try {
                tvPhoneCode.setEnabled(false);
                tvPhoneCode.setClickable(false);
                tvPhoneCode.setText(millisUntilFinished / 1000 + "秒");
            } catch (Exception e) {

            }

        }

        @Override
        public void onFinish() {// 计时完毕时触发
            try {
                tvPhoneCode.setEnabled(true);
                tvPhoneCode.setText("重新验证");
                tvPhoneCode.setClickable(true);
                tvPhoneCode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPhoneCode(etPhoneNum.getText().toString());
                    }
                });
            } catch (Exception E) {
            }

        }

    }
}
