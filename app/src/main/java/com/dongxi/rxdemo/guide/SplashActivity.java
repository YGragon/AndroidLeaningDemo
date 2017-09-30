package com.dongxi.rxdemo.guide;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.dongxi.rxdemo.BaseActivity;
import com.dongxi.rxdemo.MainActivity;
import com.dongxi.rxdemo.R;
import com.dongxi.rxdemo.global.AppConstants;
import com.dongxi.rxdemo.utils.SpUtils;

/**
 * Created by Administrator on 2017/9/29.
 */

public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Boolean isFirstOpen = SpUtils.getBoolean(this, AppConstants.FIRST_OPEN);

        // 不是第一次进入
        if (!isFirstOpen){
            // 直接进入欢迎页面
            Log.e(TAG, "onCreate: 进入引导页");
            Intent intent = new Intent(this, WelcomeGuideActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        Log.e(TAG, "onCreate: 进入欢迎页");
        // 第一次进入
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "onCreate: 进入主页");
                enterMainActivity() ;
            }
        },2000);

    }

    private void enterMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
