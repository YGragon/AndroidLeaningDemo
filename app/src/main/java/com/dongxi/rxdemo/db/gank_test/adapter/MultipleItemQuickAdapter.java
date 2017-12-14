package com.dongxi.rxdemo.db.gank_test.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dongxi.rxdemo.R;
import com.dongxi.rxdemo.db.gank_test.bean.Gank;

import java.util.List;

/**
 * Created by macmini002 on 17/12/13.
 */

public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<Gank.GankItem,BaseViewHolder> {
    private Context mContent ;
    public MultipleItemQuickAdapter(Context context ,List data) {
        super(data);
        this.mContent = context ;
        addItemType(Gank.GankItem.TEXT, R.layout.text_view);
        addItemType(Gank.GankItem.IMG, R.layout.image_view);
        Log.e(TAG, "MultipleItemQuickAdapter: data.size=="+data.size());
    }


    @Override
    protected void convert(BaseViewHolder helper, Gank.GankItem item) {

        Log.e(TAG, "convert: 尽力啊啊啊啊");
        Log.e(TAG, "convert: 尽力啊啊啊啊type==="+helper.getItemViewType());
        switch (helper.getItemViewType()) {
            case Gank.GankItem.TEXT:
                Log.e(TAG, "convert: 文字");
                helper.setText(R.id.id_gank_txt_tv,item.getDesc()) ;
                break;
            case Gank.GankItem.IMG:
                Log.e(TAG, "convert: 图片");
                ImageView imageView = helper.getView(R.id.image_view);
                Glide.with(mContext).load(item.getUrl()).into(imageView) ;
                break;

        }
    }

  
}
