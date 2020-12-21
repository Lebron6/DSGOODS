package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/9/10.
 */
public class SupplierList {

        /**
         * current_page : 1
         * per_page : 10
         * has_more : false
         * total : 1
         * pageall : 1
         * list : [{"t_id":"1","headimg":"http://img.oceanscm.com/3plheadimg/2020090406478148661241.png","name":"大洋1","control_no":"P00001","id_allow":1,"kpi":"--"}]
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
             * t_id : 1
             * headimg : http://img.oceanscm.com/3plheadimg/2020090406478148661241.png
             * name : 大洋1
             * control_no : P00001
             * id_allow : 1
             * kpi : --
             */

            private String t_id;
            private String headimg;
            private String name;
            private String control_no;
            private int id_allow;
            private String kpi;

            public String getT_id() {
                return t_id;
            }

            public void setT_id(String t_id) {
                this.t_id = t_id;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getControl_no() {
                return control_no;
            }

            public void setControl_no(String control_no) {
                this.control_no = control_no;
            }

            public int getId_allow() {
                return id_allow;
            }

            public void setId_allow(int id_allow) {
                this.id_allow = id_allow;
            }

            public String getKpi() {
                return kpi;
            }

            public void setKpi(String kpi) {
                this.kpi = kpi;
            }
        }

}
