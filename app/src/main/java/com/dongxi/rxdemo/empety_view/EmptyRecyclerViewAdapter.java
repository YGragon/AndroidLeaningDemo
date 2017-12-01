package com.dongxi.rxdemo.empety_view;

import android.content.Context;

import com.dongxi.rxdemo.utils.recyclerview.CommonAdapter;
import com.dongxi.rxdemo.utils.recyclerview.CommonViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/10/25.
 */

public class EmptyRecyclerViewAdapter extends CommonAdapter<DataBean> {

    private static final String TAG = "EmptyRecyclerViewAdapter";

    public EmptyRecyclerViewAdapter(Context context, int layoutId, List<DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void onBind(CommonViewHolder holder, DataBean dataBean, int position) {

    }
}
