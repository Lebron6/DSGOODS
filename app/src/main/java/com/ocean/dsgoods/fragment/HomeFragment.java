package com.ocean.dsgoods.fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.CustomMarkerActivity;
import com.ocean.dsgoods.activity.GoodsManagementActivity;
import com.ocean.dsgoods.activity.IShippedItActivity;
import com.ocean.dsgoods.activity.ITookItActivity;
import com.ocean.dsgoods.activity.ScanBoxActivity;
import com.ocean.dsgoods.activity.ScanTurnoverBoxActivity;
import com.ocean.dsgoods.activity.ShppedDetailsActivity;
import com.ocean.dsgoods.adapter.NavPagerAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.GeoCoordinate;
import com.ocean.dsgoods.entity.HomeData;
import com.ocean.dsgoods.entity.HomeSearchData;
import com.ocean.dsgoods.entity.ScanResult;
import com.ocean.dsgoods.entity.ScanUpdata;
import com.ocean.dsgoods.fragment.dynamic.PackagingFragment;
import com.ocean.dsgoods.fragment.dynamic.TransportFragment;
import com.ocean.dsgoods.fragment.dynamic.WarehouseFragment;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.ToastUtil;
import com.ocean.dsgoods.tools.Utils;
import com.ocean.dsgoods.view.MyMapView;
import com.ocean.dsgoods.view.NavitationLayout;
import com.ocean.dsgoods.view.togglebutton.facebook.rebound.ui.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.github.xudaojie.qrcodelib.CaptureActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/6/29.
 * 首页
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.layout_waybill_inquiry)
    LinearLayout layoutWaybillInquiry;
    @BindView(R.id.layout_goods_management)
    LinearLayout layoutGoodsManagement;
    @BindView(R.id.layout_inventory_query)
    LinearLayout layoutInventoryQuery;
    @BindView(R.id.layout_system_board)
    LinearLayout layoutSystemBoard;
    @BindView(R.id.layout_pol)
    LinearLayout layoutPol;
    @BindView(R.id.layout_wait)
    LinearLayout layoutWait;
    @BindView(R.id.layout_tips)
    LinearLayout layoutTips;
    @BindView(R.id.nav_tap)
    NavitationLayout navTap;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;
    @BindView(R.id.layout_frag)
    LinearLayout layoutFrag;
    @BindView(R.id.layout_search)
    RelativeLayout layoutSearch;
    @BindView(R.id.tv_pol_num)
    TextView tvPolNum;
    @BindView(R.id.tv_sbbj)
    TextView tvSbbj;
    @BindView(R.id.layout_sbyj)
    LinearLayout layoutSbyj;
    @BindView(R.id.tv_ydcs)
    TextView tvYdcs;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.layout_ydcs)
    LinearLayout layoutYdcs;
    @BindView(R.id.tv_bzdq)
    TextView tvBzdq;
    @BindView(R.id.layout_bzdq)
    LinearLayout layoutBzdq;
    @BindView(R.id.tv_wait_num)
    TextView tvWaitNum;
    @BindView(R.id.tv_sl)
    TextView tvSl;
    @BindView(R.id.layout_sl)
    LinearLayout layoutSl;
    @BindView(R.id.tv_hdqr)
    TextView tvHdqr;
    @BindView(R.id.layout_hdqr)
    LinearLayout layoutHdqr;
    @BindView(R.id.tv_ydqs)
    TextView tvYdqs;
    @BindView(R.id.layout_ydqs)
    LinearLayout layoutYdqs;
    @BindView(R.id.tv_bill_num)
    TextView tvBillNum;
    @BindView(R.id.mv_home)
    MapView mvHome;
    Unbinder unbinder;
    AMap aMap;
    private Marker marker3;// 从地上生长的marker对象
    private HomeData homeData;
    private CameraUpdate mCameraUpdate;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public void initMapView(Bundle savedInstanceState) {
        super.initMapView(savedInstanceState);
        mvHome.onCreate(savedInstanceState);
        aMap = mvHome.getMap();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mvHome.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mvHome.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mvHome.onSaveInstanceState(outState);
    }

    @Override
    protected void initViews() {
        useMap = true;
        String[] strings = {"运输动态", "仓储动态", "包装动态"};
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TransportFragment());
        fragments.add(new WarehouseFragment());
        fragments.add(new PackagingFragment());
        NavPagerAdapter viewPagerAdapter2 = new NavPagerAdapter(getChildFragmentManager());
        viewPagerAdapter2.setData(fragments);
        vpFragment.setAdapter(viewPagerAdapter2);
        vpFragment.setCurrentItem(0);
        navTap.setViewPager(getActivity(), strings, vpFragment, R.color.colorMainBlack, R.color.colorMain, 14, 16, 0, 55, true);
        navTap.setBgLine(getActivity(), 0, R.color.colorMain);
        navTap.setNavLine(getActivity(), 2, R.color.colorWhite, 0);
    }

    @Override
    protected void initDatas() {

        HttpUtil.createRequest(TAG, BaseUrl.getInstence().shipperHome()).shipperHome(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<ApiResponse<HomeData>>() {
            @Override
            public void onResponse(Call<ApiResponse<HomeData>> call, Response<ApiResponse<HomeData>> response) {
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        homeData = response.body().getData();
                        tvPolNum.setText("(" + homeData.getTotal_warning() + ")");
                        tvWaitNum.setText("(" + homeData.getTotal_accept() + ")");
                        tvSbbj.setText(homeData.getWarning());
                        tvBzdq.setText(homeData.getPast_num());
                        tvSl.setText(homeData.getAccept_num());
                        tvHdqr.setText(homeData.getAffirm_num());
                        tvYdqs.setText(homeData.getSign_num());
                        tvBillNum.setText(homeData.getWaybill_num());
                        List<GeoCoordinate> geoCoordinates=new ArrayList<>();
                        if (homeData.getLocation() != null && homeData.getLocation().size() > 0) {
                            for (int i = 0; i < homeData.getLocation().size(); i++) {
                                GeoCoordinate geoCoordinate=new GeoCoordinate(Double.valueOf(homeData.getLocation().get(i).getLat()), Double.valueOf(homeData.getLocation().get(i).getLng()));
                                geoCoordinates.add(geoCoordinate);


                                Log.e("经纬度", (Double.valueOf(homeData.getLocation().get(i).getLat()) + ""));
                                LatLng latLng = new LatLng(Double.valueOf(homeData.getLocation().get(i).getLat()), Double.valueOf(homeData.getLocation().get(i).getLng()));
                                aMap.addMarker(new MarkerOptions().position(
                                        latLng).icon(
                                        BitmapDescriptorFactory.fromResource(R.mipmap.icon_position)));

                            }
                        }
                        GeoCoordinate point = Utils.getCenterPointFromListOfCoordinates(geoCoordinates);//根据坐标数组获取中心点
                        mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(point.getLatitude(),point.getLongitude()),3,30,0));
                        aMap.moveCamera(mCameraUpdate);
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<HomeData>> call, Throwable t) {
                ToastUtil.showToast("未查询到相关结果");
            }
        });
    }
    /**
     * 处理二维码扫描结果
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }
            if (requestCode == SCAN) {
                String s = bundle.getString("result");
                Log.e("是否接收", s);
                ScanResult scanResult = new Gson().fromJson(s, ScanResult.class);
                if (scanResult.getType().equals("revolve")) {//周转箱
                    ScanTurnoverBoxActivity.actionStart(getActivity(),scanResult.getNum(),scanResult.getRv_id());
                }else{
                    ScanBoxActivity.actionStart(getActivity(),scanResult.getNum(),scanResult.getPk_id());
                }
            }
        }
    }
    private static final int SCAN = 96;//扫码
    @OnClick({R.id.layout_sl,R.id.layout_hdqr,R.id.layout_ydqs,R.id.iv_search, R.id.layout_to_map_details, R.id.iv_scan, R.id.layout_waybill_inquiry, R.id.layout_goods_management, R.id.layout_inventory_query, R.id.layout_system_board, R.id.layout_tips})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                Intent ykIntent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(ykIntent, SCAN);
                break;

                case R.id.layout_sl:
                    IShippedItActivity.actionStart(getActivity());
                break;  case R.id.layout_hdqr:
                    IShippedItActivity.actionStart(getActivity());
                break;

                case R.id.layout_ydqs:
                    ITookItActivity.actionStart(getActivity());
                break;
            case R.id.layout_to_map_details:
                if (homeData != null && homeData.getLocation() != null && homeData.getLocation().size() > 0) {
                    CustomMarkerActivity.actionStart(getActivity(), new Gson().toJson(homeData));
                } else {
                    ToastUtil.showToast("暂无标记信息");
                    return;
                }

                break;
            case R.id.iv_search:
                if (TextUtils.isEmpty(etSearch.getText().toString())) {
                    ToastUtil.showToast("请输入单号");
                    return;
                }
                search();
                break;
            case R.id.layout_waybill_inquiry:
                break;
            case R.id.layout_goods_management:
                GoodsManagementActivity.actionStart(getActivity());
                break;
            case R.id.layout_inventory_query:
                break;
            case R.id.layout_system_board:
                break;
            case R.id.layout_tips:
                break;
        }
    }

    private void search() {

        HttpUtil.createRequest(BaseUrl.getInstence().homeSearch()).homeSearch(PreferenceUtils.getInstance().getUserToken(), etSearch.getText().toString()).enqueue(new Callback<ApiResponse<HomeSearchData>>() {
            @Override
            public void onResponse(Call<ApiResponse<HomeSearchData>> call, Response<ApiResponse<HomeSearchData>> response) {
                if (response.body().getCode() == 1) {
                    ShppedDetailsActivity.actionStart(getActivity(),response.body().getData().getWa_id(),response.body().getData().getType());
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<HomeSearchData>> call, Throwable t) {
                ToastUtil.showToast("网络异常:搜索失败");
            }
        });
    }


}
