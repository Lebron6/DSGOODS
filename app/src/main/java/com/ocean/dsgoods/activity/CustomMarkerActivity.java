package com.ocean.dsgoods.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapLoadedListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.AMap.OnMarkerDragListener;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.Projection;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.ScaleAnimation;
import com.google.gson.Gson;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.entity.GeoCoordinate;
import com.ocean.dsgoods.entity.HomeData;
import com.ocean.dsgoods.tools.ToastUtil;
import com.ocean.dsgoods.tools.Utils;

/**
 * AMapV2地图中简单介绍一些Marker的用法.
 */
public class CustomMarkerActivity extends Activity implements OnMarkerClickListener,
        OnInfoWindowClickListener,InfoWindowAdapter{
    private MarkerOptions markerOption;
    private TextView markerText;
    private AMap aMap;
    private MapView mapView;

    public static final String CONTENT="content";
    private HomeData homeData;

    public static void actionStart(Context context,String content) {
        Intent intent = new Intent(context, CustomMarkerActivity.class);
        intent .putExtra(CONTENT,content);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custommarker_activity);
        /*
         * 设置离线地图存储目录，在下载离线地图或初始化地图设置; 使用过程中可自行设置, 若自行设置了离线地图存储的路径，
         * 则需要在离线地图下载和使用地图页面都进行路径设置
         */
        // Demo中为了其他界面可以使用下载的离线地图，使用默认位置存储，屏蔽了自定义设置
        // MapsInitializer.sdcardDir =OffLineMapUtils.getSdCacheDir(this);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState); // 此方法必须重写
        init();
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
    }

    private void setUpMap() {
        aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
        aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
        addMarkersToMap();// 往地图上添加marker
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap() {
        Log.e("获取json",getIntent().getStringExtra(CONTENT));
        if (getIntent().getStringExtra(CONTENT)!=null){
            List<GeoCoordinate> geoCoordinates=new ArrayList<>();
            homeData = new Gson().fromJson(getIntent().getStringExtra(CONTENT), HomeData.class);
            for (int i = 0; i < homeData.getLocation().size(); i++) {
                GeoCoordinate geoCoordinate=new GeoCoordinate(Double.valueOf(homeData.getLocation().get(i).getLat()), Double.valueOf(homeData.getLocation().get(i).getLng()));
                geoCoordinates.add(geoCoordinate);
                LatLng latLng=new LatLng(Double.valueOf(homeData.getLocation().get(i).getLat()),Double.valueOf(homeData.getLocation().get(i).getLng()));

                MarkerOptions markerOptions = new MarkerOptions().anchor(0.5f, 0.5f)
                        .position(latLng).title(homeData.getLocation().get(i).getWa_num())
                        .snippet("收货方："+homeData.getLocation().get(i).getShippers_name()+"\n件数："+homeData.getLocation().get(i).getGoodsJnum()+"    "+"数量："+homeData.getLocation().get(i).getGoodsNum()).icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_position))
                        .draggable(true).period(10);
                ArrayList<MarkerOptions> markerOptionlst = new ArrayList<MarkerOptions>();
                markerOptionlst.add(markerOptions);
                aMap.addMarkers(markerOptionlst, true);
                aMap.setInfoWindowAdapter(this);

            }
            GeoCoordinate point = Utils.getCenterPointFromListOfCoordinates(geoCoordinates);//根据坐标数组获取中心点
             CameraUpdate mCameraUpdate= CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(point.getLatitude(),point.getLongitude()),3,30,0));
            aMap.moveCamera(mCameraUpdate);
        }
    }
    /**
     * 监听自定义infowindow窗口的infowindow事件回调
     */
    @Override
    public View getInfoWindow(Marker marker) {
        View infoWindow = getLayoutInflater().inflate(
                R.layout.custom_info_window, null);
        render(marker, infoWindow);
        return infoWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View infoWindow = getLayoutInflater().inflate(
                R.layout.custom_info_contents, null);
        render(marker, infoWindow);
        return infoWindow;
    }

    /**
     * 自定义infowinfow窗口
     */
    public void render(Marker marker, View view) {
        String title = marker.getTitle();
        TextView titleUi = ((TextView) view.findViewById(R.id.title));
        if (title != null) {
            SpannableString titleText = new SpannableString(title);

            titleUi.setText(titleText);

        } else {
            titleUi.setText("");
        }

        String snippet = marker.getSnippet();
        TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
        if (snippet != null) {
            SpannableString snippetText = new SpannableString(snippet);

            snippetUi.setText(snippetText);
        } else {
            snippetUi.setText("");
        }

    }
    /**
     * 对marker标注点点击响应事件
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {
        if (aMap != null) {
//            if (marker.equals(marker2)) {
//                jumpPoint(marker);
//            } else if (marker.equals(marker3)) {
//                growInto(marker);
//            }

        }

        return false;
    }


    /**
     * 监听点击infowindow窗口事件回调
     */
    @Override
    public void onInfoWindowClick(Marker marker) {
        ToastUtil.showToast("你点击了infoWindow窗口" + marker.getTitle());
        ToastUtil.showToast("当前地图可视区域内Marker数量:"
                + aMap.getMapScreenMarkers().size());
    }


}
