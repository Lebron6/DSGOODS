package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/11/18.
 */
public class HomeData {

        /**
         * warning : 0
         * past_num : 0
         * overtime_num : 4
         * total_warning : 4
         * accept_num : 0
         * affirm_num : 1
         * sign_num : 4
         * total_accept : 5
         * location : [{"wa_id":"215","wa_num":"Y202010217095","goodsJnum":"0","goodsNum":"0","shippers_name":"测试吕公司","lat":"31.717849346598","lng":"112.29255972053"},{"wa_id":"216","wa_num":"Y202010227057","goodsJnum":"1","goodsNum":"1","shippers_name":"测试吕公司","lat":"31.717849346598","lng":"112.29255972053"},{"wa_id":"219","wa_num":"Y202010226734","goodsJnum":"1","goodsNum":"1","shippers_name":"测试吕公司","lat":"31.717849346598","lng":"112.29255972053"}]
         * waybill_num : 3
         */

        private String warning;
        private String past_num;
        private String overtime_num;
        private String total_warning;
        private String accept_num;
        private String affirm_num;
        private String sign_num;
        private String total_accept;
        private String waybill_num;
        private List<LocationBean> location;

        public String getWarning() {
            return warning;
        }

        public void setWarning(String warning) {
            this.warning = warning;
        }

        public String getPast_num() {
            return past_num;
        }

        public void setPast_num(String past_num) {
            this.past_num = past_num;
        }

        public String getOvertime_num() {
            return overtime_num;
        }

        public void setOvertime_num(String overtime_num) {
            this.overtime_num = overtime_num;
        }

        public String getTotal_warning() {
            return total_warning;
        }

        public void setTotal_warning(String total_warning) {
            this.total_warning = total_warning;
        }

        public String getAccept_num() {
            return accept_num;
        }

        public void setAccept_num(String accept_num) {
            this.accept_num = accept_num;
        }

        public String getAffirm_num() {
            return affirm_num;
        }

        public void setAffirm_num(String affirm_num) {
            this.affirm_num = affirm_num;
        }

        public String getSign_num() {
            return sign_num;
        }

        public void setSign_num(String sign_num) {
            this.sign_num = sign_num;
        }

        public String getTotal_accept() {
            return total_accept;
        }

        public void setTotal_accept(String total_accept) {
            this.total_accept = total_accept;
        }

        public String getWaybill_num() {
            return waybill_num;
        }

        public void setWaybill_num(String waybill_num) {
            this.waybill_num = waybill_num;
        }

        public List<LocationBean> getLocation() {
            return location;
        }

        public void setLocation(List<LocationBean> location) {
            this.location = location;
        }

        public static class LocationBean {
            /**
             * wa_id : 215
             * wa_num : Y202010217095
             * goodsJnum : 0
             * goodsNum : 0
             * shippers_name : 测试吕公司
             * lat : 31.717849346598
             * lng : 112.29255972053
             */

            private String wa_id;
            private String wa_num;
            private String goodsJnum;
            private String goodsNum;
            private String shippers_name;
            private String lat;
            private String lng;

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

            public String getGoodsJnum() {
                return goodsJnum;
            }

            public void setGoodsJnum(String goodsJnum) {
                this.goodsJnum = goodsJnum;
            }

            public String getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(String goodsNum) {
                this.goodsNum = goodsNum;
            }

            public String getShippers_name() {
                return shippers_name;
            }

            public void setShippers_name(String shippers_name) {
                this.shippers_name = shippers_name;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }
        }

}
