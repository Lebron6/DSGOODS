package com.ocean.dsgoods.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by James on 2020/11/13.
 */
public class ScanGoodInfo {

        /**
         * current_page : 1
         * per_page : 10
         * has_more : false
         * total : 2
         * pageall : 1
         * list : [{"id":"0","num_sn":"PB202010293820001","num":"10","is_bind":"1"},{"id":"8","num_sn":"ZB202010149459","num":"2","is_bind":"1"}]
         * waybillInfo : {"wa_id":"215","g_id":"164","pnum":"2","num":"15","already_jnum":"2","already_num":"15","name":"货物名称test","good_type":"1小小的"}
         */

        private int current_page;
        private int per_page;
        private boolean has_more;
        private int total;
        private int pageall;
        private WaybillInfoBean waybillInfo;
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

        public WaybillInfoBean getWaybillInfo() {
            return waybillInfo;
        }

        public void setWaybillInfo(WaybillInfoBean waybillInfo) {
            this.waybillInfo = waybillInfo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class WaybillInfoBean implements Serializable {
            /**
             * wa_id : 215
             * g_id : 164
             * pnum : 2
             * num : 15
             * already_jnum : 2
             * already_num : 15
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

            @Override
            public String toString() {
                return "WaybillInfoBean{" +
                        "wa_id='" + wa_id + '\'' +
                        ", g_id='" + g_id + '\'' +
                        ", pnum='" + pnum + '\'' +
                        ", num='" + num + '\'' +
                        ", already_jnum='" + already_jnum + '\'' +
                        ", already_num='" + already_num + '\'' +
                        ", name='" + name + '\'' +
                        ", good_type='" + good_type + '\'' +
                        '}';
            }
        }

        public static class ListBean {
            /**
             * id : 0
             * num_sn : PB202010293820001
             * num : 10
             * is_bind : 1
             */

            private String id;
            private String num_sn;
            private String num;
            private String is_bind;
            private String wa_id;
            private String g_id;

            public String getG_id() {
                return g_id;
            }

            public void setG_id(String g_id) {
                this.g_id = g_id;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNum_sn() {
                return num_sn;
            }

            public void setNum_sn(String num_sn) {
                this.num_sn = num_sn;
            }

            public String getNum() {
                return num;
            }

            public String getWa_id() {
                return wa_id;
            }

            public void setWa_id(String wa_id) {
                this.wa_id = wa_id;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getIs_bind() {
                return is_bind;
            }

            public void setIs_bind(String is_bind) {
                this.is_bind = is_bind;
            }
        }

}
