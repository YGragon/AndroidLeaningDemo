package com.dongxi.rxdemo.update;

import android.content.Context;
import android.widget.TextView;

import com.dongxi.rxdemo.R;
import com.dongxi.rxdemo.utils.recyclerview.CommonAdapter;
import com.dongxi.rxdemo.utils.recyclerview.CommonViewHolder;

import java.util.List;

/**
 * Created by macmini002 on 17/9/14.
 */

public class UpdateAdapter extends CommonAdapter<String> {

    public UpdateAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void onBind(CommonViewHolder holder, String s, int position) {
        TextView descTv = holder.getView(R.id.tv_update_list_desc) ;
        descTv.setText(s);
    }
}
