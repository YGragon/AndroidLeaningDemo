package com.dongxi.rxdemo.utils.recyclerview;

import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4.
 */

public abstract class MultiLayoutsCommoAdapter<T> extends CommonAdapter<T> {

    // 头部控件
    private View mHeaderView;

    // 底部控件
    private View mFooterView;


    // item 的三种类型
    public static final int ITEM_TYPE_NORMAL = 0X1111; // 正常的item类型
    public static final int ITEM_TYPE_HEADER = 0X1112; // header
    public static final int ITEM_TYPE_FOOTER = 0X1113; // footer


    private boolean isHasHeader = false;

    private boolean isHasFooter = false;

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
