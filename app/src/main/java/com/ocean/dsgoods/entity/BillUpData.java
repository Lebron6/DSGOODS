package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/9/11.
 * 提交提货单信息
 */
public class BillUpData {
    private String t_id;
    private String dp_id;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDp_id() {
        return dp_id;
    }

    public void setDp_id(String dp_id) {
        this.dp_id = dp_id;
    }

    private String co_id;
    private String w_id;
    private String sa_id;
    private String startTime;
    private String endTime;
    private String arrivalTime;
    private String aorder;
    private String ynum;
    private String delivery;
    private String tsTime;
    private String transport;
    private String needCar;
    private String settleSty;
    private String allWeight;
    private String allVolume;
    private String fileInfo;
    private String remarks;
    private String price;
    private List<Good> goodList;

    public List<Good> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<Good> goodList) {
        this.goodList = goodList;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getCo_id() {
        return co_id;
    }

    public void setCo_id(String co_id) {
        this.co_id = co_id;
    }

    public String getW_id() {
        return w_id;
    }

    public void setW_id(String w_id) {
        this.w_id = w_id;
    }

    public String getSa_id() {
        return sa_id;
    }

    public void setSa_id(String sa_id) {
        this.sa_id = sa_id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getAorder() {
        return aorder;
    }

    public void setAorder(String aorder) {
        this.aorder = aorder;
    }

    public String getYnum() {
        return ynum;
    }

    public void setYnum(String ynum) {
        this.ynum = ynum;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getTsTime() {
        return tsTime;
    }

    public void setTsTime(String tsTime) {
        this.tsTime = tsTime;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getNeedCar() {
        return needCar;
    }

    public void setNeedCar(String needCar) {
        this.needCar = needCar;
    }

    public String getSettleSty() {
        return settleSty;
    }

    public void setSettleSty(String settleSty) {
        this.settleSty = settleSty;
    }

    public String getAllWeight() {
        return allWeight;
    }

    public void setAllWeight(String allWeight) {
        this.allWeight = allWeight;
    }

    public String getAllVolume() {
        return allVolume;
    }

    public void setAllVolume(String allVolume) {
        this.allVolume = allVolume;
    }

    public String getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(String fileInfo) {
        this.fileInfo = fileInfo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(String goodsList) {
        this.goodsList = goodsList;
    }

    private String goodsList;

    public static class Good {
        private String thg_id;
        private String g_id;
        private int num;

        public String getThg_id() {
            return thg_id;
        }

        public void setThg_id(String thg_id) {
            this.thg_id = thg_id;
        }

        public String getG_id() {
            return g_id;
        }

        public void setG_id(String g_id) {
            this.g_id = g_id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getPnum() {
            return pnum;
        }

        public void setPnum(int pnum) {
            this.pnum = pnum;
        }

        private int pnum;
    }
}
