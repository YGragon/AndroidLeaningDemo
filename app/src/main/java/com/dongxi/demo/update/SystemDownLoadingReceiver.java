package com.dongxi.demo.update;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/9/12.
 */

public class SystemDownLoadingReceiver extends BroadcastReceiver {
    private OnSystemDownLoadingListener mListener;
    private int code;

    public void setListener(OnSystemDownLoadingListener listener, int code) {
        this.mListener = listener;
        this.code = code;
    }

    public interface OnSystemDownLoadingListener{
        // 应用于没有数据传送的情况。
        void OnEventOccur(int receiverId, Intent intent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (this.mListener != null) {
            this.mListener.OnEventOccur(this.code, intent);
        }
    }
}
