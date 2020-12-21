package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/9/14.
 */
public class TransportationType {

    /**
     * code : 1
     * msg : 请求成功
     * time : 1600062536
     * data : {"delivery":[{"id":1,"name":"派送到门"},{"id":2,"name":"派送自提"}],"tsTime":[{"id":1,"name":"普通"},{"id":2,"name":"加急"},{"id":3,"name":"JIT"}],"transport_mode":[{"id":1,"name":"公路零担"},{"id":2,"name":"公路整车"},{"id":3,"name":"空运"},{"id":4,"name":"国内联运"},{"id":5,"name":"网络班车"},{"id":6,"name":"VMI配送"},{"id":7,"name":"不限"}],"car_require":[{"id":1,"name":"无要求"},{"id":2,"name":"货的"},{"id":3,"name":"栏板车"},{"id":4,"name":"半封闭厢车"},{"id":5,"name":"全封闭厢车"},{"id":6,"name":"集装厢车"},{"id":7,"name":"平板拖车"},{"id":8,"name":"保温车"},{"id":9,"name":"冷藏车"},{"id":10,"name":"特种车"},{"id":11,"name":"面包车"},{"id":12,"name":"罐装车"}],"settleSty":[{"id":"14","name":"月结30天"},{"id":"32","name":"月结90天"}],"remarks":[{"id":1,"name":"请勿倒置"},{"id":2,"name":"货物禁止中途中转"},{"id":3,"name":"客户自提"}]}
     */

    private int code;
    private String msg;
    private int time;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<DeliveryBean> delivery;
        private List<TsTimeBean> tsTime;
        private List<TransportModeBean> transport_mode;
        private List<CarRequireBean> car_require;
        private List<SettleStyBean> settleSty;
        private List<RemarksBean> remarks;

        public List<DeliveryBean> getDelivery() {
            return delivery;
        }

        public void setDelivery(List<DeliveryBean> delivery) {
            this.delivery = delivery;
        }

        public List<TsTimeBean> getTsTime() {
            return tsTime;
        }

        public void setTsTime(List<TsTimeBean> tsTime) {
            this.tsTime = tsTime;
        }

        public List<TransportModeBean> getTransport_mode() {
            return transport_mode;
        }

        public void setTransport_mode(List<TransportModeBean> transport_mode) {
            this.transport_mode = transport_mode;
        }

        public List<CarRequireBean> getCar_require() {
            return car_require;
        }

        public void setCar_require(List<CarRequireBean> car_require) {
            this.car_require = car_require;
        }

        public List<SettleStyBean> getSettleSty() {
            return settleSty;
        }

        public void setSettleSty(List<SettleStyBean> settleSty) {
            this.settleSty = settleSty;
        }

        public List<RemarksBean> getRemarks() {
            return remarks;
        }

        public void setRemarks(List<RemarksBean> remarks) {
            this.remarks = remarks;
        }

        public static class DeliveryBean {
            /**
             * id : 1
             * name : 派送到门
             */

            private String id;
            private String name;

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
        }

        public static class TsTimeBean {
            /**
             * id : 1
             * name : 普通
             */

            private String id;
            private String name;

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
        }

        public static class TransportModeBean {
            /**
             * id : 1
             * name : 公路零担
             */

            private String id;
            private String name;

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
        }

        public static class CarRequireBean {
            /**
             * id : 1
             * name : 无要求
             */

            private String id;
            private String name;

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
        }

        public static class SettleStyBean {
            /**
             * id : 14
             * name : 月结30天
             */

            private String id;
            private String name;

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
        }

        public static class RemarksBean {
            /**
             * id : 1
             * name : 请勿倒置
             */

            private String id;
            private String name;

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
        }
    }
}
