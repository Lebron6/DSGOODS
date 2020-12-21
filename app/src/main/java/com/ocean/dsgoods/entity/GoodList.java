package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/9/11.
 */
public class GoodList {


        /**
         * current_page : 1
         * per_page : 10
         * has_more : false
         * total : 6
         * pageall : 1
         * list : [{"id":"4","g_id":"4","num":"洗衣机01","name":"超级洗衣机","packType":"2","takeNum":"100","weight":"20.21","volume":"1150.00","default_weight":"2021.00","default_volume":"1150.00","default_num":"100","default_pnum":1,"type":2},{"id":"5","g_id":"3","num":"123","name":"1232","packType":"2","takeNum":"222","weight":"100.00","volume":"27.00","default_weight":"22200.00","default_volume":"27.00","default_num":"222","default_pnum":1,"type":2},{"id":"6","g_id":"1","num":"路","name":"冰箱零一21","packType":"2","takeNum":"34","weight":"345.00","volume":"8.00","default_weight":"11730.00","default_volume":"8.00","default_num":"34","default_pnum":1,"type":2},{"id":"7","g_id":"2","num":"电池01","name":"电池零一","packType":"1","takeNum":"111","weight":"1.00","volume":"8.00","default_weight":"111.00","default_volume":"8.00","default_num":"111","default_pnum":1,"type":2},{"id":"8","g_id":"2","num":"电池01","name":"电池零一","packType":"1","takeNum":"10","weight":"1.00","volume":"10.00","default_weight":"10.00","default_volume":"10.00","default_num":"10","default_pnum":"1","type":1},{"id":"12","g_id":"24","num":"电池code01","name":"电池1","packType":"1","takeNum":"10","weight":"1.00","volume":"0.12","default_weight":"10.00","default_volume":"0.12","default_num":"10","default_pnum":"1","type":1}]
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
             * id : 4
             * g_id : 4
             * num : 洗衣机01
             * name : 超级洗衣机
             * packType : 2
             * takeNum : 100
             * weight : 20.21
             * volume : 1150.00
             * default_weight : 2021.00
             * default_volume : 1150.00
             * default_num : 100
             * default_pnum : 1
             * type : 2
             */

            private String id;
            private String g_id;
            private String num;
            private String name;
            private String packType;
            private String takeNum;
            private Double weight;
            private Double volume;
            private String default_weight;
            private String default_volume;
            private int default_num;
            private int default_pnum;
            private int type;

            private Double singleWeigth;
            private Double singleVolume;
            private int singleNum;
            private int singlePnum;

            public int getSingleNum() {
                return singleNum;
            }

            public void setSingleNum(int singleNum) {
                this.singleNum = singleNum;
            }

            public int getSinglePnum() {
                return singlePnum;
            }

            public void setSinglePnum(int singlePnum) {
                this.singlePnum = singlePnum;
            }

            public Double getSingleWeigth() {
                return singleWeigth;
            }

            public void setSingleWeigth(Double singleWeigth) {
                this.singleWeigth = singleWeigth;
            }

            public Double getSingleVolume() {
                return singleVolume;
            }

            public void setSingleVolume(Double singleVolume) {
                this.singleVolume = singleVolume;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getG_id() {
                return g_id;
            }

            public void setG_id(String g_id) {
                this.g_id = g_id;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPackType() {
                return packType;
            }

            public void setPackType(String packType) {
                this.packType = packType;
            }

            public String getTakeNum() {
                return takeNum;
            }

            public void setTakeNum(String takeNum) {
                this.takeNum = takeNum;
            }

            public Double getWeight() {
                return weight;
            }

            public void setWeight(Double weight) {
                this.weight = weight;
            }

            public Double getVolume() {
                return volume;
            }

            public void setVolume(Double volume) {
                this.volume = volume;
            }

            public String getDefault_weight() {
                return default_weight;
            }

            public void setDefault_weight(String default_weight) {
                this.default_weight = default_weight;
            }

            public String getDefault_volume() {
                return default_volume;
            }

            public void setDefault_volume(String default_volume) {
                this.default_volume = default_volume;
            }

            public int getDefault_num() {
                return default_num;
            }

            public void setDefault_num(int default_num) {
                this.default_num = default_num;
            }

            public int getDefault_pnum() {
                return default_pnum;
            }

            public void setDefault_pnum(int default_pnum) {
                this.default_pnum = default_pnum;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

}
