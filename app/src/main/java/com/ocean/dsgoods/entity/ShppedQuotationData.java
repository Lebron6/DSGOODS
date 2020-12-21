package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/10/30.
 */
public class ShppedQuotationData {


        /**
         * current_page : 1
         * per_page : 10
         * has_more : false
         * total : 2
         * pageall : 1
         * list : {"header":["货物名称","货物型号","包装类型","包装收容数","重量(kg)/单个","体积(m³)/单个","件数","货物数量","生产批次号","货物描述"],"list":[["格力空调","HWXH2P","周转箱A类-Ⅴ","1","30.00","1.00","1","1","",""],["小天鹅冰箱","XH0201","周转箱C类","100","13.00","100.00","2","200","",""]]}
         * total_info : {"allWeight":"56.00kg","allVolume":"201.00m³","goodsJnum":"3","goodsNum":"201"}
         */

        private int current_page;
        private int per_page;
        private boolean has_more;
        private int total;
        private int pageall;
        private ListBean list;
        private TotalInfoBean total_info;

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

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public TotalInfoBean getTotal_info() {
            return total_info;
        }

        public void setTotal_info(TotalInfoBean total_info) {
            this.total_info = total_info;
        }

        public static class ListBean {
            private List<String> header;
            private List<List<String>> list;

            public List<String> getHeader() {
                return header;
            }

            public void setHeader(List<String> header) {
                this.header = header;
            }

            public List<List<String>> getList() {
                return list;
            }

            public void setList(List<List<String>> list) {
                this.list = list;
            }
        }

        public static class TotalInfoBean {
            /**
             * allWeight : 56.00kg
             * allVolume : 201.00m³
             * goodsJnum : 3
             * goodsNum : 201
             */

            private String allWeight;
            private String allVolume;
            private String goodsJnum;
            private String goodsNum;

            public String getAllWeight() {
                return allWeight;
            }

            public void setAllWeight(String allWeight) {
                this.allWeight = allWeight;
            }

            public String getAllVolume() {
                return allVolume;
            }

            public void setAllVolume(String allVolume) {
                this.allVolume = allVolume;
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
        }

}
