package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/10/16.
 */
public class AddInitTwo {
    /**
     * code : 1
     * msg : 请求成功
     * time : 1600327774
     * data : [{"dp_id":"1","serial_num":"T122","list":[{"g_id":"4","pro_num":"HW202008114436","name":"超级洗衣机","packType":"周转箱","takeNum":"100","weight":"0.00","volume":"0.00","num":"100","pnum":"1"}]},{"dp_id":"22","serial_num":"T202008283947","list":[{"g_id":"4","pro_num":"HW202008114436","name":"超级洗衣机","packType":"周转箱","takeNum":"100","weight":"0.00","volume":"0.00","num":"0","pnum":"0"}]}]
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
         * dp_id : 1
         * serial_num : T122
         * list : [{"g_id":"4","pro_num":"HW202008114436","name":"超级洗衣机","packType":"周转箱","takeNum":"100","weight":"0.00","volume":"0.00","num":"100","pnum":"1"}]
         */

        private String dp_id;
        private String serial_num;
        private List<ListBean> list;
        private int type=0;//0 未选中  1选中

        public String getDp_id() {
            return dp_id;
        }

        public void setDp_id(String dp_id) {
            this.dp_id = dp_id;
        }

        public String getSerial_num() {
            return serial_num;
        }

        public void setSerial_num(String serial_num) {
            this.serial_num = serial_num;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * g_id : 4
             * pro_num : HW202008114436
             * name : 超级洗衣机
             * packType : 周转箱
             * takeNum : 100
             * weight : 0.00
             * volume : 0.00
             * num : 100
             * pnum : 1
             */

            private String g_id;
            private String pro_num;
            private String name;
            private String packType;
            private String takeNum;
            private String weight;
            private String volume;
            private String num;
            private String pnum;

            public String getG_id() {
                return g_id;
            }

            public void setG_id(String g_id) {
                this.g_id = g_id;
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

            public String getPackType() {
                return packType;
            }

            public void setPackType(String packType) {
                this.packType = packType;
            }

            public String getTakeNum() {
                return takeNum;
            }

            public void setTakeNum(String takeNum) {
                this.takeNum = takeNum;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getVolume() {
                return volume;
            }

            public void setVolume(String volume) {
                this.volume = volume;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getPnum() {
                return pnum;
            }

            public void setPnum(String pnum) {
                this.pnum = pnum;
            }
        }
    }
}
