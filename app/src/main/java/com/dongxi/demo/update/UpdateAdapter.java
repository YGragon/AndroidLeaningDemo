package com.dongxi.demo.update;

import android.content.Context;
import android.widget.TextView;

import com.dongxi.demo.R;
import com.dongxi.demo.utils.recyclerview.CommonAdapter;
import com.dongxi.demo.utils.recyclerview.CommonViewHolder;

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
