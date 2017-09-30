package com.dongxi.rxdemo.global;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.dongxi.rxdemo.R;
import com.dongxi.rxdemo.utils.CrashUtils;


public class ShowCrashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_crash);
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(getIntent().getStringExtra("crash"));
        findViewById(R.id.exit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrashUtils.flag[0] = false;
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });
    }
}
