package com.dongxi.rxdemo.callback;

import android.util.Log;

/**
 * Created by Administrator on 2017/10/30.
 */

public class Li {
    private static final String TAG = "Li";
    public void solveMessage(CallBack callBack,String question){
        Log.e(TAG, "solveMessage: wang question="+question);

        //模拟小李办自己的事情需要很长时间
        for(int i=0; i<10000;i++){

        }

        /**
         * 小李办完自己的事情之后想到了答案是2
         */
        String result = "答案是2";

        /**
         * 于是就打电话告诉小王，调用小王中的方法
         * 这就相当于B类反过来调用A的方法D
         */
        callBack.solve(result);
    }
}
