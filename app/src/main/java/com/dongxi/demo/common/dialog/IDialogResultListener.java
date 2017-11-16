package com.dongxi.demo.common.dialog;

/**
 * Created by Administrator on 2017/10/30.
 * 回掉接口
 */

public interface IDialogResultListener<T> {
    void onDataResult(T result);
}
