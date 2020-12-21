package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/11/27.
 */
public class Trail {
    /**
     * code : 1
     * msg : 请求成功
     * time : 1600939687
     * data : [{"city":"邯郸","title":"干线签收","time":"1970-01-01 08:00","img":[],"remarks":"","signuser":"joycetest","status":2,"sign_status":"签收状态 1正常 2异常"}]
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
         * city : 邯郸
         * title : 干线签收
         * time : 1970-01-01 08:00
         * img : []
         * remarks :
         * signuser : joycetest
         * status : 2
         * sign_status : 签收状态 1正常 2异常
         */

        private String city;
        private String title;
        private String time;
        private String remarks;
        private String signuser;
        private int status;
        private String sign_status;
        private String img;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getSignuser() {
            return signuser;
        }

        public void setSignuser(String signuser) {
            this.signuser = signuser;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSign_status() {
            return sign_status;
        }

        public void setSign_status(String sign_status) {
            this.sign_status = sign_status;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
