package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/11/16.
 */
public class ManualBindData {

        /**
         * current_page : 1
         * per_page : 10
         * has_more : false
         * total : 1
         * pageall : 1
         * list : [{"wa_id":"215","g_id":"164","pnum":"2","num":"15","already_jnum":"0","already_num":"0","name":"货物名称test","good_type":"1小小的"}]
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
             * g_id : 164
             * pnum : 2
             * num : 15
             * already_jnum : 0
             * already_num : 0
             * name : 货物名称test
             * good_type : 1小小的
             */

            private String wa_id;
            private String g_id;
            private String pnum;
            private String num;
            private String already_jnum;
            private String already_num;
            private String name;
            private String good_type;

            public String getWa_id() {
                return wa_id;
            }

            public void setWa_id(String wa_id) {
                this.wa_id = wa_id;
            }

            public String getG_id() {
                return g_id;
            }

            public void setG_id(String g_id) {
                this.g_id = g_id;
            }

            public String getPnum() {
                return pnum;
            }

            public void setPnum(String pnum) {
                this.pnum = pnum;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
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
