package com.dongxi.demo.pinsenction;

/**
 * Created by macmini002 on 17/10/13.
 */

public class Item {
    public static final int ITEM = 0;//判断是否是普通item
    public static final int SECTION = 1;//判断是否是需要置顶悬停的item

    public final int type;//外部传入的标记
    public final CityBean cityBean;//外部传入的数据，这里我们将它写成城市实体类，可以任意更换

    //如果该item是头，则集合标记失效
    public int sectionPosition;//头标记
    public int listPosition;//集合标记

    public Item(int type, CityBean cityBean) {
        this.type = type;
        this.cityBean = cityBean;
    }

    public CityBean getCityBean() {
        return cityBean;
    }
}
