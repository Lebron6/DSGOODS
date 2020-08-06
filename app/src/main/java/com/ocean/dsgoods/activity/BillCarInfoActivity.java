package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.ocean.dsgoods.R;
import butterknife.BindView;

/**
 * Created by James on 2020/7/7.
 * 提货车辆信息
 */
public class BillCarInfoActivity extends BaseActivity implements RouteSearch.OnRouteSearchListener{

    @BindView(R.id.map_view)
    MapView mapView;
    private RouteSearch routeSearch;
    private LatLonPoint mStartPoint = new LatLonPoint(117.18, 116.335891);//起点，116.335891,39.942295
    private LatLonPoint mEndPoint = new LatLonPoint(39.995576, 26.336164);//终点，116.481288,39.995576
//    private LatLonPoint mStartPoint = new LatLonPoint(117.184811, 34.261792);//起点
//    private LatLonPoint mEndPoint = new LatLonPoint(52.030018, 116.481288);//终点
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, BillCarInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        if (mapView!=null){
            mapView.onResume();
        }
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_bill_car_info;
    }

    @Override
    protected void initViews() {
        AMap aMap = mapView.getMap();
        aMap.setMyLocationEnabled(true);
        routeSearch = new RouteSearch(this);
        routeSearch.setRouteSearchListener(this);
        RouteSearch.FromAndTo fromAndTo =new RouteSearch.FromAndTo(mStartPoint, mEndPoint);
        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, 0, null, null, "");
        routeSearch.calculateDriveRouteAsyn(query);// 异步路径规划步行模式查询
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        if (mapView!=null){
            mapView.onDestroy();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        if (mapView!=null){
            mapView.onPause();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }
}
