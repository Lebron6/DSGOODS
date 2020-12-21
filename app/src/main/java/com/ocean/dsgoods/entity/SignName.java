package com.ocean.dsgoods.entity;

/**
 * Created by James on 2020/11/27.
 */
public class SignName {
    /**
     * code : 1
     * msg : 请求成功
     * time : 1600585647
     * data : 签收人名称
     */

    private int code;
    private String msg;
    private int time;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
