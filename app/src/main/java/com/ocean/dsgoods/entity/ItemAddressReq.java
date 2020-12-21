package com.ocean.dsgoods.entity;


import com.ocean.dsgoods.callback.CityInterface;

public class ItemAddressReq implements CityInterface {


    /**
     * areaAbbName : 葵青
     * areaCode : 810308
     * areaEnName : KUI QING DISTRICT
     * areaName : 葵青区
     * areaType : 3
     * areaZip : 810308
     * id : 3720
     * parentId : 3712
     */


    private String areaName;

    private String id;
    private String parentId;



    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String getCityName() {
        return areaName;
    }
}
