package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/9/9.
 */
public class BillList {
        /**
         * current_page : 1
         * per_page : 10
         * has_more : false
         * total : 1
         * pageall : 1
         * list : [{"dp_id":"提货单id","serial_num":"提货单号","start_city":"出发城市","end_city":"到达城市","name":"承运方","status":"1新建，2确认，3调度，4出发，5到达，6装车，7驳回，9完成，10作废 ","status_name":"状态名称","dp_type":"发布类型  1=3pl发布  2=收发货端发布","start_time":"提单出发时间","end_time":"到达时间"}]
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
             * dp_id : 提货单id
             * serial_num : 提货单号
             * start_city : 出发城市
             * end_city : 到达城市
             * name : 承运方
             * status : 1新建，2确认，3调度，4出发，5到达，6装车，7驳回，9完成，10作废
             * status_name : 状态名称
             * dp_type : 发布类型  1=3pl发布  2=收发货端发布
             * start_time : 提单出发时间
             * end_time : 到达时间
             */

            private String dp_id;
            private String serial_num;
            private String start_city;
            private String end_city;
            private String name;
            private String status;
            private String status_name;
            private String dp_type;
            private String start_time;
            private String end_time;

            public String getDp_id() {
                return dp_id;
            }

            public void setDp_id(String dp_id) {
                this.dp_id = dp_id;
            }

            public String getSerial_num() {
                return serial_num;
            }

            public void setSerial_num(String serial_num) {
                this.serial_num = serial_num;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getStatus_name() {
                return status_name;
            }

            public void setStatus_name(String status_name) {
                this.status_name = status_name;
            }

            public String getDp_type() {
                return dp_type;
            }

            public void setDp_type(String dp_type) {
                this.dp_type = dp_type;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }
        }
}
