package com.dongxi.rxdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/8/26.
 */

public class RightMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TEXT_TYPE = 1 ;
    private static final int TEXT_ICON_TYPE = 2 ;
    private Context mContext ;

    public RightMenuAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (getItemViewType(viewType) == TEXT_TYPE){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_right_menu,parent,false) ;
            return new TextViewHolder(view) ;
        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_right_text_ion_menu,parent,false) ;
            return new TextAndIconViewHolder(view) ;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case TEXT_TYPE:
                TextViewHolder holder1 = (TextViewHolder) holder;
                holder1.rightMenuText.setText(" I m text ");
                break;
            case TEXT_ICON_TYPE:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == TEXT_TYPE){
            return TEXT_TYPE ;
        }
        return super.getItemViewType(position);
    }

    public class TextViewHolder extends RecyclerView.ViewHolder{

        private TextView rightMenuText ;
        public TextViewHolder(View itemView) {
            super(itemView);
            rightMenuText= (TextView) itemView.findViewById(R.id.item_right_text);
        }
    }
    public class TextAndIconViewHolder extends RecyclerView.ViewHolder{

        private TextView rightMenuTxt ;
        private ImageView rightMenuImg ;

        public TextAndIconViewHolder(View itemView) {
            super(itemView);
            rightMenuTxt= (TextView) itemView.findViewById(R.id.item_right_text_and_icon);
            rightMenuImg= (ImageView) itemView.findViewById(R.id.item_right_text_and_icon_img);
        }
    }
}
