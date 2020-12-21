package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/11/12.
 */
public class BindWillList {

        /**
         * current_page : 1
         * per_page : 10
         * has_more : false
         * total : 7
         * pageall : 1
         * list : [{"wa_id":"215","status":"15","wa_num":"Y202010217095","startCity":"苏州","endCity":"扬州","shippers_id":"21","t_id":"43","arrivalTime":"2010-10-10 10:00","shippers_name":"测试吕公司","t_name":"常州三人禾物流有限公司","num":"15","pnum":"1","status_name":"完成"},{"wa_id":"216","status":"5","wa_num":"Y202010227057","startCity":"苏州","endCity":"非洲","shippers_id":"21","t_id":"43","arrivalTime":"2010-10-10 10:00","shippers_name":"测试吕公司","t_name":"常州三人禾物流有限公司","num":"1","pnum":"1","status_name":"交货出发"},{"wa_id":"219","status":"5","wa_num":"Y202010226734","startCity":"广州","endCity":"非洲","shippers_id":"21","t_id":"43","arrivalTime":"2010-10-10 10:00","shippers_name":"测试吕公司","t_name":"常州三人禾物流有限公司","num":"1","pnum":"1","status_name":"交货出发"},{"wa_id":"220","status":"5","wa_num":"Y202010226110","startCity":"","endCity":"","shippers_id":"21","t_id":"36","arrivalTime":"2010-10-10 10:00","shippers_name":"测试吕公司","t_name":"苏州物流有限公司2","num":null,"pnum":null,"status_name":"交货出发"},{"wa_id":"222","status":"3","wa_num":"Y202010226734","startCity":"松原市","endCity":"","shippers_id":"13","t_id":"1","arrivalTime":"2020-10-30 18:30","shippers_name":"大洋33","t_name":"大洋1","num":"1","pnum":"1","status_name":"驳回"},{"wa_id":"223","status":"12","wa_num":"Y202010226110","startCity":"广州","endCity":"苏州","shippers_id":"21","t_id":"1","arrivalTime":"2020-10-30 18:30","shippers_name":"测试吕公司","t_name":"大洋1","num":null,"pnum":null,"status_name":"签收"},{"wa_id":"226","status":"2","wa_num":"Y202010285001","startCity":"","endCity":"北京市","shippers_id":"32","t_id":"43","arrivalTime":"2010-10-10 10:00","shippers_name":"无锡雪两有限公司","t_name":"常州三人禾物流有限公司","num":"29","pnum":"5","status_name":"确认"}]
         */

        private String current_page;
        private int per_page;
        private boolean has_more;
        private String total;
        private int pageall;
        private List<ListBean> list;

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
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

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
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
             * wa_id : 215
             * status : 15
             * wa_num : Y202010217095
             * startCity : 苏州
             * endCity : 扬州
             * shippers_id : 21
             * t_id : 43
             * arrivalTime : 2010-10-10 10:00
             * shippers_name : 测试吕公司
             * t_name : 常州三人禾物流有限公司
             * num : 15
             * pnum : 1
             * status_name : 完成
             */

            private String wa_id;
            private String status;
            private String wa_num;
            private String startCity;
            private String endCity;
            private String shippers_id;
            private String t_id;
            private String arrivalTime;
            private String shippers_name;
            private String t_name;
            private String num;
            private String pnum;
            private String status_name;

            public String getWa_id() {
                return wa_id;
            }

            public void setWa_id(String wa_id) {
                this.wa_id = wa_id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getWa_num() {
                return wa_num;
            }

            public void setWa_num(String wa_num) {
                this.wa_num = wa_num;
            }

            public String getStartCity() {
                return startCity;
            }

            public void setStartCity(String startCity) {
                this.startCity = startCity;
            }

            public String getEndCity() {
                return endCity;
            }

            public void setEndCity(String endCity) {
                this.endCity = endCity;
            }

            public String getShippers_id() {
                return shippers_id;
            }

            public void setShippers_id(String shippers_id) {
                this.shippers_id = shippers_id;
            }

            public String getT_id() {
                return t_id;
            }

            public void setT_id(String t_id) {
                this.t_id = t_id;
            }

            public String getArrivalTime() {
                return arrivalTime;
            }

            public void setArrivalTime(String arrivalTime) {
                this.arrivalTime = arrivalTime;
            }

            public String getShippers_name() {
                return shippers_name;
            }

            public void setShippers_name(String shippers_name) {
                this.shippers_name = shippers_name;
            }

            public String getT_name() {
                return t_name;
            }

            public void setT_name(String t_name) {
                this.t_name = t_name;
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

            public String getStatus_name() {
                return status_name;
            }

            public void setStatus_name(String status_name) {
                this.status_name = status_name;
            }
        }

}
