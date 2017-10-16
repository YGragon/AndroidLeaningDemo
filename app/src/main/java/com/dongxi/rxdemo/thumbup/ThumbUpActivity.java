package com.dongxi.rxdemo.thumbup;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.dongxi.rxdemo.R;

import java.util.ArrayList;

public class ThumbUpActivity extends AppCompatActivity {

    private static final String TAG = "ThumbUpActivity";

    private static final String ACTION_BROADCAST = "action_broadcast";

    private ArrayList<ThumbUp> mThumbUpList = new ArrayList<>() ;
    private ThumbUpAdapter mThumbUpAdapter;
    private IntentFilter mIntentFilter;
//    private MessageReceiver mMessageReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thumb_up);

//        mIntentFilter = new IntentFilter();
//        mIntentFilter.addAction(ACTION_BROADCAST);
//        mMessageReceiver = new MessageReceiver();
//        registerReceiver(mMessageReceiver,mIntentFilter) ;


        ListView listView = (ListView) findViewById(R.id.listView);
        mThumbUpAdapter = new ThumbUpAdapter(this,initData());
        listView.setAdapter(mThumbUpAdapter);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(mMessageReceiver);
//    }
    public ArrayList<ThumbUp> initData(){
        for (int i = 0; i < 20; i++){
            ThumbUp thumbUp = new ThumbUp();
            thumbUp.setThumbUpCount(i);
            mThumbUpList.add(thumbUp) ;
        }

//        Intent intent = new Intent();
//        intent.setAction("action_broadcast") ;
//        mContext.sendBroadcast(intent);
        Log.e(TAG, "initData: 发送");
        Log.e(TAG, "initData: 发送"+mThumbUpList.size());
        return mThumbUpList ;
    }

//    class MessageReceiver extends BroadcastReceiver{
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.e(TAG, "onReceive: 接受");
//            Log.e(TAG, "onReceive: 接受"+mThumbUpList.size());
//            mThumbUpAdapter.notifyDataSetChanged();
//        }
//    }
}
