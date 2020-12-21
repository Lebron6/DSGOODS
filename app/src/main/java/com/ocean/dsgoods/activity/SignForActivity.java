package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.GridAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.SignName;
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
 * 签收
 */
public class SignForActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rb_z)
    RadioButton rbZ;
    @BindView(R.id.rb_j)
    RadioButton rbJ;
    @BindView(R.id.rg_type)
    RadioGroup rgType;
    @BindView(R.id.layout_center)
    RelativeLayout layoutCenter;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.layout_remarks)
    LinearLayout layoutRemarks;
    @BindView(R.id.et_remarks)
    EditText etRemarks;
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.btn_sign_for)
    Button btnSignFor;
    @BindView(R.id.tv_sign_name)
    TextView tvSignName;
    private GridAdapter gridAdapter;
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private ArrayList<String> imagePaths = new ArrayList<>();

    public static final String WA_ID="wa_id";

    public static void actionStart(Context context,String wa_id) {
        Intent intent = new Intent(context, SignForActivity.class);
        intent.putExtra(WA_ID,wa_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("签收");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_sign_for;
    }

    @Override
    protected void initViews() {
        imagePaths.add("000000");
        gridAdapter = new GridAdapter(this);
        gridAdapter.setData(imagePaths);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(onItemClickListener);
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String imgs = (String) parent.getItemAtPosition(position);
            if ("000000".equals(imgs)) {
                PhotoPickerIntent intent = new PhotoPickerIntent(SignForActivity.this);
                intent.setSelectModel(SelectModel.MULTI);
                intent.setShowCarema(true); // 是否显示拍照
                intent.setMaxTotal(3); // 最多选择照片数量，默认为6
                intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                startActivityForResult(intent, REQUEST_CAMERA_CODE);
            } else {
                PhotoPreviewIntent intent = new PhotoPreviewIntent(SignForActivity.this);
                intent.setCurrentItem(position);
                intent.setPhotoPaths(imagePaths);
                startActivityForResult(intent, REQUEST_PREVIEW_CODE);
            }
        }
    };

    @Override
    protected void initDatas() {
        getSignName();
    }

    private void getSignName() {

        HttpUtil.createRequest(BaseUrl.getInstence().getSignName()).signName(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<SignName>() {
            @Override
            public void onResponse(Call<SignName> call, Response<SignName> response) {
                if (response.body().getCode() == 1) {
                    tvSignName.setText("签收人："+response.body().getData());
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<SignName> call, Throwable t) {
                ToastUtil.showToast("获取签收人失败");
            }
        });
    }


    @OnClick({R.id.btn_sure, R.id.btn_sign_for})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sure:
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

    private ArrayList<String> upLoadPath ;
    ArrayList<String> list;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
           list=new ArrayList<>();
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
                upLoadPath= new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    File file = new File(list.get(i));
                    MultipartBody.Part part = filesToMultipartBodyPart(file);
                    HttpUtil.createRequest(TAG, BaseUrl.getInstence().dispatchUploads()).dispatchUploads(PreferenceUtils.getInstance().getUserToken(), "2", part).enqueue(new Callback<ApiResponse<UpLoadResult>>() {
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

    private void upLoad() {
        String result = upLoadPath.toString().substring(1,upLoadPath.toString().length()-1);
        Log.e("提交的图片数量:",upLoadPath.size()+"");
        Log.e("result",result);
        HttpUtil.createRequest(TAG,BaseUrl.getInstence().shipperSign()).shipperSign(PreferenceUtils.getInstance().getUserToken(),getIntent().getStringExtra(WA_ID),result,rbZ.isChecked()==true?"1":"2",etRemarks.getText().toString()).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode()==1){
                    ToastUtil.showToast("签收成功");
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
}
