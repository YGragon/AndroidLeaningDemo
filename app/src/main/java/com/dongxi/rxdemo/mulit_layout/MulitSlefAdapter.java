package com.dongxi.rxdemo.mulit_layout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dongxi.rxdemo.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/2.
 */

public class MulitSlefAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "MulitSlefAdapter";
    private static final int LAYOUT1 = 0;
    private static final int LAYOUT2 = 1;
    private static final int LAYOUT3 = 2;
    private Context mContext ;

    private ArrayList<MulitLayoutActivity.Test> mTestArrayList ;

    private String str1 ;
    private String str2 ;

    public MulitSlefAdapter(MulitLayoutActivity mulitLayoutActivity, ArrayList<MulitLayoutActivity.Test> testArrayList) {
        mTestArrayList = testArrayList;
        mContext = mulitLayoutActivity ;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        RecyclerView.ViewHolder holder ;
        if (viewType == LAYOUT1){
            view = LayoutInflater.from(mContext).inflate(R.layout.mulit_layout1,parent,false) ;
            holder = new ViewHolderTV1(view) ;
        }else if (viewType == LAYOUT2){
            view = LayoutInflater.from(mContext).inflate(R.layout.mulit_layout2,parent,false) ;
            holder = new ViewHolderTV2(view) ;
        }else {
            view = LayoutInflater.from(mContext).inflate(R.layout.mulit_layout3,parent,false) ;
            holder = new ViewHolderTV3(view) ;
        }
        return holder;
    }

    public void setTv1(String tv1){
        this.str1 = tv1 ;
    }

    public void setTv2(String tv2){
        this.str2 = tv2 ;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolderTV1){
            ((ViewHolderTV1) holder).mTextView1.setText(mTestArrayList.get(position).text);
//            ((ViewHolderTV1) holder).mTextView1.setText(str1);
        }else if (holder instanceof ViewHolderTV2){
            ((ViewHolderTV2) holder).mTextView2.setText(mTestArrayList.get(position).text);
//            ((ViewHolderTV2) holder).mTextView2.setText(str2);
        }else {
            ((ViewHolderTV3) holder).mTextView3.setText(mTestArrayList.get(position).text);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == LAYOUT1){
            return LAYOUT1 ;
        }else if (position == LAYOUT2){
            return LAYOUT2 ;
        }else {
            return LAYOUT3 ;
        }
    }

    @Override
    public int getItemCount() {
        return mTestArrayList.size();
    }

    class ViewHolderTV1 extends RecyclerView.ViewHolder{
        TextView mTextView1 ;

        public ViewHolderTV1(View itemView) {
            super(itemView);
            mTextView1 = (TextView) itemView.findViewById(R.id.mulit_tv1);
        }
    }
    class ViewHolderTV2 extends RecyclerView.ViewHolder{
        TextView mTextView2 ;

        public ViewHolderTV2(View itemView) {
            super(itemView);
            mTextView2 = (TextView) itemView.findViewById(R.id.mulit_tv2);
        }
    }
    class ViewHolderTV3 extends RecyclerView.ViewHolder{
        TextView mTextView3 ;

        public ViewHolderTV3(View itemView) {
            super(itemView);
            mTextView3 = (TextView) itemView.findViewById(R.id.mulit_tv3);
        }
    }
}
