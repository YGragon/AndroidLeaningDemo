package com.dongxi.rxdemo.event_bus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dongxi.rxdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by macmini002 on 17/11/22.
 */

public class Event1 extends AppCompatActivity {

    private static final String TAG = "Event1";

    @BindView(R.id.event_bus1_tv)
    TextView eventBus1Tv;
    @BindView(R.id.bt_message)
    Button btMessage;
    @BindView(R.id.bt_subscription)
    Button btSubscription;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus1);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        btMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Event1.this,Event2.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        Log.e(TAG, "onStart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(String msg){
        eventBus1Tv.setText(msg);
        Log.e(TAG, "onMessageEvent: " );
    }

    @Subscribe(sticky = true)
    public void onEventBus(String msg){
        eventBus1Tv.setText(msg);
        Log.e(TAG, "onMessageEvent: " );
    }

}
