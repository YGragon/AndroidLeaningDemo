package com.dongxi.rxdemo.utils.recyclerview;

/**
 * Created by Administrator on 2017/9/4.
 */

public interface MultiItemType<T> {
    int getLayoutId(int itemType);
    int getItemViewType(int position, T t);
}
