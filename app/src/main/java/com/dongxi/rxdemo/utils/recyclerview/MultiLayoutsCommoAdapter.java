package com.dongxi.rxdemo.utils.recyclerview;

import android.content.Context;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4.
 */

public abstract class MultiLayoutsCommoAdapter<T> extends CommonAdapter<T> {

    public MultiLayoutsCommoAdapter(Context context, int[] layoutIds, List<T> datas) {
        super(context, layoutIds, datas);
    }

    @Override
    public int getItemViewType(int position) {
        //调用子类继承实现的获取指定布局类型的抽象方法
        return getItemsType(position);
    }

    @Override
    public void onBind(CommonViewHolder holder, T t, int position) {
        //调用子类继承实现的对加载后特定布局中控件操作的抽象方法，同时传入布局类型
        onBinds(holder, t, position, getItemViewType(position));
    }

    //获取指定的布局类型的抽象方法，让子类继承实现
    public abstract int getItemsType(int position);
    //对加载后的特定布局中的控件操作的抽象方法，让子类继承实现
    public abstract void onBinds(CommonViewHolder holder, T t, int position, int itemType);
}
