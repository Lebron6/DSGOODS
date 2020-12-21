package com.ocean.dsgoods.entity;

/**
 * Created by James on 2020/9/4.
 */
public class UpLoadResult {

        /**
         * url : http://img.oceanscm.com/
         * path : 3plheadimg/2020090229801493241649.png
         */

        private String url;
        private String path;
    private String filepath;

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

}
