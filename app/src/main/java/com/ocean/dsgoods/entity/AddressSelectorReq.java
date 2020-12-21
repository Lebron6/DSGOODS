package com.ocean.dsgoods.entity;


import com.google.gson.annotations.SerializedName;
import com.ocean.dsgoods.callback.CityInterface;

import java.util.List;

public class AddressSelectorReq {



    private int ret;
    private String msg;
    private List<DatasBean> datas;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean implements CityInterface {

        @SerializedName("id")
        private String db_id;
        @SerializedName("parentId")
        private String db_parentId;
        @SerializedName("areaName")
        private String db_areaName;

        @SerializedName("children")
        private List<ChildrenBeanX> db_children;

        public String getDb_id() {
            return db_id;
        }

        public void setDb_id(String db_id) {
            this.db_id = db_id;
        }

        public String getDb_parentId() {
            return db_parentId;
        }

        public void setDb_parentId(String db_parentId) {
            this.db_parentId = db_parentId;
        }

        public String getDb_areaName() {
            return db_areaName;
        }

        public void setDb_areaName(String db_areaName) {
            this.db_areaName = db_areaName;
        }


        public List<ChildrenBeanX> getDb_children() {
            return db_children;
        }

        public void setDb_children(List<ChildrenBeanX> db_children) {
            this.db_children = db_children;
        }

        @Override
        public String getCityName() {
            return db_areaName;
        }

        public static class ChildrenBeanX implements CityInterface {


            @SerializedName("id")
            private String cb_id;
            @SerializedName("parentId")
            private String cb_parentId;
            @SerializedName("areaName")
            private String cb_areaName;
            @SerializedName("children")
            private List<ChildrenBean> cb_children;

            public String getCb_id() {
                return cb_id;
            }

            public void setCb_id(String cb_id) {
                this.cb_id = cb_id;
            }

            public String getCb_parentId() {
                return cb_parentId;
            }

            public void setCb_parentId(String cb_parentId) {
                this.cb_parentId = cb_parentId;
            }

            public String getCb_areaName() {
                return cb_areaName;
            }

            public void setCb_areaName(String cb_areaName) {
                this.cb_areaName = cb_areaName;
            }


            public List<ChildrenBean> getCb_children() {
                return cb_children;
            }

            public void setCb_children(List<ChildrenBean> cb_children) {
                this.cb_children = cb_children;
            }

            @Override
            public String getCityName() {
                return cb_areaName;
            }

            public static class ChildrenBean implements CityInterface {
                /**
                 * id : 4
                 * parentId : 3
                 * areaName : 东城区
                 * areaZip : 100000
                 * areaType : 3
                 * areaCode : 110101
                 * areaAbbName : 东城
                 * areaEnName : DONG CHENG DISTRICT
                 */

                private String id;
                private String parentId;
                private String areaName;


                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }

                public String getAreaName() {
                    return areaName;
                }

                public void setAreaName(String areaName) {
                    this.areaName = areaName;
                }



                @Override
                public String getCityName() {
                    return areaName;
                }
            }
        }
    }
}
