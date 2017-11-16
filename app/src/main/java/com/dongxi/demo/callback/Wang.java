package com.dongxi.demo.callback;

import android.util.Log;

import com.dongxi.demo.utils.ToastUtil;

/**
 * Created by Administrator on 2017/10/30.
 */

public class Wang implements CallBack {

    private static final String TAG = "Wang";

    private Li mLi ;

    public Wang(Li li){
        this.mLi = li ;
    }

    public void askQuestion(final String question){
//这里用一个线程就是异步，
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * 小王调用小李中的方法，在这里注册回调接口
                 * 这就相当于A类调用B的方法C
                 */
                mLi.solveMessage(Wang.this, question);
            }
        }).start();

        //小网问完问题挂掉电话就去干其他的事情了，诳街去了
        play();
    }

    private void play(){
        Log.e(TAG, "play: play");
    }
    @Override
    public void solve(String result) {
        Log.e(TAG, "solve: answer="+result);
        ToastUtil.showLongToast(result);
    }
}
