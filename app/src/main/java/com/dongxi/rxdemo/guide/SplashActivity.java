package com.dongxi.rxdemo.guide;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dongxi.rxdemo.MainActivity;
import com.dongxi.rxdemo.R;
import com.dongxi.rxdemo.global.AppConstants;
import com.dongxi.rxdemo.utils.SpUtils;

/**
 * Created by Administrator on 2017/9/29.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Boolean isFirstOpen = SpUtils.getBoolean(this, AppConstants.FIRST_OPEN);

        // 不是第一次进入
        if (!isFirstOpen){
            // 直接进入欢迎页面
            Intent intent = new Intent(this, WelcomeGuideActivity.class);
            startActivity(intent);
            finish();
        }
        // 第一次进入
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
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
