package com.ocean.dsgoods.entity;

/**
 * Created by James on 2020/11/13.
 */
public class ScanUpdata {
    /**
     * code : 1
     * msg : 此箱已被绑过
     * time : 1605254980
     * data : {"status_type":"1103","name":"货物名称test","good_type":"1小小的"}
     */

    private int code;
    private String msg;
    private int time;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * status_type : 1103
         * name : 货物名称test
         * good_type : 1小小的
         */

        private String status_type;
        private String name;
        private String good_type;

        public String getStatus_type() {
            return status_type;
        }

        public void setStatus_type(String status_type) {
            this.status_type = status_type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGood_type() {
            return good_type;
        }

        public void setGood_type(String good_type) {
            this.good_type = good_type;
        }
    }
}
