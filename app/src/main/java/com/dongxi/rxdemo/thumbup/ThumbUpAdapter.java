package com.dongxi.rxdemo.thumbup;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dongxi.rxdemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/16.
 */

public class ThumbUpAdapter extends BaseAdapter {

    private static final String TAG = "ThumbUpAdapter";

    private Context mContext ;
    private ArrayList<ThumbUp> mThumbUpList ;
    private LayoutInflater mInflater;

    /*
    根据position，保存点击的是哪一行数据，默认为false，点击后改为true
    */
    private Map<Integer, Boolean> isExist = new HashMap<Integer, Boolean>();

    public ThumbUpAdapter(Context context,ArrayList<ThumbUp> list) {
        Log.e(TAG, "ThumbUpAdapter: list.size=="+list.size());
        this.mContext = context ;
        this.mThumbUpList = list ;
        this.mInflater = LayoutInflater.from(context);
//        initData() ;
    }

    private void setData(ArrayList<ThumbUp> list) {
        this.mThumbUpList = list ;
    }

    public ArrayList<ThumbUp> initData(){
        for (int i = 0; i < 20; i++){
            ThumbUp thumbUp = new ThumbUp();
            thumbUp.setThumbUpCount(i);
            mThumbUpList.add(thumbUp) ;
        }

//        Intent intent = new Intent();
//        intent.setAction("action_broadcast") ;
//        mContext.sendBroadcast(intent);
        Log.e(TAG, "initData: 发送");
        Log.e(TAG, "initData: 发送"+mThumbUpList.size());
        return mThumbUpList ;
    }


    @Override
    public int getCount() {
        Log.e(TAG, "getCount: size="+mThumbUpList.size() );
        return mThumbUpList.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder ;
        if ( convertView == null){
            viewHolder = new ViewHolder();
            convertView =   mInflater.inflate(R.layout.thumb_up_item, null);

            viewHolder.imgThumbUp = (ImageView) convertView.findViewById(R.id.img_thumb_up);
            viewHolder.thumbUpCount = (TextView) convertView.findViewById(R.id.tv_thumb_up_count);
            viewHolder.thumbUpItem = (LinearLayout) convertView.findViewById(R.id.thumb_up_item) ;

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.thumbUpCount.setText(mThumbUpList.get(position).getThumbUpCount()+"");


        viewHolder.thumbUpItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,position+"");
//                if(mThumbUpList.getPraise()==0){
                if(isExist.get(position) == false){
                    final Handler handler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                            switch (msg.what){
                                case 1:
                                        isExist.put(position, true);
                                        viewHolder.thumbUpCount.setText(mThumbUpList.get(position).getThumbUpCount()+1);
                                        viewHolder.imgThumbUp.setImageResource(R.drawable.message);
                                        notifyDataSetChanged();
                                        break;
                                case 2:
                                    Toast.makeText(mContext, "失败", Toast.LENGTH_LONG).show();
                                    break;
                            }
                        }
                    };
                    new Thread(){
                        @Override
                        public void run() {
                            Message msg = new Message();
                            msg.what=1;
                            handler.sendMessage(msg);
                        }
                    }.start();
                }
            }
//            else{
//                Toast.makeText(mContext, "已经点击过", Toast.LENGTH_LONG).show();
//            }


//                viewHolder.imgThumbUp.setImageDrawable(mContext.getResources().getDrawable(R.drawable.dot_focused));
//                viewHolder.thumbUpCount.setText(mThumbUpList.get(position).getThumbUpCount()+1+"");
//            }
        });


        return convertView;
    }

    class ViewHolder{
        LinearLayout thumbUpItem ;
        ImageView imgThumbUp ;
        TextView thumbUpCount ;
    }

}
