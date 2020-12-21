package com.ocean.dsgoods.entity;

/**
 * Created by James on 2020/11/13.
 */
public class ScanResult {

    /**
     * type : normal
     * pk_id : 8
     * num : PB202010293820001
     */

    private String type;
    private String pk_id;
    private String rv_id;
    private String num;


    public String getRv_id() {
        return rv_id;
    }

    public void setRv_id(String rv_id) {
        this.rv_id = rv_id;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPk_id() {
        return pk_id;
    }

    public void setPk_id(String pk_id) {
        this.pk_id = pk_id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
