package com.dongxi.rxdemo.db.gank_test;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dongxi.rxdemo.R;
import com.dongxi.rxdemo.global.BaseApplication;
import com.dongxi.rxdemo.utils.recyclerview.CommonAdapter;
import com.dongxi.rxdemo.utils.recyclerview.CommonViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/10/5.
 */

public class GankAdapter extends CommonAdapter<ResultsBean> {
    public GankAdapter(Context context, int layoutId, List<ResultsBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void onBind(CommonViewHolder holder, ResultsBean resultsBean, int position) {

        ImageView img = holder.getView(R.id.img_gank) ;
        TextView desc = holder.getView(R.id.tv_desc_gank) ;

        desc.setText(resultsBean.getDesc());
        Glide.with(BaseApplication.getApplication()).load(resultsBean.getUrl()).into(img) ;
    }
}
