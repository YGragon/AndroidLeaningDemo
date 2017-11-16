package com.dongxi.demo.common;

import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2017/9/29.
 */

public class GuideView1PagerAdapter extends BasePagerAdapter<View> {

    public GuideView1PagerAdapter(List<View> data) {
        super(data);
    }

    @Override
    public View newView(int position) {
        // 使用示例
//        View view = View.inflate(mContext, R.layout.view_remote_capture, null);
//        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
//        UIUtil.setLayoutParamsHeight(imageView, R.dimen.padding_common, 4, 3);
//        imageView.setAdjustViewBounds(true);
//        mImageLoader.displayImage(UrlUtil.imageUrl(getItem(position).getImgUrl()), imageView);
        return null;
    }
}
