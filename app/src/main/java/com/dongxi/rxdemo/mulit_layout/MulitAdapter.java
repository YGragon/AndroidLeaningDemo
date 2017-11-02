package com.dongxi.rxdemo.mulit_layout;

import android.content.Context;
import android.widget.TextView;

import com.dongxi.rxdemo.R;
import com.dongxi.rxdemo.utils.recyclerview.CommonViewHolder;
import com.dongxi.rxdemo.utils.recyclerview.MultiLayoutsCommoAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/2.
 */

public class MulitAdapter extends MultiLayoutsCommoAdapter<MulitLayoutActivity.Test> {

    private static final String TAG = "MulitAdapter";
    private static final int LAYOUT1 = 0;
    private static final int LAYOUT2 = 1;
    private static final int LAYOUT3 = 2;

    private String str1 ;
    private String str2 ;

    private ArrayList<MulitLayoutActivity.Test> mTestArrayList = new ArrayList<>() ;

    public MulitAdapter(Context context, int[] layoutIds, ArrayList<MulitLayoutActivity.Test> datas) {
        super(context, layoutIds, datas);
        this.mTestArrayList = datas ;
    }

    public void setTv1(String tv1){
        this.str1 = tv1 ;
    }

    public void setTv2(String tv2){
        this.str2 = tv2 ;
    }

    @Override
    public int getItemsType(int position) {
        if (position == LAYOUT1){
            return LAYOUT1 ;
        }else if (position == LAYOUT2){
            return LAYOUT2 ;
        }else {
            return LAYOUT3 ;
        }
    }

    @Override
    public void onBinds(CommonViewHolder holder, MulitLayoutActivity.Test test, int position, int itemType) {
        switch (itemType) {
            case LAYOUT1:
                TextView tv1 = holder.getView(R.id.mulit_tv1);
//                tv1.setText(str1);
                tv1.setText(mTestArrayList.get(position).text);
                break;
            case LAYOUT2:
                TextView tv2 = holder.getView(R.id.mulit_tv2);
//                tv2.setText(str2);
                tv2.setText(mTestArrayList.get(position).text);
                break;
            case LAYOUT3:
                TextView tv3 = holder.getView(R.id.mulit_tv3);
                tv3.setText(test.text);

                break;
            default:

                break;
        }
    }
}
