package com.ocean.dsgoods.app;

import android.app.Application;
import android.content.Context;

import com.amap.api.maps.AMap;


/**
 * Created by James on 2020/4/7.
 */
public class DSGoodsAPP extends Application {

    private static Context application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

    }
    public static Context getApplication(){
        return application;
    }
  public static String PATH_LOGCAT;

}
