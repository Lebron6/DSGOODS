package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/10/27.
 */
public class WayList {


        /**
         * current_page : 1
         * per_page : 10
         * has_more : false
         * total : 1
         * pageall : 1
         * list : [{"wa_id":"225","wa_num":"Y202010282381","start_city":"松原市","end_city":"北京市","shipper_name":"无锡雪两有限公司","tlogistics_name":"常州三人禾物流有限公司","goods_jnum":"1","send_time":"2010-10-10 10:00","arrivalTime":"2010-10-10 10:00","status":2,"status_name":"分配"}]
         */

        private int current_page;
        private int per_page;
        private boolean has_more;
        private int total;
        private int pageall;
        private List<ListBean> list;

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageall() {
            return pageall;
        }

        public void setPageall(int pageall) {
            this.pageall = pageall;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * wa_id : 225
             * wa_num : Y202010282381
             * start_city : 松原市
             * end_city : 北京市
             * shipper_name : 无锡雪两有限公司
             * tlogistics_name : 常州三人禾物流有限公司
             * goods_jnum : 1
             * send_time : 2010-10-10 10:00
             * arrivalTime : 2010-10-10 10:00
             * status : 2
             * status_name : 分配
             */

            private String wa_id;
            private String wa_num;
            private String start_city;
            private String end_city;
            private String shipper_name;
            private String tlogistics_name;
            private String goods_jnum;
            private String goodsNum;

            public String getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(String goodsNum) {
                this.goodsNum = goodsNum;
            }

            private String send_time;
            private String arrivalTime;
            private int status;
            private String status_name;

            public String getWa_id() {
                return wa_id;
            }

            public void setWa_id(String wa_id) {
                this.wa_id = wa_id;
            }

            public String getWa_num() {
                return wa_num;
            }

            public void setWa_num(String wa_num) {
                this.wa_num = wa_num;
            }

            public String getStart_city() {
                return start_city;
            }

            public void setStart_city(String start_city) {
                this.start_city = start_city;
            }

            public String getEnd_city() {
                return end_city;
            }

            public void setEnd_city(String end_city) {
                this.end_city = end_city;
            }

            public String getShipper_name() {
                return shipper_name;
            }

            public void setShipper_name(String shipper_name) {
                this.shipper_name = shipper_name;
            }

            public String getTlogistics_name() {
                return tlogistics_name;
            }

            public void setTlogistics_name(String tlogistics_name) {
                this.tlogistics_name = tlogistics_name;
            }

            public String getGoods_jnum() {
                return goods_jnum;
            }

            public void setGoods_jnum(String goods_jnum) {
                this.goods_jnum = goods_jnum;
            }

            public String getSend_time() {
                return send_time;
            }

            public void setSend_time(String send_time) {
                this.send_time = send_time;
            }

            public String getArrivalTime() {
                return arrivalTime;
            }

            public void setArrivalTime(String arrivalTime) {
                this.arrivalTime = arrivalTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getStatus_name() {
                return status_name;
            }

            public void setStatus_name(String status_name) {
                this.status_name = status_name;
            }
        }
}
