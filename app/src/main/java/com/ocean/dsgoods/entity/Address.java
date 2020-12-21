package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/9/10.
 */
public class Address {

        /**
         * current_page : 1
         * per_page : 10
         * has_more : false
         * total : 0
         * pageall : 1
         * list : [{"w_id":"提货地址id","name":"仓库名称","address":"地址","liaison":"联系人","tel":"联系电话"}]
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
             * w_id : 提货地址id
             * name : 仓库名称
             * address : 地址
             * liaison : 联系人
             * tel : 联系电话
             */

            private String w_id;
            private String sa_id;

            public String getSa_id() {
                return sa_id;
            }

            public void setSa_id(String sa_id) {
                this.sa_id = sa_id;
            }

            private String name;
            private String address;
            private String liaison;
            private String tel;

            public String getW_id() {
                return w_id;
            }

            public void setW_id(String w_id) {
                this.w_id = w_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getLiaison() {
                return liaison;
            }

            public void setLiaison(String liaison) {
                this.liaison = liaison;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }
        }

}
