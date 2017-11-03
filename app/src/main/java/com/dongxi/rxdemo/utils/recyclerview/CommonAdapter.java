package com.dongxi.rxdemo.utils.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder>{
    protected Context mContext ;
    protected int[] mLayoutId ;
    protected List<T> mDatas ;
//    protected LayoutInflater mInflater ;

    public CommonAdapter(Context context, int layoutId, List<T> datas) {
        this.mContext = context;
        this.mDatas = datas;
        //不使用多布局时的构造函数，将layoutId放到布局资源Id数组layoutIds中
        this.mLayoutId = new int[]{layoutId};
//        mInflater = LayoutInflater.from(mContext) ;
    }


    //使用多布局时的构造函数，可以接收传入的布局资源Id数组
    public CommonAdapter(Context context, int[] layoutIds, List<T> datas ) {
        this.mContext = context;
        this.mDatas = datas;
        this.mLayoutId = layoutIds;
    }

    public void setData(List<T> data) {
        this.mDatas = data;
    }
    public List<T> getData() {
        return this.mDatas;
    }

    @Override
    public int getItemCount() {
        int size = mDatas.size() ;
//        if(isHasFooter)
//            size ++;
//        if(isHasHeader)
//            size++;
        return mDatas == null ? 0 : size;
    }
    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据viewType从布局资源数组中加载指定的布局
        //1.当不需要加载多布局时，子类继承BaseAdapter，不用覆写getItemViewType()
        //此时，viewType值默认为0；
        //2.当需要加载多布局时，子类继承MultiLayoutsBaseAdapter，需要覆写getItemViewType()
        //此时，viewType值为子类adapter指定的值；

        return CommonViewHolder.getHolder(mContext, parent, mLayoutId[viewType]);
    }
    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        //调用由子类实现的抽象方法，将操作下放到子类中
        onBind(holder, mDatas.get(position), position);
    }
    //抽象方法，让子类继承实现
    public abstract void onBind(CommonViewHolder holder, T t, int position);
}
