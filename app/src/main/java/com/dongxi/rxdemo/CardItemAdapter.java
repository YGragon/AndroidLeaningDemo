package com.dongxi.rxdemo;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/20.
 */

public class CardItemAdapter extends RecyclerView.Adapter<CardItemAdapter.ViewHolder> {


    private Context mContext;
    private List<String> mTextList;
    private OnMyClickListener mMyListener ;

    public interface OnMyClickListener {
        void cardClickListener(View view ,int position) ;
    }

    public void setCardClickListener(OnMyClickListener listener){
        this.mMyListener = listener ;
    }

    public CardItemAdapter(Context context, List list) {
        this.mContext = context;
        this.mTextList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_card_view_text, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String text = mTextList.get(position);
        holder.mItemCardViewTxt.setText(text);
        holder.mItemCardViewTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMyListener != null){
                    mMyListener.cardClickListener(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTextList.size() > 0?mTextList.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_card_view_txt)
        TextView mItemCardViewTxt;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView) ;
        }
    }
}
