package com.dongxi.rxdemo.thumbup;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dongxi.rxdemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/10/16.
 * 点赞
 * 全选，单选，反选
 */

public class ThumbUpAdapter extends BaseAdapter {

    private static final String TAG = "ThumbUpAdapter";
    private final HashMap<Integer, Boolean> map ;

    private Context mContext ;

    private ArrayList<ThumbUp> mThumbUpList ;
    private LayoutInflater mInflater;

    public OnThumbUpClickListener mOnThumbUpClickListener ;
    public interface  OnThumbUpClickListener{
        void onItemClick(int position) ;
    }
    public void setThumbUpListener(OnThumbUpClickListener listener){
        this.mOnThumbUpClickListener = listener ;
    }
    public ThumbUpAdapter(Context context,ArrayList<ThumbUp> list) {
        Log.e(TAG, "ThumbUpAdapter: list.size=="+list.size());
        this.mContext = context ;
        this.mThumbUpList = list ;
        this.mInflater = LayoutInflater.from(context);

        // 模拟数据
        map = new HashMap<>();
        for (int  i = 0 ; i < list.size(); i++){
            if ( i % 2 == 0 ){
                // 偶数
                map.put(i,true) ;
            }else {
                map.put(i,false) ;
            }
        }
    }
    /**
     * 单选
     */
    public void singleSelect(){
        for (int i = 0; i < mThumbUpList.size(); i++){
            if (map.get(i)){
                map.put(i,!map.get(i)) ;  // true
            }
        }
        notifyDataSetChanged();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(false);
        }
        map.put(0, true);
        notifyDataSetChanged();

    }
    /**
     * 全选
     */
    public void allSelect(){

        for(int i=0;i<mThumbUpList.size();i++){
            map.put(i,true);

        }
        notifyDataSetChanged();//注意这一句必须加上，否则checkbox无法正常更新状态

    }

    /**
     * 反选
     */
    public void revertSelect(){

        for(int i = 0; i < mThumbUpList.size(); i++){
            if(!map.get(i)){
                map.put(i, true);
            }else{
                map.put(i, false);
            }
            notifyDataSetChanged();
        }
        notifyDataSetChanged();

    }
    @Override
    public int getCount() {
        return mThumbUpList.size();
    }

    @Override
    public ThumbUp getItem(int arg0) {
        // TODO Auto-generated method stub
        return mThumbUpList.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder ;
        if ( convertView == null){
            viewHolder = new ViewHolder();
            convertView =   mInflater.inflate(R.layout.thumb_up_item, null);

            viewHolder.imgThumbUp = (ImageView) convertView.findViewById(R.id.img_thumb_up);
            viewHolder.thumbUpCount = (TextView) convertView.findViewById(R.id.tv_thumb_up_count);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            viewHolder.thumbUpItem = (LinearLayout) convertView.findViewById(R.id.thumb_up_item) ;

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.thumbUpCount.setText(mThumbUpList.get(position).getThumbUpCount()+"");
//        viewHolder.imgThumbUp.setImageResource(R.drawable.dot_normal);

        // 取出bean中当记录状态是否为true，是的话则给img设置focus点赞图片
        if (mThumbUpList.get(position).isSelect()) {
            viewHolder.imgThumbUp.setImageResource(R.drawable.message);
        } else {
            viewHolder.imgThumbUp.setImageResource(R.drawable.dot_normal);
        }

        if (map.get(position)){
            //true
            viewHolder.checkBox.setChecked(true);
        }else {
            viewHolder.checkBox.setChecked(false);
        }

        viewHolder.thumbUpItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnThumbUpClickListener != null){
                    mOnThumbUpClickListener.onItemClick(position);
                }
                Toast.makeText(mContext, "点击了"+position, Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }

    class ViewHolder{
        LinearLayout thumbUpItem ;
        ImageView imgThumbUp ;
        TextView thumbUpCount ;
        CheckBox checkBox ;
    }

}
