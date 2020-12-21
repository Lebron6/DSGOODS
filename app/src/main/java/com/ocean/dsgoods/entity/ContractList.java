package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/9/8.
 */
public class ContractList {

        /**
         * current_page : 1
         * per_page : 10
         * has_more : false
         * total : 10
         * pageall : 1
         * list : [{"co_id":"67","constract_sn":"HT202009018738","supplier_name":"常州水蜜桃17物流有限公司","check_com_id":"22","startTime":"2020-09-04 00:00","endTime":"2020-09-01 00:00","upTime":null,"status":"2","q_id":"525","cancel_remarks":null,"reject_remarks":null},{"co_id":"66","constract_sn":"HT202009014488","supplier_name":"常州水蜜桃17物流有限公司","check_com_id":"22","startTime":"2020-09-04 00:00","endTime":"2020-09-01 00:00","upTime":null,"status":"2","q_id":"525","cancel_remarks":null,"reject_remarks":null},{"co_id":"62","constract_sn":"HT202009011976","supplier_name":"常州水蜜桃17物流有限公司","check_com_id":"22","startTime":"2020-09-18 00:00","endTime":"2020-09-16 00:00","upTime":null,"status":"2","q_id":"525","cancel_remarks":null,"reject_remarks":null},{"co_id":"57","constract_sn":"HT202009019137","supplier_name":"常州水蜜桃17物流有限公司","check_com_id":"22","startTime":"2020-09-01 00:00","endTime":"2020-09-09 00:00","upTime":null,"status":"2","q_id":"525","cancel_remarks":null,"reject_remarks":null},{"co_id":"54","constract_sn":"HT202009011278","supplier_name":"常州水蜜桃17物流有限公司","check_com_id":"22","startTime":"2020-09-01 00:00","endTime":"2020-09-02 00:00","upTime":null,"status":"2","q_id":"525","cancel_remarks":null,"reject_remarks":null},{"co_id":"52","constract_sn":"HT202009017581","supplier_name":"常州水蜜桃17物流有限公司","check_com_id":"22","startTime":"2020-09-02 00:00","endTime":"2020-09-09 00:00","upTime":null,"status":"2","q_id":"525","cancel_remarks":null,"reject_remarks":null},{"co_id":"38","constract_sn":"HT202008311638","supplier_name":"常州水蜜桃17物流有限公司","check_com_id":"22","startTime":"2020-08-05 00:00","endTime":"2020-08-14 00:00","upTime":null,"status":"2","q_id":"","cancel_remarks":null,"reject_remarks":null},{"co_id":"35","constract_sn":"HT202008311946","supplier_name":"常州水蜜桃17物流有限公司","check_com_id":"22","startTime":"2020-08-04 00:00","endTime":"2020-08-11 00:00","upTime":null,"status":"2","q_id":"518","cancel_remarks":null,"reject_remarks":null},{"co_id":"34","constract_sn":"HT202008314635","supplier_name":"常州水蜜桃17物流有限公司","check_com_id":"22","startTime":"2020-08-03 00:00","endTime":"2020-08-10 00:00","upTime":null,"status":"2","q_id":"518","cancel_remarks":null,"reject_remarks":null},{"co_id":"31","constract_sn":"HT202008316838","supplier_name":"常州水蜜桃17物流有限公司","check_com_id":"22","startTime":"2020-08-02 00:00","endTime":"2020-08-09 00:00","upTime":null,"status":"2","q_id":"","cancel_remarks":null,"reject_remarks":null}]
         */

        private int current_page;
        private int per_page;
        private boolean has_more;
        private String total;
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
             * co_id : 67
             * constract_sn : HT202009018738
             * supplier_name : 常州水蜜桃17物流有限公司
             * check_com_id : 22
             * startTime : 2020-09-04 00:00
             * endTime : 2020-09-01 00:00
             * upTime : null
             * status : 2
             * q_id : 525
             * cancel_remarks : null
             * reject_remarks : null
             */

            private String co_id;
            private String constract_sn;
            private String supplier_name;
            private String check_com_id;
            private String startTime;
            private String endTime;
            private String upTime;
            private String status;
            private String q_id;
            private String cancel_remarks;
            private String reject_remarks;
            private String com_name;

            public String getCom_name() {
                return com_name;
            }

            public void setCom_name(String com_name) {
                this.com_name = com_name;
            }

            public String getCo_id() {
                return co_id;
            }

            public void setCo_id(String co_id) {
                this.co_id = co_id;
            }

            public String getConstract_sn() {
                return constract_sn;
            }

            public void setConstract_sn(String constract_sn) {
                this.constract_sn = constract_sn;
            }

            public String getSupplier_name() {
                return supplier_name;
            }

            public void setSupplier_name(String supplier_name) {
                this.supplier_name = supplier_name;
            }

            public String getCheck_com_id() {
                return check_com_id;
            }

            public void setCheck_com_id(String check_com_id) {
                this.check_com_id = check_com_id;
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

            public String getUpTime() {
                return upTime;
            }

            public void setUpTime(String upTime) {
                this.upTime = upTime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getQ_id() {
                return q_id;
            }

            public void setQ_id(String q_id) {
                this.q_id = q_id;
            }

            public Object getCancel_remarks() {
                return cancel_remarks;
            }

            public void setCancel_remarks(String cancel_remarks) {
                this.cancel_remarks = cancel_remarks;
            }

            public String getReject_remarks() {
                return reject_remarks;
            }

            public void setReject_remarks(String reject_remarks) {
                this.reject_remarks = reject_remarks;
            }
        }

}
