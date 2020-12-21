package com.ocean.dsgoods.entity;

/**
 * Created by James on 2020/9/9.
 */
public class BillDetailsData {

        /**
         * serial_num : T202009042069
         * co_id : 1
         * id_allow : 1
         * constract_sn : HT202008146266
         * order_time : 1970-01-01 08:00
         * name : 大洋1
         * pickup_address : {"address":"故宫西路230号","liaison":"联系人","tel":"18765856968","name":"大洋1"}
         * collect_address : {"address":"","liaison":"","tel":"","name":""}
         * aorder : 123
         * ynum : 123
         * startTime : 1970-01-01 08:18
         * endTime : 1970-01-01 11:05
         * arrivalTime : 1970-01-01 11:05
         * delivery : 派送到门
         * tsTime : 普通
         * transport : 公路零担
         * needCar : 无要求
         * allWeight : 20.00
         * allVolume : 10.12
         * settleSty : 月结30天
         * status : 2
         * dp_type : 1
         * operationTime : 2020-09-08 14:24
         * reject_remarks :
         * price : 111.00
         * fileInfo : 123
         * remarks : 123
         */

        private String serial_num;
        private String co_id;
        private int id_allow;
        private String constract_sn;
        private String order_time;
        private String name;
        private PickupAddressBean pickup_address;
        private CollectAddressBean collect_address;
        private String aorder;
        private String ynum;
        private String startTime;
        private String endTime;
        private String arrivalTime;
        private String delivery;
        private String tsTime;
        private String transport;
        private String needCar;
        private String allWeight;
        private String allVolume;
        private String settleSty;
        private String status;
        private String dp_type;
        private String operationTime;
        private String reject_remarks;
        private String price;
        private String fileInfo;
        private String remarks;
        private String t_id;
        private String w_id;

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getW_id() {
        return w_id;
    }

    public void setW_id(String w_id) {
        this.w_id = w_id;
    }

    public String getSa_id() {
        return sa_id;
    }

    public void setSa_id(String sa_id) {
        this.sa_id = sa_id;
    }

    private String sa_id;

        public String getSerial_num() {
            return serial_num;
        }

        public void setSerial_num(String serial_num) {
            this.serial_num = serial_num;
        }

        public String getCo_id() {
            return co_id;
        }

        public void setCo_id(String co_id) {
            this.co_id = co_id;
        }

        public int getId_allow() {
            return id_allow;
        }

        public void setId_allow(int id_allow) {
            this.id_allow = id_allow;
        }

        public String getConstract_sn() {
            return constract_sn;
        }

        public void setConstract_sn(String constract_sn) {
            this.constract_sn = constract_sn;
        }

        public String getOrder_time() {
            return order_time;
        }

        public void setOrder_time(String order_time) {
            this.order_time = order_time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public PickupAddressBean getPickup_address() {
            return pickup_address;
        }

        public void setPickup_address(PickupAddressBean pickup_address) {
            this.pickup_address = pickup_address;
        }

        public CollectAddressBean getCollect_address() {
            return collect_address;
        }

        public void setCollect_address(CollectAddressBean collect_address) {
            this.collect_address = collect_address;
        }

        public String getAorder() {
            return aorder;
        }

        public void setAorder(String aorder) {
            this.aorder = aorder;
        }

        public String getYnum() {
            return ynum;
        }

        public void setYnum(String ynum) {
            this.ynum = ynum;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getArrivalTime() {
            return arrivalTime;
        }

        public void setArrivalTime(String arrivalTime) {
            this.arrivalTime = arrivalTime;
        }

        public String getDelivery() {
            return delivery;
        }

        public void setDelivery(String delivery) {
            this.delivery = delivery;
        }

        public String getTsTime() {
            return tsTime;
        }

        public void setTsTime(String tsTime) {
            this.tsTime = tsTime;
        }

        public String getTransport() {
            return transport;
        }

        public void setTransport(String transport) {
            this.transport = transport;
        }

        public String getNeedCar() {
            return needCar;
        }

        public void setNeedCar(String needCar) {
            this.needCar = needCar;
        }

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

        public String getSettleSty() {
            return settleSty;
        }

        public void setSettleSty(String settleSty) {
            this.settleSty = settleSty;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDp_type() {
            return dp_type;
        }

        public void setDp_type(String dp_type) {
            this.dp_type = dp_type;
        }

        public String getOperationTime() {
            return operationTime;
        }

        public void setOperationTime(String operationTime) {
            this.operationTime = operationTime;
        }

        public String getReject_remarks() {
            return reject_remarks;
        }

        public void setReject_remarks(String reject_remarks) {
            this.reject_remarks = reject_remarks;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getFileInfo() {
            return fileInfo;
        }

        public void setFileInfo(String fileInfo) {
            this.fileInfo = fileInfo;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public static class PickupAddressBean {
            /**
             * address : 故宫西路230号
             * liaison : 联系人
             * tel : 18765856968
             * name : 大洋1
             */

            private String address;
            private String liaison;
            private String tel;
            private String name;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class CollectAddressBean {
            /**
             * address :
             * liaison :
             * tel :
             * name :
             */

            private String address;
            private String liaison;
            private String tel;
            private String name;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
}
