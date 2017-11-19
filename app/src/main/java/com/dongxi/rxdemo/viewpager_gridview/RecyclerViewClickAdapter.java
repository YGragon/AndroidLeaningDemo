package com.dongxi.rxdemo.viewpager_gridview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dongxi.rxdemo.utils.ToastUtil;
import com.dongxi.rxdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/12.
 */

public class RecyclerViewClickAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext ;
    private List<ProdctBean> mProdctBeanList ;
    private int mIndex; // 页数下标，标示第几页，从0开始
    private int mPargerSize;// 每页显示的最大的数量
    private int clickTemp = -1 ; // 临时的点击效果

    public RecyclerViewClickAdapter(Context context, List<ProdctBean> prodctBeanList) {
        mContext = context;
        mProdctBeanList = prodctBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder){

            if (clickTemp == position){
                ((ViewHolder) holder).itemLayout.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
            }else {
                ((ViewHolder) holder).itemLayout.setBackgroundColor(Color.TRANSPARENT);
            }

            ((ViewHolder) holder).itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickTemp = position ;
                    notifyDataSetChanged();
                    ToastUtil.showShortToast("position=="+position);
                }
            });

            ((ViewHolder) holder).tv_name.setText(mProdctBeanList.get(position).getName());
            ((ViewHolder) holder).iv_nul.setImageResource(mProdctBeanList.get(position).getUrl());
        }
    }

    @Override
    public int getItemCount() {
        return mProdctBeanList.size();
    }

    public void setDatas(List<ProdctBean> list) {
        this.mProdctBeanList = list ;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout itemLayout ;
        private TextView tv_name ;
        private ImageView iv_nul ;

        public ViewHolder(View itemView) {
            super(itemView);
            itemLayout = (LinearLayout)itemView.findViewById(R.id.item_layout);
            tv_name = (TextView)itemView.findViewById(R.id.item_name);
            iv_nul = (ImageView)itemView.findViewById(R.id.item_image);
        }
    }
}
