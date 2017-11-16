package com.dongxi.demo.viewpager_gridview;

/**
 * Created by Administrator on 2017/11/9.
 */

public class ProdctBean {
    private String name ;
    private int url ;
    private boolean isSelect ;

    public ProdctBean(String name, int image, boolean isSelect) {
        this.name = name ;
        this.url = image ;
        this.isSelect = isSelect ;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }
}
