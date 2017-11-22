package com.dongxi.rxdemo.event_bus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dongxi.rxdemo.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by macmini002 on 17/11/22.
 */

public class Event2 extends AppCompatActivity {

    private static final String TAG = "Event2";

    @BindView(R.id.event_bus2_tv)
    TextView eventBus2Tv;
    @BindView(R.id.bt_message)
    Button btMessage;
    @BindView(R.id.bt_subscription)
    Button btSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus2);
        ButterKnife.bind(this);

        btMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.msg = "你好" ;
                Log.e(TAG, "onClick: "+ messageEvent.msg);
                EventBus.getDefault().post("直接发布");
                finish();
            }
        });

        btSubscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.msg = "你好" ;
                Log.e(TAG, "onClick: "+ messageEvent.msg);
                EventBus.getDefault().postSticky("bbbbbbbb");
                finish();
            }
        });
    }
}
