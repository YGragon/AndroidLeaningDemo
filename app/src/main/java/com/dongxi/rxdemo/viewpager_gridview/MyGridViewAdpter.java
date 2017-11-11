package com.dongxi.rxdemo.viewpager_gridview;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dongxi.rxdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */

public class MyGridViewAdpter extends BaseAdapter {

    private static final String TAG = "MyGridViewAdpter";
    private Context context;
    private List<ProdctBean> lists;//数据源
    private int mIndex; // 页数下标，标示第几页，从0开始
    private int mPargerSize;// 每页显示的最大的数量
    private int clickTemp = -1 ; // 临时的点击效果

    private OnPositionClick mOnPositionClick ;
    public interface OnPositionClick{
        void click(int pos) ;
    }
    public void setOnPositionClick(OnPositionClick onPositionClick){
        this.mOnPositionClick = onPositionClick ;
    }

    public MyGridViewAdpter(Context context, List<ProdctBean> lists, int mIndex, int mPargerSize) {
        Log.e(TAG, "MyGridViewAdpter: 走这里");
        Log.e(TAG, "MyGridViewAdpter: size"+lists.size());
        this.context = context;
        this.lists = lists;
        this.mIndex = mIndex;
        this.mPargerSize = mPargerSize;
    }

    public void setSeclection(int position){
        clickTemp = position ;
    }



    /**
     * 先判断数据及的大小是否显示满本页lists.size() > (mIndex + 1)*mPagerSize
     * 如果满足，则此页就显示最大数量lists的个数
     * 如果不够显示每页的最大数量，那么剩下几个就显示几个
     */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lists.size() > (mIndex + 1) * mPargerSize ?
                mPargerSize : (lists.size() - mIndex*mPargerSize);
    }

    @Override
    public ProdctBean getItem(int arg0) {
        // TODO Auto-generated method stub
        return lists.get(arg0 + mIndex * mPargerSize);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0 + mIndex * mPargerSize;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_view, null);
            holder.itemLayout = (LinearLayout)convertView.findViewById(R.id.item_layout);
            holder.tv_name = (TextView)convertView.findViewById(R.id.item_name);
            holder.iv_nul = (ImageView)convertView.findViewById(R.id.item_image);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        //重新确定position因为拿到的总是数据源，数据源是分页加载到每页的GridView上的
        final int pos = position + mIndex * mPargerSize;//假设mPageSiez
        //假设mPagerSize=8，假如点击的是第二页（即mIndex=1）上的第二个位置item(position=1),那么这个item的实际位置就是pos=9
        holder.tv_name.setText(lists.get(pos).getName() + "");
        holder.iv_nul.setImageResource(lists.get(pos).getUrl());
//
        if (lists.get(pos).isSelect()){
            holder.itemLayout.setBackgroundColor(Color.YELLOW);
        }else {
            holder.itemLayout.setBackgroundColor(Color.TRANSPARENT);
        }

//        if (clickTemp == position){
//            holder.itemLayout.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
//        }else {
//            holder.itemLayout.setBackgroundColor(Color.TRANSPARENT);
//        }
        //添加item监听
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                setSeclection(position);
                if (mOnPositionClick != null){
                    mOnPositionClick.click(pos);
                    notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }
    static class ViewHolder{
        private TextView tv_name;
        private ImageView iv_nul;
        private LinearLayout itemLayout ;
    }
}
