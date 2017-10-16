package com.dongxi.rxdemo.pinsenction;

import java.util.ArrayList;
import java.util.List;


public class TestData {
    private List<CityBean> data;

    public List<CityBean> initData() {
        data = new ArrayList<>();
        List<CityBean> beijingList = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            CityBean cityBean = new CityBean();
            cityBean.setCityId(i + 10);
            cityBean.setCityName("精彩" + i + "区");
            beijingList.add(cityBean);
        }
        CityBean cityBean = new CityBean();
        cityBean.setCityId(1);
        cityBean.setCityName("精彩");
        cityBean.setSubordinateList(beijingList);
        data.add(cityBean);

        List<CityBean> shanghaiList = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            CityBean cityBean1 = new CityBean();
            cityBean1.setCityId(i + 20);
            cityBean1.setCityName("全部" + i + "区");
            shanghaiList.add(cityBean1);
        }
        CityBean cityBean1 = new CityBean();
        cityBean1.setCityId(2);
        cityBean1.setCityName("全部");
        cityBean1.setSubordinateList(shanghaiList);
        data.add(cityBean1);
        return data;
    }
}
