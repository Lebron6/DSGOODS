package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/10/14.
 */
public class AddInitOne {

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
             * id : 1
             * name : 大洋1
             * pl_warehouse : [{"w_id":"1","name":"大锅饭1号","dp_num":"0"},{"w_id":"2","name":"大锅饭2号2","dp_num":"0"},{"w_id":"3","name":"大锅饭1号","dp_num":"0"},{"w_id":"4","name":"大洋供应链","dp_num":"2"},{"w_id":"8","name":"asdasd","dp_num":"0"}]
             */

            private String id;
            private String name;
            private List<PlWarehouseBean> pl_warehouse;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<PlWarehouseBean> getPl_warehouse() {
                return pl_warehouse;
            }

            public void setPl_warehouse(List<PlWarehouseBean> pl_warehouse) {
                this.pl_warehouse = pl_warehouse;
            }

            public static class PlWarehouseBean {
                /**
                 * w_id : 1
                 * name : 大锅饭1号
                 * dp_num : 0
                 */

                private String w_id;
                private String name;
                private String dp_num;

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

                public String getDp_num() {
                    return dp_num;
                }

                public void setDp_num(String dp_num) {
                    this.dp_num = dp_num;
                }
            }
        }

}
