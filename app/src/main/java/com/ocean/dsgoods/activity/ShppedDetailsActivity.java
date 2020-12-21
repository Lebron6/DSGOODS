package com.ocean.dsgoods.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.dialog.RejectContractRemarksDialog;
import com.ocean.dsgoods.dialog.RejectShippedRemarksDialog;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.ShppedDetailsData;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.TitleManger;
import com.ocean.dsgoods.tools.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/7/3.
 * 我发运的详情
 */
public class ShppedDetailsActivity extends BaseActivity {

    public static final String WA_ID = "wa_id";
    public static final String TYPE = "type";
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_reject_time)
    TextView tvRejectTime;
    @BindView(R.id.tc_reject_content)
    TextView tcRejectContent;
    @BindView(R.id.layout_t)
    LinearLayout layoutT;
    @BindView(R.id.tv_ydh)
    TextView tvYdh;
    @BindView(R.id.iv_copy)
    ImageView ivCopy;
    @BindView(R.id.tv_cyf)
    TextView tvCyf;
    @BindView(R.id.iv_ddh)
    ImageView ivDdh;
    @BindView(R.id.layout_top)
    LinearLayout layoutTop;
    @BindView(R.id.txt_t)
    TextView txtT;
    @BindView(R.id.txt_s)
    TextView txtS;
    @BindView(R.id.layout_line)
    RelativeLayout layoutLine;
    @BindView(R.id.tv_name_t)
    TextView tvNameT;
    @BindView(R.id.tv_phone_t)
    TextView tvPhoneT;
    @BindView(R.id.tv_addr_t)
    TextView tvAddrT;
    @BindView(R.id.layout_t_info)
    LinearLayout layoutTInfo;
    @BindView(R.id.tv_name_s)
    TextView tvNameS;
    @BindView(R.id.tv_phone_s)
    TextView tvPhoneS;
    @BindView(R.id.tv_addr_s)
    TextView tvAddrS;
    @BindView(R.id.layout_s_info)
    LinearLayout layoutSInfo;
    @BindView(R.id.layout_addr)
    RelativeLayout layoutAddr;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_fhdw)
    TextView tvFhdw;
    @BindView(R.id.layout_time)
    RelativeLayout layoutTime;
    @BindView(R.id.view_line_2)
    View viewLine2;
    @BindView(R.id.tv_shdw)
    TextView tvShdw;
    @BindView(R.id.tv_zzl)
    TextView tvZzl;
    @BindView(R.id.tv_ztj)
    TextView tvZtj;
    @BindView(R.id.tv_gldd)
    TextView tvGldd;
    @BindView(R.id.tv_ysdh)
    TextView tvYsdh;
    @BindView(R.id.tv_shsj)
    TextView tvShsj;
    @BindView(R.id.tv_yqddsj)
    TextView tvYqddsj;
    @BindView(R.id.tv_fwfs)
    TextView tvFwfs;
    @BindView(R.id.tv_ysfs)
    TextView tvYsfs;
    @BindView(R.id.tv_yssx)
    TextView tvYssx;
    @BindView(R.id.tv_clyq)
    TextView tvClyq;
    @BindView(R.id.tv_jsfs)
    TextView tvJsfs;
    @BindView(R.id.tv_xdfs)
    TextView tvXdfs;
    @BindView(R.id.tv_xdsj)
    TextView tvXdsj;
    @BindView(R.id.tv_bxsmjz)
    TextView tvBxsmjz;
    @BindView(R.id.tv_sfwj)
    TextView tvSfwj;
    @BindView(R.id.tv_bz)
    TextView tvBz;
    @BindView(R.id.layout_bottom)
    RelativeLayout layoutBottom;
    @BindView(R.id.tv_zzzl)
    TextView tvZzzl;
    @BindView(R.id.tv_zzjs)
    TextView tvZzjs;
    @BindView(R.id.zztj)
    TextView zztj;
    @BindView(R.id.tv_zzsl)
    TextView tvZzsl;
    @BindView(R.id.layout_see_list)
    LinearLayout layoutSeeList;
    @BindView(R.id.sv)
    ScrollView sv;
    @BindView(R.id.layout_see_map)
    LinearLayout layoutSeeMap;
    @BindView(R.id.layout_hdqr)
    LinearLayout layoutHdqr;
    @BindView(R.id.layout_fp)
    LinearLayout layoutFp;
    @BindView(R.id.layout_reject)
    LinearLayout layoutReject;
    @BindView(R.id.layout_accept)
    LinearLayout layoutAccept;
    @BindView(R.id.layout_sl)
    LinearLayout layoutSl;
    @BindView(R.id.layout_bottom_one)
    RelativeLayout layoutBottomOne;


    public static void actionStart(Context context, String wa_id, String type) {
        Intent intent = new Intent(context, ShppedDetailsActivity.class);
        intent.putExtra(WA_ID, wa_id);
        intent.putExtra(TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger = TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("详情");
        manger.setBack();
        getDetailsData();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_shpped_details;
    }

    @Override
    protected void initViews() {

    }
String tlogistics_mobile;
    private void getDetailsData() {
        HttpUtil.createRequest(BaseUrl.getInstence().waybillInfo()).waybillInfo(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(TYPE), getIntent().getStringExtra(WA_ID)).enqueue(new Callback<ApiResponse<ShppedDetailsData>>() {
            @Override
            public void onResponse(Call<ApiResponse<ShppedDetailsData>> call, Response<ApiResponse<ShppedDetailsData>> response) {
                if (response.body().getCode() == 1) {
                    tlogistics_mobile=response.body().getData().getTlogistics_mobile();
                    tvYdh.setText(response.body().getData().getWa_num());
                    tvCyf.setText(response.body().getData().getTlogistics_name());
                    tvNameT.setText(response.body().getData().getSend_address().getTel_name());
                    tvPhoneT.setText(response.body().getData().getSend_address().getPhone());
                    tvAddrT.setText(response.body().getData().getSend_address().getAddrs());
                    tvNameS.setText(response.body().getData().getSaddress().getTel_name());
                    tvPhoneS.setText(response.body().getData().getSaddress().getPhone());
                    tvAddrS.setText(response.body().getData().getSaddress().getAddrs());
                    tvFhdw.setText(response.body().getData().getSend_name());
                    tvShdw.setText(response.body().getData().getShipper_name());
                    tvZzl.setText(response.body().getData().getAllWeight());
                    tvZtj.setText(response.body().getData().getAllVolume());
                    tvGldd.setText(response.body().getData().getAorder());
                    tvYsdh.setText(response.body().getData().getYnum());
                    tvShsj.setText(response.body().getData().getStartTime() + "--" + response.body().getData().getEndTime());
                    tvYqddsj.setText(response.body().getData().getArrivalTime());
                    tvFwfs.setText(response.body().getData().getDelivery());
                    tvYsfs.setText(response.body().getData().getTransport());
                    tvYssx.setText(response.body().getData().getTsTime());
                    tvClyq.setText( response.body().getData().getCarLength() + "," + response.body().getData().getNeedCar());
                    tvJsfs.setText(response.body().getData().getSettleSty());
                    tvXdfs.setText(response.body().getData().getPOrder());
                    tvXdsj.setText(response.body().getData().getOrderTime());
                    tvBxsmjz.setText(response.body().getData().getPrice());
                    tvSfwj.setText(response.body().getData().getFileInfo());
                    tvBz.setText(response.body().getData().getRemarks());
                    tvZzjs.setText(response.body().getData().getGoodsJnum());
                    tvZzsl.setText(response.body().getData().getGoodsNum());
                    tvZzzl.setText(response.body().getData().getAllWeight()+"kg");
                    zztj.setText(response.body().getData().getAllVolume()+"m³");
                    layoutHdqr.setVisibility(View.GONE);
                    switch (response.body().getData().getStatus()) {//1受理(新建)   2分配  3驳回 5途中  12签收   15完成 16作废"
                        case "1":
                            layoutSl.setVisibility(View.VISIBLE);
                            layoutFp.setVisibility(View.GONE);
                            break;
                        case "2":
                            layoutSl.setVisibility(View.GONE);
                            layoutFp.setVisibility(View.VISIBLE);

                            break;
                        case "3":
                            layoutT.setVisibility(View.VISIBLE);
                            layoutSl.setVisibility(View.GONE);
                            layoutFp.setVisibility(View.GONE);
                            tvRejectTime.setText(response.body().getData().getRejectTime());
                            tcRejectContent.setText(response.body().getData().getReject_remarks());
                            break;
                        case "5":

                            break;
                        case "12":
                            if (response.body().getData().getIsTurn().equals("1")) {//是否需要上缴回单 1：是 2：否
                                layoutHdqr.setVisibility(View.VISIBLE);
                            } else {
                                layoutHdqr.setVisibility(View.GONE);
                            }
                            break;
                        case "15":
                            layoutT.setVisibility(View.GONE);
                            layoutSl.setVisibility(View.GONE);
                            layoutFp.setVisibility(View.VISIBLE);
                            layoutHdqr.setVisibility(View.GONE);
                            break;
                        case "16":

                            break;
                    }
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<ShppedDetailsData>> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取详情数据失败");
            }
        });
    }

    @Override
    protected void initDatas() {
        getDetailsData();
    }


    @OnClick({R.id.iv_ddh,R.id.layout_see_list, R.id.layout_see_map, R.id.layout_hdqr, R.id.layout_fp, R.id.layout_reject, R.id.layout_accept, R.id.layout_sl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_ddh:
                if (TextUtils.isEmpty(tlogistics_mobile)) {
                    ToastUtil.showToast("暂不支持客服热线");
                    return;
                }
                callPhone(tlogistics_mobile);
                break;case R.id.layout_see_list:
                QuotationShppedActivity.actionStart(this, getIntent().getStringExtra(WA_ID),getIntent().getStringExtra(TYPE));
                break;
            case R.id.layout_see_map:
                OperationTrackActivity.actionStart(this, getIntent().getStringExtra(TYPE),getIntent().getStringExtra(WA_ID));
                break;
            case R.id.layout_hdqr:
                HandInReceiptActivity.actionStart(this, getIntent().getStringExtra(WA_ID));
                break;
            case R.id.layout_fp:
                break;
            case R.id.layout_reject:
                RejectShippedRemarksDialog dialog = new RejectShippedRemarksDialog(this, R.style.Theme_AppCompat_Dialog, getIntent().getStringExtra(WA_ID));
                dialog.show();
                break;
            case R.id.layout_accept:
                sure();
                break;
            case R.id.layout_sl:
                break;
        }
    }

    private void callPhone(String tlogistics_mobile) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + tlogistics_mobile);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

    private void sure() {
        HttpUtil.createRequest(BaseUrl.getInstence().shipperConfirm()).shipperConfirm(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(WA_ID)).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getCode() == 1) {
                    ToastUtil.showToast("通过成功");
                    finish();
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ToastUtil.showToast("网络异常:操作失败");
            }
        });

    }
}
