package com.ocean.dsgoods.entity;

/**
 * Created by James on 2020/8/17.
 */
public class SettingResult {

        /**
         * s_name : lihuan
         * company_no : H0001
         * name : 发收货方1
         * phone : 181****4682
         * headimg : http://img.oceanscm.com/3plheadimg/2020081829947103223694.png
         * email :
         * department : 研发
         * position : php开发
         * service_phone : 400-0000-0000
         * isemail : 2
         */

        private String s_name;
        private String company_no;
        private String name;
        private String phone;
        private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private String headimg;
        private String email;
        private String department;
        private String position;
        private String service_phone;
        private int isemail;

        public String getS_name() {
            return s_name;
        }

        public void setS_name(String s_name) {
            this.s_name = s_name;
        }

        public String getCompany_no() {
            return company_no;
        }

        public void setCompany_no(String company_no) {
            this.company_no = company_no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getService_phone() {
            return service_phone;
        }

        public void setService_phone(String service_phone) {
            this.service_phone = service_phone;
        }

        public int getIsemail() {
            return isemail;
        }

        public void setIsemail(int isemail) {
            this.isemail = isemail;
        }
}
