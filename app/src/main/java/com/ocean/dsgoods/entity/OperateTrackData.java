package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/10/15.
 */
public class OperateTrackData {
    /**
     * code : 1
     * msg : 请求成功
     * time : 1602742522
     * data : [{"id":"18","status":"1","name":"东方夜","phone":"15288880001","num":"苏D00008","acceptTime":"--","setoutTime":"--","arriveTime":"--","loadingTime":"--","finishTime":"--","track":[]}]
     */

    private int code;
    private String msg;
    private int time;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 18
         * status : 1
         * name : 东方夜
         * phone : 15288880001
         * num : 苏D00008
         * acceptTime : --
         * setoutTime : --
         * arriveTime : --
         * loadingTime : --
         * finishTime : --
         * track : []
         */

        private String id;
        private String status;
        private String name;
        private String phone;
        private String num;
        private String acceptTime;
        private String setoutTime;
        private String arriveTime;
        private String loadingTime;
        private String finishTime;
        private List<?> track;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getAcceptTime() {
            return acceptTime;
        }

        public void setAcceptTime(String acceptTime) {
            this.acceptTime = acceptTime;
        }

        public String getSetoutTime() {
            return setoutTime;
        }

        public void setSetoutTime(String setoutTime) {
            this.setoutTime = setoutTime;
        }

        public String getArriveTime() {
            return arriveTime;
        }

        public void setArriveTime(String arriveTime) {
            this.arriveTime = arriveTime;
        }

        public String getLoadingTime() {
            return loadingTime;
        }

        public void setLoadingTime(String loadingTime) {
            this.loadingTime = loadingTime;
        }

        public String getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(String finishTime) {
            this.finishTime = finishTime;
        }

        public List<?> getTrack() {
            return track;
        }

        public void setTrack(List<?> track) {
            this.track = track;
        }
    }
}
