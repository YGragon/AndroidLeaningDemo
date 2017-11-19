package com.dongxi.rxdemo.guide;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.dongxi.rxdemo.MainActivity;
import com.dongxi.rxdemo.R;

/**
 * Created by Administrator on 2017/9/29.
 */

public class SplashActivity extends Activity {
    private static final String TAG = "SplashActivity";
    //判断用户是否是第一次使用该应用
     private boolean isFirstUse = false;
    //延时时间，用于由欢迎界面进入另外的页面的延时效果
    private static final int TIME = 2*1000;
     private static final int TO_MAIN = 100001;
     private static final int TO_GUIDE = 100002;

    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TO_MAIN:
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            enterHomeActivity();
                        }
                    }, 2000);
                    break;
                case TO_GUIDE:
                    Intent intent = new Intent(SplashActivity.this, WelcomeGuideActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:

                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences perPreferences = getSharedPreferences("JohnTsai", MODE_PRIVATE);
        isFirstUse = perPreferences.getBoolean("isFirstUse", true);

        if (!isFirstUse) {
            myHandler.sendEmptyMessageDelayed(TO_MAIN, TIME);
        }else{
            myHandler.sendEmptyMessageDelayed(TO_GUIDE, TIME);
            SharedPreferences.Editor editor = perPreferences.edit();
            editor.putBoolean("isFirstUse", false);
            editor.commit();
        }
    }

    private void enterHomeActivity() {
        Log.e(TAG, "enterHomeActivity: mainactivity");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
