package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/11/24.
 */
public class TurnoverInfo {

        /**
         * rv_id : 2
         * rv_num : 10002
         * model : 1234556
         * size : 480.00*200.00*200.00
         * goods : {"has_goods":false,"list":[{"g_id":"1","name":"冰箱零一21","pro_num":"HW202008111847"},{"g_id":"3","name":"1232","pro_num":"HW202008114883"}]}
         */

        private String rv_id;
        private String rv_num;
        private String model;
        private String pk_num;

    public String getPk_num() {
        return pk_num;
    }

    public void setPk_num(String pk_num) {
        this.pk_num = pk_num;
    }

    private String size;
        private String name;
        private String fuselage;

    public String getFuselage() {
        return fuselage;
    }

    public void setFuselage(String fuselage) {
        this.fuselage = fuselage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private GoodsBean goods;

        public String getRv_id() {
            return rv_id;
        }

        public void setRv_id(String rv_id) {
            this.rv_id = rv_id;
        }

        public String getRv_num() {
            return rv_num;
        }

        public void setRv_num(String rv_num) {
            this.rv_num = rv_num;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * has_goods : false
             * list : [{"g_id":"1","name":"冰箱零一21","pro_num":"HW202008111847"},{"g_id":"3","name":"1232","pro_num":"HW202008114883"}]
             */
            private boolean has_goods;
            private InfoBean info;


            public InfoBean getInfo() {
                return info;
            }

            public void setInfo(InfoBean info) {
                this.info = info;
            }

            public static class InfoBean {
                /**
                 * type : 1
                 * g_id : 2
                 * s_name : lihuan
                 * pro_num : HW202008119156
                 * name : 电池零一
                 * take_num : 20
                 * num : 15
                 */

                private int type;
                private String g_id;
                private String s_name;
                private String pro_num;
                private String name;
                private String take_num;
                private String num;

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getG_id() {
                    return g_id;
                }

                public void setG_id(String g_id) {
                    this.g_id = g_id;
                }

                public String getS_name() {
                    return s_name;
                }

                public void setS_name(String s_name) {
                    this.s_name = s_name;
                }

                public String getPro_num() {
                    return pro_num;
                }

                public void setPro_num(String pro_num) {
                    this.pro_num = pro_num;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getTake_num() {
                    return take_num;
                }

                public void setTake_num(String take_num) {
                    this.take_num = take_num;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }
            }
            private List<ListBean> list;

            public boolean isHas_goods() {
                return has_goods;
            }

            public void setHas_goods(boolean has_goods) {
                this.has_goods = has_goods;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * g_id : 1
                 * name : 冰箱零一21
                 * pro_num : HW202008111847
                 */

                private String g_id;
                private String name;
                private String pro_num;
                private String take_num;

                public String getTake_num() {
                    return take_num;
                }

                public void setTake_num(String take_num) {
                    this.take_num = take_num;
                }

                public String getG_id() {
                    return g_id;
                }

                public void setG_id(String g_id) {
                    this.g_id = g_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPro_num() {
                    return pro_num;
                }

                public void setPro_num(String pro_num) {
                    this.pro_num = pro_num;
                }
            }
        }

}
