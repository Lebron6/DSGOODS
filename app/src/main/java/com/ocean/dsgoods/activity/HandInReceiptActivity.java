package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.GridAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.datepicker.CustomDatePicker;
import com.ocean.dsgoods.datepicker.DateFormatUtils;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.UpLoadResult;
import com.ocean.dsgoods.photopicker.PhotoPickerActivity;
import com.ocean.dsgoods.photopicker.PhotoPickerIntent;
import com.ocean.dsgoods.photopicker.PhotoPreviewActivity;
import com.ocean.dsgoods.photopicker.PhotoPreviewIntent;
import com.ocean.dsgoods.photopicker.SelectModel;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.TitleManger;
import com.ocean.dsgoods.tools.ToastUtil;
import com.ocean.dsgoods.tools.Utils;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/10/26.
 * 回单上缴
 */
public class HandInReceiptActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_chose_data)
    TextView tvChoseData;
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.btn_sign_for)
    Button btnSignFor;
    private GridAdapter gridAdapter;
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    public static final String WA_ID = "wa_id";
    private ArrayList<String> imagePaths = new ArrayList<>();
    private ArrayList<String> upLoadPath ;
    private CustomDatePicker mTimerPicker;

    public static void actionStart(Context context, String wa_id) {
        Intent intent = new Intent(context, HandInReceiptActivity.class);
        intent.putExtra(WA_ID, wa_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("回单上缴");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_hand_in_receipt;
    }

    @Override
    protected void initViews() {
        imagePaths.add("000000");
        gridAdapter = new GridAdapter(this);
        gridAdapter.setData(imagePaths);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(onItemClickListener);
        initTimerPicker();
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String imgs = (String) parent.getItemAtPosition(position);
            if ("000000".equals(imgs)) {
                PhotoPickerIntent intent = new PhotoPickerIntent(HandInReceiptActivity.this);
                intent.setSelectModel(SelectModel.MULTI);
                intent.setShowCarema(true); // 是否显示拍照
                intent.setMaxTotal(3); // 最多选择照片数量，默认为6
                intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                startActivityForResult(intent, REQUEST_CAMERA_CODE);
            } else {
                PhotoPreviewIntent intent = new PhotoPreviewIntent(HandInReceiptActivity.this);
                intent.setCurrentItem(position);
                intent.setPhotoPaths(imagePaths);
                startActivityForResult(intent, REQUEST_PREVIEW_CODE);
            }
        }
    };

    @Override
    protected void initDatas() {

    }


    private void loadAdpater(ArrayList<String> paths) {
        if (imagePaths != null && imagePaths.size() > 0) {
            imagePaths.clear();
        }
        if (paths.contains("000000")) {
            paths.remove("000000");
        }
        paths.add("000000");
        imagePaths.addAll(paths);
        gridAdapter.setData(imagePaths);
        gridView.setAdapter(gridAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ArrayList<String> list=new ArrayList<>();
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    Log.d(TAG, "list: " + "list = [" + list.size());
                    loadAdpater(list);
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    list = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    Log.d(TAG, "ListExtra: " + "ListExtra = [" + list.size());
                    loadAdpater(list);
                    break;

            }
            if (list != null && list.size() > 0) {
                upLoadPath = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    File file = new File(list.get(i));
                    MultipartBody.Part part = filesToMultipartBodyPart(file);
                    HttpUtil.createRequest(TAG, BaseUrl.getInstence().dispatchUploads()).dispatchUploads(PreferenceUtils.getInstance().getUserToken(), "1", part).enqueue(new Callback<ApiResponse<UpLoadResult>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<UpLoadResult>> call, Response<ApiResponse<UpLoadResult>> response) {
                            if (response.body() != null) {
                                if (response.body().getCode() == 1) {
                                    upLoadPath.add(response.body().getData().getFilepath());
                                } else {
                                    ToastUtil.showToast(response.body().getMsg());
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<UpLoadResult>> call, Throwable t) {
//                            ToastUtil.showToast("网络异常:上传失败");
                        }
                    });
                }
            }
        } else {
            Log.e("选项为空时执行", "---");
        }

    }

    private MultipartBody.Part filesToMultipartBodyPart(File file) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        return part;
    }

    @OnClick({R.id.tv_chose_data, R.id.btn_sign_for})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_chose_data:
                // 日期格式为yyyy-MM-dd HH:mm
                mTimerPicker.show(tvChoseData.getText().toString());
                break;
            case R.id.btn_sign_for:
                if (TextUtils.isEmpty(upLoadPath.toString())){
                    ToastUtil.showToast("请选择图片");
                    return;
                }
               upLoad();
                break;
        }
    }

    private void upLoad() {
        String result = upLoadPath.toString().substring(1,upLoadPath.toString().length()-1);
        Log.e("result",result);
        HttpUtil.createRequest(TAG,BaseUrl.getInstence().shipperAffirm()).shipperAffirm(PreferenceUtils.getInstance().getUserToken(),getIntent().getStringExtra(WA_ID),result,tvChoseData.getText().toString()).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode()==1){
                    ToastUtil.showToast("确认成功");
                    finish();
                }else{
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网路异常："+t.toString());
            }
        });
    }

    private void initTimerPicker() {
        String beginTime = "2018-10-17 18:00";
        String endTime = DateFormatUtils.long2Str(System.currentTimeMillis(), true);
        tvChoseData.setText(endTime);
        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mTimerPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                tvChoseData.setText(DateFormatUtils.long2Str(timestamp, true));
            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mTimerPicker.setCancelable(true);
        // 显示时和分
        mTimerPicker.setCanShowPreciseTime(true);
        // 允许循环滚动
        mTimerPicker.setScrollLoop(true);
        // 允许滚动动画
        mTimerPicker.setCanShowAnim(true);
    }
}
