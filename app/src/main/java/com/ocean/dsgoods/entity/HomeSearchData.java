package com.ocean.dsgoods.entity;

/**
 * Created by James on 2020/11/23.
 */
public class HomeSearchData {

        /**
         * wa_id : 运单id
         * type : 1-发货方  2-收货方
         */

        private String wa_id;
        private String type;

        public String getWa_id() {
            return wa_id;
        }

        public void setWa_id(String wa_id) {
            this.wa_id = wa_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

}
