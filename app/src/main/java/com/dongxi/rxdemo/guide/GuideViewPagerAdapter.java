package com.dongxi.rxdemo.guide;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/9/29.
 */

public class GuideViewPagerAdapter extends PagerAdapter {

    private List<View> mViews ;

    public GuideViewPagerAdapter(List<View> views) {
        super();
        mViews = views;
    }

    @Override
    public int getCount() {
        if (mViews != null){
            return mViews.size();
        }
        return 0;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        ((ViewPager)container).removeView(mViews.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager)container).addView(mViews.get(position),0);

        return mViews.get(position);

    }
}
