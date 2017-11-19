package com.dongxi.rxdemo.thumbup;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2017/10/16.
 */

public class ThumbUp {
    private Drawable thumbUpImg ;
    private int thumbUpCount ;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    private boolean isSelect ;

    public Drawable getThumbUpImg() {
        return thumbUpImg;
    }

    public void setThumbUpImg(Drawable thumbUpImg) {
        this.thumbUpImg = thumbUpImg;
    }

    public int getThumbUpCount() {
        return thumbUpCount;
    }

    public void setThumbUpCount(int thumbUpCount) {
        this.thumbUpCount = thumbUpCount;
    }

    public void increase() {
        thumbUpCount++ ;
    }
}
