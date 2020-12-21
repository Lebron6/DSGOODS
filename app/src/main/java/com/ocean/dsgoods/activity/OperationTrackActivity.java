package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.PolylineOptions;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.TrackAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.Track;
import com.ocean.dsgoods.entity.Trail;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.RecyclerViewHelper;
import com.ocean.dsgoods.tools.TitleManger;
import com.ocean.dsgoods.tools.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/11/20.
 * 轨迹查询
 */
public class OperationTrackActivity extends BaseActivity {

    @BindView(R.id.map)
    MapView mapView;
    @BindView(R.id.layout_show)
    LinearLayout layoutShow;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.layout_path)
    LinearLayout layoutPath;
    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
//            setUpMap();//画线
        }
    }

    public static final String TYPE = "type";
    public static final String WA_ID = "wa_id";

    public static void actionStart(Context context, String type,String wa_id) {
        Intent intent = new Intent(context, OperationTrackActivity.class);
        intent.putExtra(TYPE, type);
        intent.putExtra(WA_ID, wa_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleManger manger= TitleManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("查看轨迹");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_operation_track;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        getTrackData();

        getTrackViewData();
    }

    private void getTrackViewData() {
        HttpUtil.createRequest(BaseUrl.getInstence().trailList()).trailList(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(WA_ID),getIntent().getStringExtra(TYPE)).enqueue(new Callback<Trail>() {
            @Override
            public void onResponse(Call<Trail> call, Response<Trail> response) {
                if (response.body().getCode() == 1) {
                    TrackAdapter adapter = new TrackAdapter(OperationTrackActivity.this);
                    adapter.setDatas(response.body().getData());
                    RecyclerViewHelper.initRecyclerViewV(OperationTrackActivity.this, rvList, adapter);
                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<Trail> call, Throwable t) {
                ToastUtil.showToast("获取地址数据失败");
            }
        });


    }

    private void getTrackData() {

        HttpUtil.createRequest(BaseUrl.getInstence().mapTrailList()).mapTrailList(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(WA_ID),getIntent().getStringExtra(TYPE)).enqueue(new Callback<Track>() {
            @Override
            public void onResponse(Call<Track> call, Response<Track> response) {
                if (response.body().getCode() == 1) {

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        List<LatLng> latLngs = new ArrayList<LatLng>();
                        for (int j = 0; j < response.body().getData().get(i).size() ; j++) {
                            latLngs.add(new LatLng(Double.valueOf(response.body().getData().get(i).get(j).getLat()),Double.valueOf(response.body().getData().get(i).get(j).getLng())));
                        }
                    aMap.addPolyline(new PolylineOptions().
                                addAll(latLngs).width(10).color(Color.argb(255, 30,144,255)));
                    }

                } else {
                    ToastUtil.showToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<Track> call, Throwable t) {
                ToastUtil.showToast("获取地址数据失败");
            }
        });

    }

    @OnClick(R.id.layout_show)
    public void onViewClicked() {
                rvList.setVisibility(rvList.getVisibility()==View.GONE?View.VISIBLE:View.GONE);
    }
}
