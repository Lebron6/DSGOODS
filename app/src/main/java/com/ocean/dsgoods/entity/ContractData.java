package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/9/10.
 */
public class ContractData {

        /**
         * current_page : 2
         * per_page : 10
         * has_more : false
         * total : 11
         * pageall : 2
         * list : [{"co_id":"合同id","constract_sn":"流水号","startTime":"有效开始时间","endTime":"有效结束时间","filePath":"文件地址","name":"公司名称","type":"结算方式id"}]
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
             * co_id : 合同id
             * constract_sn : 流水号
             * startTime : 有效开始时间
             * endTime : 有效结束时间
             * filePath : 文件地址
             * name : 公司名称
             * type : 结算方式id
             */

            private String co_id;
            private String constract_sn;
            private String startTime;
            private String endTime;
            private String filePath;
            private String name;
            private String type;

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

            public String getFilePath() {
                return filePath;
            }

            public void setFilePath(String filePath) {
                this.filePath = filePath;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

}
