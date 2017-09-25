package com.dongxi.rxdemo.mvp;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * Created by macmini002 on 17/9/19.
 */

public interface BaseContract {
    interface Presenter extends BasePresenter{
        //获取数据
        void getDrawerList();
        //头像点击
        void onDrawerIconClicked();
        //获取fragment
        void getSelectFragment(int position);
    }

    interface View extends BaseView{
        //获取数据后，更新界面
        void onDrawerListGet(ArrayList list);
        //设置头像
        void setDrawerIcon(int resId);
        //fragment返回后
        void onSelectFragmentGet(Fragment fragment);

    }
    //数据监听
    interface onGetDrawerListListener {
        void onSuccess();
        void onError();
    }
}
