package com.ocean.dsgoods.entity;

/**
 * Created by James on 2020/10/29.
 */
public class ShppedDetailsData {

        /**
         * wa_id : 227
         * wa_num : Y202010289002
         * status : 2
         * tlogistics_name : 常州三人禾物流有限公司
         * tlogistics_mobile : 15261566657
         * shipper_name : 无锡雪两有限公司
         * send_name : 无锡雪两有限公司
         * allWeight : 1.00kg
         * allVolume : 1.00m³
         * aorder : 123456
         * ynum : 123456
         * startTime : 2010-10-10 10:00
         * endTime : 2010-10-10 10:00
         * arrivalTime : 2010-10-10 10:00
         * delivery : 派送到门
         * transport : 公路零担
         * tsTime : 普通
         * carLength : 车长
         * needCar : 车型平板
         * settleSty : 电器
         * pOrder : 邮件
         * orderTime : 2010-10-10 10:00
         * price : 12
         * fileInfo : 无
         * remarks : 无
         * goodsJnum : 1
         * goodsNum : 1
         * send_address : {"tel_name":"金猫路","phone":"15235455444","addrs":"吉林省松原市扶余市金猫路9号"}
         * saddress : {"tel_name":"qinfeng","phone":"15200000000","addrs":"北京北京市东城区123456789"}
         * isTurn : 12
         */

        private String wa_id;
        private String wa_num;
        private String status;
        private String tlogistics_name;
        private String tlogistics_mobile;
        private String shipper_name;
        private String send_name;
        private String allWeight;
        private String allVolume;
        private String aorder;
        private String ynum;
        private String startTime;
        private String endTime;
        private String arrivalTime;
        private String delivery;
        private String transport;
        private String tsTime;
        private String carLength;
        private String needCar;
        private String settleSty;
        private String pOrder;
        private String orderTime;
        private String price;
        private String fileInfo;
        private String remarks;
        private String rejectTime;
        private String reject_remarks;

    public String getRejectTime() {
        return rejectTime;
    }

    public void setRejectTime(String rejectTime) {
        this.rejectTime = rejectTime;
    }

    public String getReject_remarks() {
        return reject_remarks;
    }

    public void setReject_remarks(String reject_remarks) {
        this.reject_remarks = reject_remarks;
    }

    private String goodsJnum;
        private String goodsNum;
        private SendAddressBean send_address;
        private SaddressBean saddress;
        private String isTurn;

        public String getWa_id() {
            return wa_id;
        }

        public void setWa_id(String wa_id) {
            this.wa_id = wa_id;
        }

        public String getWa_num() {
            return wa_num;
        }

        public void setWa_num(String wa_num) {
            this.wa_num = wa_num;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTlogistics_name() {
            return tlogistics_name;
        }

        public void setTlogistics_name(String tlogistics_name) {
            this.tlogistics_name = tlogistics_name;
        }

        public String getTlogistics_mobile() {
            return tlogistics_mobile;
        }

        public void setTlogistics_mobile(String tlogistics_mobile) {
            this.tlogistics_mobile = tlogistics_mobile;
        }

        public String getShipper_name() {
            return shipper_name;
        }

        public void setShipper_name(String shipper_name) {
            this.shipper_name = shipper_name;
        }

        public String getSend_name() {
            return send_name;
        }

        public void setSend_name(String send_name) {
            this.send_name = send_name;
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

        public String getTransport() {
            return transport;
        }

        public void setTransport(String transport) {
            this.transport = transport;
        }

        public String getTsTime() {
            return tsTime;
        }

        public void setTsTime(String tsTime) {
            this.tsTime = tsTime;
        }

        public String getCarLength() {
            return carLength;
        }

        public void setCarLength(String carLength) {
            this.carLength = carLength;
        }

        public String getNeedCar() {
            return needCar;
        }

        public void setNeedCar(String needCar) {
            this.needCar = needCar;
        }

        public String getSettleSty() {
            return settleSty;
        }

        public void setSettleSty(String settleSty) {
            this.settleSty = settleSty;
        }

        public String getPOrder() {
            return pOrder;
        }

        public void setPOrder(String pOrder) {
            this.pOrder = pOrder;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
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

        public SendAddressBean getSend_address() {
            return send_address;
        }

        public void setSend_address(SendAddressBean send_address) {
            this.send_address = send_address;
        }

        public SaddressBean getSaddress() {
            return saddress;
        }

        public void setSaddress(SaddressBean saddress) {
            this.saddress = saddress;
        }

        public String getIsTurn() {
            return isTurn;
        }

        public void setIsTurn(String isTurn) {
            this.isTurn = isTurn;
        }

        public static class SendAddressBean {
            /**
             * tel_name : 金猫路
             * phone : 15235455444
             * addrs : 吉林省松原市扶余市金猫路9号
             */

            private String tel_name;
            private String phone;
            private String addrs;

            public String getTel_name() {
                return tel_name;
            }

            public void setTel_name(String tel_name) {
                this.tel_name = tel_name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAddrs() {
                return addrs;
            }

            public void setAddrs(String addrs) {
                this.addrs = addrs;
            }
        }

        public static class SaddressBean {
            /**
             * tel_name : qinfeng
             * phone : 15200000000
             * addrs : 北京北京市东城区123456789
             */

            private String tel_name;
            private String phone;
            private String addrs;

            public String getTel_name() {
                return tel_name;
            }

            public void setTel_name(String tel_name) {
                this.tel_name = tel_name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAddrs() {
                return addrs;
            }

            public void setAddrs(String addrs) {
                this.addrs = addrs;
            }
        }
}
