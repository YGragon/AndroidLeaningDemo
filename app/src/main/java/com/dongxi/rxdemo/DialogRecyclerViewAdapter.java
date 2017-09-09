package com.dongxi.rxdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/27.
 */

public class DialogRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "DialogRecyclerViewAdapter";
    private Context mContext ;
    private List<String> mStringList ;

    public DialogRecyclerViewAdapter(Context context, List<String> stringList) {
        mContext = context;
        mStringList = stringList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_recyclerview_item,parent,false) ;
        return new StringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((StringViewHolder) holder).name.setText(mStringList.get(position));
    }

    @Override
    public int getItemCount() {
        return mStringList.size() > 0 ? mStringList.size():0 ;
    }

    public class StringViewHolder extends RecyclerView.ViewHolder{

        private TextView name ;
        public StringViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name) ;
        }
    }
}
