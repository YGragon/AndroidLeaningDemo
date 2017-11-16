package com.dongxi.demo.mvp;

import android.view.View;

/**
 * Created by macmini002 on 17/9/19.
 */

public interface BaseView {
    //    void setPresenter(P presenter);

    void showLoading(String msg);

    void hideLoading();

    void showError(String msg, View.OnClickListener onClickListener);

    void showEmpty(String msg, View.OnClickListener onClickListener);

    void showEmpty(String msg, View.OnClickListener onClickListener, int imageId);

    void showNetError(View.OnClickListener onClickListener);
}
