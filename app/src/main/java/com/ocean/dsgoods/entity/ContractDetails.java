package com.ocean.dsgoods.entity;

/**
 * Created by James on 2020/9/8.
 */
public class ContractDetails {

        /**
         * co_id : 67
         * constract_sn : HT202009018738
         * supplier_name : 常州水蜜桃17物流有限公司
         * cycle : 12
         * accountTpye : 14
         * check_com_id : 22
         * startTime : 2020-09-04 00:00
         * endTime : 2020-09-01 00:00
         * upTime : null
         * status : 2
         * q_id : 525
         * cancel_remarks : null
         * reject_remarks : null
         */

        private String co_id;
        private String constract_sn;
        private String com_name;
        private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    private String supplier_name;
        private String cycle;
        private String accountTpye;
        private String check_com_id;
        private String startTime;
        private String endTime;
        private String upTime;
        private String status;
        private String q_id;
        private String cancel_remarks;
        private String reject_remarks;
        private String rejectTime;
        private String p_name;

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getRejectTime() {
        return rejectTime;
    }

    public void setRejectTime(String rejectTime) {
        this.rejectTime = rejectTime;
    }

    public String getCo_id() {
            return co_id;
        }

        public void setCo_id(String co_id) {
            this.co_id = co_id;
        }

        public String getConstract_sn() {
            return constract_sn;
        }

        public void setConstract_sn(String constract_sn) {
            this.constract_sn = constract_sn;
        }

        public String getSupplier_name() {
            return supplier_name;
        }

        public void setSupplier_name(String supplier_name) {
            this.supplier_name = supplier_name;
        }

        public String getCycle() {
            return cycle;
        }

        public void setCycle(String cycle) {
            this.cycle = cycle;
        }

        public String getAccountTpye() {
            return accountTpye;
        }

        public void setAccountTpye(String accountTpye) {
            this.accountTpye = accountTpye;
        }

        public String getCheck_com_id() {
            return check_com_id;
        }

        public void setCheck_com_id(String check_com_id) {
            this.check_com_id = check_com_id;
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

        public String getUpTime() {
            return upTime;
        }

        public void setUpTime(String upTime) {
            this.upTime = upTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getQ_id() {
            return q_id;
        }

        public void setQ_id(String q_id) {
            this.q_id = q_id;
        }

        public String getCancel_remarks() {
            return cancel_remarks;
        }

        public void setCancel_remarks(String cancel_remarks) {
            this.cancel_remarks = cancel_remarks;
        }

        public String getReject_remarks() {
            return reject_remarks;
        }

        public void setReject_remarks(String reject_remarks) {
            this.reject_remarks = reject_remarks;
        }

}
