package com.dongxi.rxdemo.db.gank_test.adapter;

import android.util.Log;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dongxi.rxdemo.R;
import com.dongxi.rxdemo.db.gank_test.bean.Gank;

import java.util.List;

/**
 * Created by macmini002 on 17/12/13.
 */

public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<Gank.GankItem,BaseViewHolder> {

    public MultipleItemQuickAdapter(List data) {
        super(data);
        addItemType(Gank.GankItem.TEXT, R.layout.text_view);
        addItemType(Gank.GankItem.IMG, R.layout.image_view);
        addItemType(Gank.GankItem.VIDEO, R.layout.video_view);
        Log.e(TAG, "MultipleItemQuickAdapter: size========="+data.size());
    }


    @Override
    protected void convert(BaseViewHolder helper, Gank.GankItem item) {
        switch (helper.getItemViewType()) {
            case Gank.GankItem.TEXT:
                helper.setText(R.id.id_gank_txt_tv,item.getDesc()) ;
                break;
            case Gank.GankItem.IMG:
//                helper.setImageUrl(R.id.tv, item.getContent());
                break;
            case Gank.GankItem.VIDEO:

                break;
            default:
        
                break;
        }
    }

  
}
