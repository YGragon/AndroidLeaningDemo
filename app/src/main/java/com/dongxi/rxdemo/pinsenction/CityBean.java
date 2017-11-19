package com.dongxi.rxdemo.pinsenction;

import java.util.List;

public class CityBean {
    private int cityId;
    private String cityName;
    private int superiorId;//上级城市id
    private List<CityBean> subordinateList;//下级城市集合

    public List<CityBean> getSubordinateList() {
        return subordinateList;
    }

    public void setSubordinateList(List<CityBean> subordinateList) {
        this.subordinateList = subordinateList;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(int superiorId) {
        this.superiorId = superiorId;
    }
}
