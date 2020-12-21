package com.ocean.dsgoods.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ocean.dsgoods.BuildConfig;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.callback.CityInterface;
import com.ocean.dsgoods.callback.OnItemClickListener;
import com.ocean.dsgoods.dialog.CommonPopWindow;
import com.ocean.dsgoods.entity.AddressSelectorReq;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.Area;
import com.ocean.dsgoods.entity.CompanyInfo;
import com.ocean.dsgoods.entity.ItemAddressReq;
import com.ocean.dsgoods.entity.UpLoadResult;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.TitleManger;
import com.ocean.dsgoods.tools.ToastUtil;
import com.ocean.dsgoods.tools.Utils;
import com.ocean.dsgoods.view.AddressSelector;
import com.ocean.dsgoods.view.UpDateIconPop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
 * Created by James on 2020/9/4.
 * 企业信息
 */
public class CompanyInfoActivity extends BaseActivity implements CommonPopWindow.ViewClickListener {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.et_company_num)
    EditText etCompanyNum;
    @BindView(R.id.txt_phone_bind)
    TextView txtPhoneBind;
    @BindView(R.id.et_company_name)
    EditText etCompanyName;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_sh)
    EditText etSh;
    @BindView(R.id.txt_wx_bind)
    TextView txtWxBind;
    @BindView(R.id.tv_company_addr)
    TextView tvCompanyAddr;
    @BindView(R.id.iv_down)
    ImageView ivDown;
    @BindView(R.id.layout_company_addr)
    RelativeLayout layoutCompanyAddr;
    @BindView(R.id.tv_dwdz)
    EditText tvDwdz;
    @BindView(R.id.tv_bank_name)
    EditText tvBankName;
    @BindView(R.id.tv_bank_num)
    EditText tvBankNum;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.view_line)
    View viewLine;
    private UpDateIconPop upDateIconPop;

    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    private File tempFile;
    private String imageUrl;
    private Area area;

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("企业信息");
        manger.setBack();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CompanyInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_company_info;
    }

    @Override
    protected void initViews() {
        getCompanyInfo();
        getArea();
    }

    private void getArea() {
        HttpUtil.createRequest(BaseUrl.getInstence().getArea()).getArea(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<Area>() {
            @Override
            public void onResponse(Call<Area> call, Response<Area> response) {
                area = response.body();
                if (area.getCode() == 1) {
                } else {
                    ToastUtil.showToast(area.getMsg());
                }
            }

            @Override
            public void onFailure(Call<Area> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取地址失败");
            }
        });
    }

    private void getCompanyInfo() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().getCompany()).getCompanyInfo(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<ApiResponse<CompanyInfo>>() {
            @Override
            public void onResponse(Call<ApiResponse<CompanyInfo>> call, Response<ApiResponse<CompanyInfo>> response) {
                if (response.body().getCode() == 1) {
                    etCompanyNum.setText(response.body().getData().getCompany_no());
                    etCompanyName.setText(response.body().getData().getS_name());
                    etPhoneNum.setText(response.body().getData().getS_mobile());
                    etSh.setText(response.body().getData().getS_tax_num());
                    tvDwdz.setText(response.body().getData().getS_address_detail());
                    tvBankNum.setText(response.body().getData().getS_bank_num());
                    tvBankName.setText(response.body().getData().getS_bank());
                    Glide.with(CompanyInfoActivity.this).load(response.body().getData().getUrl() + response.body().getData().getS_licenseimg()).into(ivPic);
                    imageUrl = response.body().getData().getS_licenseimg();
                    tvCompanyAddr.setText(response.body().getData().getS_address());
                    saveId[0]=response.body().getData().getS_province();
                    saveId[1]=response.body().getData().getS_city();
                    saveId[2]=response.body().getData().getS_town();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<CompanyInfo>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取设置数据失败");
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.iv_pic, R.id.layout_company_addr, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_pic:
                upDateIconPop = new UpDateIconPop(this, itemsOnClick);
                upDateIconPop.showAtLocation(viewLine, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.layout_company_addr:
                setAddressSelectorPopup(view);
                break;
            case R.id.btn_save:
                if (TextUtils.isEmpty(etCompanyName.getText().toString())) {
                    ToastUtil.showToast("请输入企业编号");
                    return;
                }
                if (TextUtils.isEmpty(etPhoneNum.getText().toString())) {
                    ToastUtil.showToast("请输入电话号码");
                    return;
                }
                if (TextUtils.isEmpty(etSh.getText().toString())) {
                    ToastUtil.showToast("请输入税号");
                    return;
                }
                if (TextUtils.isEmpty(tvCompanyAddr.getText().toString())) {
                    ToastUtil.showToast("请完善企业所在地信息");
                    return;
                }
                if (TextUtils.isEmpty(tvDwdz.getText().toString())) {
                    ToastUtil.showToast("请输入单位地址");
                    return;
                }
                if (TextUtils.isEmpty(tvBankName.getText().toString())) {
                    ToastUtil.showToast("请输入开户行");
                    return;
                }
                if (TextUtils.isEmpty(tvBankNum.getText().toString())) {
                    ToastUtil.showToast("请输入银行账号");
                    return;
                }
                commit();
                break;

        }
    }
    private String[] saveId = new String[3];
    private List<AddressSelectorReq.DatasBean.ChildrenBeanX> childrenBeanXList;
    private List<AddressSelectorReq.DatasBean.ChildrenBeanX.ChildrenBean> childrenBeans;
    /**
     * 设置弹出PopWindow
     * @param v
     */
    private void setAddressSelectorPopup(View v) {
        int screenHeigh = getResources().getDisplayMetrics().heightPixels;

        CommonPopWindow.newBuilder()
                .setView(R.layout.pop_address_selector_bottom)
                .setAnimationStyle(R.style.AnimUp)
                .setBackgroundDrawable(new BitmapDrawable())
                .setSize(ViewGroup.LayoutParams.MATCH_PARENT, Math.round(screenHeigh * 0.6f))
                .setViewOnClickListener(this)
                .setBackgroundDarkEnable(true)
                .setBackgroundAlpha(0.7f)
                .setBackgroundDrawable(new ColorDrawable(999999))
                .build(this)
                .showAsBottom(v);
    }

    @Override
    public void getChildView(final PopupWindow mPopupWindow, View view, int mLayoutResId) {
        switch (mLayoutResId) {
            case R.layout.pop_address_selector_bottom:
                ImageView imageBtn = view.findViewById(R.id.img_guanbi);
                AddressSelector addressSelector = view.findViewById(R.id.address);

                //数据解析
                AddressSelectorReq addressSelectorReq = new AddressSelectorReq();
                List<AddressSelectorReq.DatasBean> datas=new ArrayList<>();
                for (int i = 0; i <area.getData().size() ; i++) {
                    AddressSelectorReq.DatasBean datasBean=new AddressSelectorReq.DatasBean();
                    datasBean.setDb_areaName(area.getData().get(i).getName());
                    datasBean.setDb_id(area.getData().get(i).getId());
                    datasBean.setDb_parentId(area.getData().get(i).getPid());
                    List<AddressSelectorReq.DatasBean.ChildrenBeanX> childrenBeanXES=new ArrayList<>();
                    for (int j = 0; j <area.getData().get(i).getChildren().size() ; j++) {
                        AddressSelectorReq.DatasBean.ChildrenBeanX childrenBeanX=new AddressSelectorReq.DatasBean.ChildrenBeanX();
                        childrenBeanX.setCb_id(area.getData().get(i).getChildren().get(j).getId());
                        childrenBeanX.setCb_areaName(area.getData().get(i).getChildren().get(j).getName());
                        childrenBeanX.setCb_parentId(area.getData().get(i).getChildren().get(j).getPid());
                        List<AddressSelectorReq.DatasBean.ChildrenBeanX.ChildrenBean> childrenBeans=new ArrayList<>();
                        for (int k = 0; k <area.getData().get(i).getChildren().get(j).getChildren().size() ; k++) {
                            AddressSelectorReq.DatasBean.ChildrenBeanX.ChildrenBean childrenBean=new AddressSelectorReq.DatasBean.ChildrenBeanX.ChildrenBean();
                            childrenBean.setAreaName(area.getData().get(i).getChildren().get(j).getChildren().get(k).getName());
                            childrenBean.setId(area.getData().get(i).getChildren().get(j).getChildren().get(k).getId());
                            childrenBean.setParentId(area.getData().get(i).getChildren().get(j).getChildren().get(k).getPid());
                            childrenBeans.add(childrenBean);
                        }
                        childrenBeanX.setCb_children(childrenBeans);
                        childrenBeanXES.add(childrenBeanX);
                    }
                    datasBean.setDb_children(childrenBeanXES);
                    datas.add(datasBean);
                }
                addressSelectorReq.setDatas(datas);
                //设置默认选择数据
                dealWithAddressSelector(addressSelector, addressSelectorReq.getDatas(), mPopupWindow);

                imageBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
                    }
                });


                break;
        }
    }
    private void dealWithAddressSelector(AddressSelector addressSelector, final List<AddressSelectorReq.DatasBean>
            addressSelectorList, final PopupWindow mPopupWindow) {
        final String[] sheng = new String[3];

        final ArrayList<ItemAddressReq> itemAddressReqs = getItemAddressSheng(addressSelectorList);
        addressSelector.setTabAmount(3);
        //设置数据
        addressSelector.setCities(itemAddressReqs);
        //设置Tab横线的颜色
        addressSelector.setLineColor(Color.parseColor("#D5A872"));
        //设置Tab文字默认颜色
        addressSelector.setTextEmptyColor(Color.parseColor("#000000"));
        //设置列表选中文字颜色
        addressSelector.setListTextSelectedColor(Color.parseColor("#D5A872"));
        //设置Tab文字选中的颜色
        addressSelector.setTextSelectedColor(Color.parseColor("#D5A872"));

        //设置列表的点击事件回调接口
        addressSelector.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void itemClick(AddressSelector addressSelector, CityInterface city, int tabPosition, int selecePos) {
                switch (tabPosition) {
                    case 0:
                        //设置省列表数据
                        sheng[0] = city.getCityName();
                        saveId[0] = addressSelectorList.get(selecePos).getDb_id();
                        childrenBeanXList = addressSelectorList.get(selecePos).getDb_children();
                        addressSelector.setCities(getItemAddressShi(childrenBeanXList));
                        break;
                    case 1:
                        //设置市列表数据
                        sheng[1] = city.getCityName();
                        saveId[1] = childrenBeanXList.get(selecePos).getCb_id();
                        childrenBeans = childrenBeanXList.get(selecePos).getCb_children();
                        addressSelector.setCities(getItemAddressQu(childrenBeans));
                        break;
                    case 2:
                        //设置区列表数据
                        sheng[2] = city.getCityName();
                        saveId[2] = childrenBeans.get(selecePos).getId();
                        tvCompanyAddr.setText(sheng[0] + sheng[1] + sheng[2] );
                        mPopupWindow.dismiss();
                        break;
                }
            }
        });


        //设置顶部tab的点击事件回调
        addressSelector.setOnTabSelectedListener(new AddressSelector.OnTabSelectedListener() {
            @Override
            public void onTabSelected(AddressSelector addressSelector, AddressSelector.Tab tab) {
                switch (tab.getIndex()) {
                    case 0:
                        addressSelector.setCities(itemAddressReqs);
                        break;
                    case 1:
                        addressSelector.setCities(getItemAddressShi(childrenBeanXList));
                        break;
                    case 2:
                        addressSelector.setCities(getItemAddressQu(childrenBeans));
                        break;
                }
            }

            @Override
            public void onTabReselected(AddressSelector addressSelector, AddressSelector.Tab tab) {

            }
        });
    }


    /**
     * 获取省的数据
     *
     * @param addressSelectorList
     * @return
     */
    @NonNull
    private ArrayList<ItemAddressReq> getItemAddressSheng(List<AddressSelectorReq.DatasBean> addressSelectorList) {
        final ArrayList<ItemAddressReq> itemAddressReqs = new ArrayList<>();
        for (int i = 0; i < addressSelectorList.size(); i++) {
            ItemAddressReq itemAddressReq = new ItemAddressReq();
            itemAddressReq.setAreaName(addressSelectorList.get(i).getDb_areaName());
            itemAddressReq.setId(addressSelectorList.get(i).getDb_id());
            itemAddressReq.setParentId(addressSelectorList.get(i).getDb_parentId());
            itemAddressReqs.add(itemAddressReq);
        }
        return itemAddressReqs;
    }


    /**
     * 获取市的数据
     *
     * @return
     */
    @NonNull
    private ArrayList<ItemAddressReq> getItemAddressShi(List<AddressSelectorReq.DatasBean.ChildrenBeanX> datas) {
        final ArrayList<ItemAddressReq> itemAddressReqs = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            ItemAddressReq itemAddressReq = new ItemAddressReq();
            itemAddressReq.setAreaName(datas.get(i).getCb_areaName());
            itemAddressReq.setId(datas.get(i).getCb_id());
            itemAddressReq.setParentId(datas.get(i).getCb_parentId());
            itemAddressReqs.add(itemAddressReq);
        }
        return itemAddressReqs;
    }

    /**
     * 获取区的数据
     *
     * @param addressSelectorList
     * @return
     */
    @NonNull
    private ArrayList<ItemAddressReq> getItemAddressQu(List<AddressSelectorReq.DatasBean.ChildrenBeanX.ChildrenBean> addressSelectorList) {
        final ArrayList<ItemAddressReq> itemAddressReqs = new ArrayList<>();
        for (int i = 0; i < addressSelectorList.size(); i++) {
            ItemAddressReq itemAddressReq = new ItemAddressReq();
            itemAddressReq.setAreaName(addressSelectorList.get(i).getAreaName());
            itemAddressReq.setId(addressSelectorList.get(i).getId());
            itemAddressReq.setParentId(addressSelectorList.get(i).getParentId());
            itemAddressReqs.add(itemAddressReq);
        }
        return itemAddressReqs;
    }


    private void commit() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().saveCompany()).saveCompany(PreferenceUtils.getInstance().getUserToken(), etPhoneNum.getText().toString(), etCompanyName.getText().toString(), etSh.getText().toString(), saveId[0], saveId[1], saveId[2], tvDwdz.getText().toString(), tvBankName.getText().toString(), tvBankNum.getText().toString(), imageUrl).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("企业信息更新成功");
                    finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:企业信息上传失败");
            }
        });
    }


    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_take_photo:                   //拍照取图
                    //权限判断
                    if (ContextCompat.checkSelfPermission(CompanyInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请WRITE_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(CompanyInfoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        //跳转到调用系统相机
                        gotoCamera();
                    }
                    break;
                case R.id.btn_chose:
                    // 3、调用从图库选取图片方法
                    //权限判断
                    if (ContextCompat.checkSelfPermission(CompanyInfoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请READ_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(CompanyInfoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                READ_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        //跳转到相册
                        gotoPhoto();
                    }
                    break;
            }
            upDateIconPop.dismiss();
        }
    };

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
     * 跳转到照相机
     */
    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        //创建拍照存储的图片文件
        tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Log.e("参数", BuildConfig.APPLICATION_ID + ".fileProvider");
            Uri contentUri = FileProvider.getUriForFile(CompanyInfoActivity.this, BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    /**
     * 检查文件是否存在
     */
    public static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
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
                    final String cropImagePath = Utils.getRealFilePathFromUri(CompanyInfoActivity.this, uri);

                    File file = new File(cropImagePath);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/png"), file);
                    MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);


                    HttpUtil.createRequest(TAG, BaseUrl.getInstence().uploadFile()).upLoadFile(PreferenceUtils.getInstance().getUserToken(), part).enqueue(new Callback<ApiResponse<UpLoadResult>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<UpLoadResult>> call, Response<ApiResponse<UpLoadResult>> response) {
                            if (response.body() != null) {
                                if (response.body().getCode() == 1) {
                                    Glide.with(CompanyInfoActivity.this).load(response.body().getData().getUrl() + response.body().getData().getPath()).into(ivPic);
                                    imageUrl = response.body().getData().getPath();
                                    ToastUtil.showToast("上传成功");
                                } else {
                                    ToastUtil.showToast(response.body().getMsg());
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<UpLoadResult>> call, Throwable t) {
                            ToastUtil.showToast("网络异常:上传失败");
                        }
                    });
                }
                break;
        }
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

}
