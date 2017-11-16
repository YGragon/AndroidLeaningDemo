package com.dongxi.demo.utils;

import android.widget.Toast;

import com.dongxi.demo.global.BaseApplication;

/**
 * Created by Administrator on 2017/10/30.
 */

public class ToastUtil {

    public static void showShortToast(String message){
        Toast.makeText(BaseApplication.getApplication(), message, Toast.LENGTH_SHORT).show();
    }
    public static void showLongToast(String message){
        Toast.makeText(BaseApplication.getApplication(), message, Toast.LENGTH_LONG).show();
    }
}
