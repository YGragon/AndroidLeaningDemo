package com.dongxi.rxdemo.guide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.dongxi.rxdemo.MainActivity;
import com.dongxi.rxdemo.R;
import com.dongxi.rxdemo.global.AppConstants;
import com.dongxi.rxdemo.utils.SpUtils;

/**
 * Created by Administrator on 2017/9/29.
 */

public class SplashActivity extends Activity {
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断是否是第一次开启应用
        boolean isFirstOpen = SpUtils.getBoolean(this, AppConstants.FIRST_OPEN);
        // 如果是第一次启动，则先进入功能引导页
        if (!isFirstOpen) {
            Log.e(TAG, "onCreate: welcome");
            Intent intent = new Intent(this, WelcomeGuideActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // 如果不是第一次启动app，则正常显示启动屏
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                enterHomeActivity();
            }
        }, 2000);
    }

    private void enterHomeActivity() {
        Log.e(TAG, "enterHomeActivity: mainactivity");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
