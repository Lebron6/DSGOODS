package com.ocean.dsgoods.tools;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.ocean.dsgoods.entity.GeoCoordinate;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by James on 2020/8/17.
 */
public class Utils {

    public static GeoCoordinate getCenterPointFromListOfCoordinates(List<GeoCoordinate> geoCoordinateList) {
        int total = geoCoordinateList.size();
        double X = 0, Y = 0, Z = 0;
        for(GeoCoordinate g: geoCoordinateList)
        {
            double lat, lon, x, y, z;
            lat = g.getLatitude() * Math.PI / 180;
            lon = g.getLongitude() * Math.PI / 180;
            x = Math.cos(lat) * Math.cos(lon);
            y = Math.cos(lat) * Math.sin(lon);
            z = Math.sin(lat);
            X += x;
            Y += y;
            Z += z;
        }
        X = X / total;
        Y = Y / total;
        Z = Z / total;
        double Lon = Math.atan2(Y, X);
        double Hyp = Math.sqrt(X * X + Y * Y);
        double Lat = Math.atan2(Z, Hyp);
        return new GeoCoordinate(Lat * 180 / Math.PI, Lon * 180 / Math.PI);

    }

        /**
         * 判断是否是手机号码
         */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
