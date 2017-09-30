package com.dongxi.rxdemo.utils.recyclerview;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by Administrator on 2017/9/5.
 */

public class CustomBehavior<T extends View> extends CoordinatorLayout.Behavior<T> {

    private int mFrameMaxHeight = 100;
    private int mStartY;

    private BounceInterpolator interpolator = new BounceInterpolator();
    public CustomBehavior() {
    }

    public CustomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    /**
     * 代表寻找被观察View
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof NestedScrollView;
    }

    /**
     * 被观察View变化的时候回调用的方法
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //记录开始的Y坐标  也就是toolbar起始Y坐标
        if(mStartY == 0) {
            mStartY = (int) dependency.getY();  // NestedScrollView 起始坐标
        }    //计算toolbar从开始移动到最后的百分比
        float percent = dependency.getY()/mStartY;    //改变child的坐标(从消失，到可见)
        child.setY( child.getHeight() - dependency.getHeight()*(1-percent));
        return true;

    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, T child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }
}
