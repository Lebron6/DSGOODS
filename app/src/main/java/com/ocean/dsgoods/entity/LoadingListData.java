package com.ocean.dsgoods.entity;

import java.util.List;

/**
 * Created by James on 2020/10/15.
 */
public class LoadingListData {

        private String status;
        private String id;
        private List<String> header;
        private List<List<String>> list;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getHeader() {
            return header;
        }

        public void setHeader(List<String> header) {
            this.header = header;
        }

        public List<List<String>> getList() {
            return list;
        }

        public void setList(List<List<String>> list) {
            this.list = list;
        }

}
