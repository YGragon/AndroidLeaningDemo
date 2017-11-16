package com.dongxi.demo.utils.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/9/4.
 */

public class CommonViewHolder extends RecyclerView.ViewHolder {

    private View mView ;
    SparseArray<View> mViewSparseArray ; // 存放itemView 中的控件

    public CommonViewHolder(View itemView) {
        super(itemView);
        this.mView = itemView ;
        mViewSparseArray = new SparseArray<>() ;
    }

    //给adapter使用，返回holder
    public static <T extends CommonViewHolder> T getHolder(Context context , ViewGroup parent, int layoutId){
        return (T) new CommonViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }
    // 获取itemView中的控件
    public <T extends View> T getView(int viewId){
        View childView = mViewSparseArray.get(viewId) ;
        if (childView == null){
            childView = mView.findViewById(viewId) ;
            mViewSparseArray.put(viewId, childView);
        }
        return (T)childView ;
    }
    // 给控件添加点击事件
    public CommonViewHolder setOnClickListener(int viewId, View.OnClickListener listener){
        View view = getView(viewId) ;
        view.setOnClickListener(listener);
        return this ;
    }
}
