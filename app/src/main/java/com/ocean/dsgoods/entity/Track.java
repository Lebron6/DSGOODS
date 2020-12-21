package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/11/20.
 */
public class Track {


    /**
     * code : 1
     * msg : 请求成功
     * time : 1605077689
     * data : [[{"lng":"112.29255972053","lat":"31.717849346598"},{"lng":"112.29255972053","lat":"31.717849346598"},{"lng":"112.29255972053","lat":"31.717849346598"},{"lng":"112.29255972053","lat":"31.717849346598"}]]
     */

    private int code;
    private String msg;
    private int time;
    private List<List<DataBean>> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<List<DataBean>> getData() {
        return data;
    }

    public void setData(List<List<DataBean>> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * lng : 112.29255972053
         * lat : 31.717849346598
         */

        private String lng;
        private String lat;

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }
    }
}

