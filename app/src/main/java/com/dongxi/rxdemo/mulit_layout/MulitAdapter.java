package com.dongxi.rxdemo.mulit_layout;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    private static final int LAYOUT4 = 3;

    private String str1 ;
    private String str2 ;

    private ArrayList<MulitLayoutActivity.Test> mTestArrayList = new ArrayList<>() ;
    private OnItemInterface mClickListener ;
    public interface OnItemInterface{
        void onItemClick(int position) ;
    }
    public void setOnClick(OnItemInterface onItemInterface){
        this.mClickListener = onItemInterface ;
    }


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
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public int getItemsType(int position) {
        Log.e(TAG, "getItemsType: position == "+position);
        Log.e(TAG, "getItemsType:  mTestArrayList.size() == "+ mTestArrayList.size());
        if (position == LAYOUT1){
            return LAYOUT1 ;
        }else if (position == LAYOUT2){
            return LAYOUT2 ;
        }else if (position + 1 == mTestArrayList.size()){
            return LAYOUT4 ;
        }else {
            return LAYOUT3 ;
        }
    }

    @Override
    public void onBinds(CommonViewHolder holder, MulitLayoutActivity.Test test, final int position, int itemType) {
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

//                if ( position == mTestArrayList.size()-1){
//                    tv3.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                        }
//                    });
//                }


                break;
            case LAYOUT4:
                Log.e(TAG, "onBinds: look more");
                TextView lookMoreTv = holder.getView(R.id.tv_look_more) ;
                lookMoreTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mClickListener != null){
                            mClickListener.onItemClick(position);
                        }
                        Toast.makeText(mContext, "显示数据", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:

                break;
        }
    }
}
