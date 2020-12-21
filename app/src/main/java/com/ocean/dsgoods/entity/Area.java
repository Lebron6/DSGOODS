package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/9/4.
 */
public class Area {
    /**
     * code : 1
     * msg : 请求成功
     * time : 1599115432
     * data : [{"id":"1","pid":"0","name":"北京","children":[{"id":"2","pid":"1","name":"北京市","children":[{"id":"3","pid":"2","name":"东城区","children":[]}]}]}]
     */

    private int code;
    private String msg;
    private int time;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * pid : 0
         * name : 北京
         * children : [{"id":"2","pid":"1","name":"北京市","children":[{"id":"3","pid":"2","name":"东城区","children":[]}]}]
         */

        private String id;
        private String pid;
        private String name;
        private List<ChildrenBeanX> children;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildrenBeanX> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBeanX> children) {
            this.children = children;
        }

        public static class ChildrenBeanX {
            /**
             * id : 2
             * pid : 1
             * name : 北京市
             * children : [{"id":"3","pid":"2","name":"东城区","children":[]}]
             */

            private String id;
            private String pid;
            private String name;
            private List<ChildrenBean> children;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            public static class ChildrenBean {
                /**
                 * id : 3
                 * pid : 2
                 * name : 东城区
                 * children : []
                 */

                private String id;
                private String pid;
                private String name;
                private List<?> children;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<?> getChildren() {
                    return children;
                }

                public void setChildren(List<?> children) {
                    this.children = children;
                }
            }
        }
    }
}
