package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/11/12.
 */
public class AlreadyAddGoods {/**
 * code : 1
 * msg : 请求成功
 * time : 1605243642
 * data : [{"id":"446","num":"15","pnum":"1","g_id":"164","already_jnum":"0","already_num":"0","name":"货物名称test","good_type":"1小小的"}]
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
         * id : 446
         * num : 15
         * pnum : 1
         * g_id : 164
         * already_jnum : 0
         * already_num : 0
         * name : 货物名称test
         * good_type : 1小小的
         */

        private String id;
        private String num;
        private String pnum;
        private String g_id;
        private String already_jnum;
        private String already_num;
        private String name;
        private String good_type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getPnum() {
            return pnum;
        }

        public void setPnum(String pnum) {
            this.pnum = pnum;
        }

        public String getG_id() {
            return g_id;
        }

        public void setG_id(String g_id) {
            this.g_id = g_id;
        }

        public String getAlready_jnum() {
            return already_jnum;
        }

        public void setAlready_jnum(String already_jnum) {
            this.already_jnum = already_jnum;
        }

        public String getAlready_num() {
            return already_num;
        }

        public void setAlready_num(String already_num) {
            this.already_num = already_num;
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
