package com.ocean.dsgoods.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 作废装车原因
 */
public class ToVoidLoadingRemarksDialog extends Dialog {


    TextView tvSure;
    TextView tvCancel;
    EditText etRemarks;
    private Activity context;
    private String id;

    public ToVoidLoadingRemarksDialog(Context context) {
        super(context);
    }

    public ToVoidLoadingRemarksDialog(Activity context, int theme, String id) {
        super(context, theme);
        this.context = context;
        this.id = id;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tovoid_remarks);
        initView();
    }

    private void initView() {
        tvCancel=findViewById(R.id.tv_cancel);
        tvSure=findViewById(R.id.tv_sure);
        etRemarks=findViewById(R.id.et_remarks);
        tvCancel.setOnClickListener(onClickListener);
        tvSure.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_cancel:
                    dismiss();
                    break;
                case R.id.tv_sure:
                    if (TextUtils.isEmpty(etRemarks.getText().toString())){
                        ToastUtil.showToast("请输入原因");
                        return;
                    }
                    reject(id);
                    break;
            }
        }
    };

    private void reject(String id) {
        HttpUtil.createRequest("ToVoidBillRemarksDialog", BaseUrl.getInstence().affirmTake()).affirmTake(PreferenceUtils.getInstance().getUserToken(),id,"2",etRemarks.getText().toString()).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body()!=null){
                    if (response.body().getCode()==1){
                        ToastUtil.showToast("操作成功");
                        dismiss();
                        context.finish();
                    }else{
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
ToastUtil.showToast("网络异常:操作失败");
            }
        });
    }


}
