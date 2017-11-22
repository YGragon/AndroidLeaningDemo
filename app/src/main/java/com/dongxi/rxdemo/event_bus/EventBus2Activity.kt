package com.dongxi.rxdemo.event_bus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.dongxi.rxdemo.R
import kotlinx.android.synthetic.main.activity_event_bus2.*
import org.greenrobot.eventbus.EventBus

class EventBus2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus2)

        val txt:String? = "EventBus2"
        event_bus2_tv.setText(txt)

        bt_message.setText("发送事件")
        bt_subscription.setText("订阅事件")

        bt_message.setOnClickListener{
            val eventMessage = EventBusMessages()
            eventMessage.message = "你好"
            val msg = eventMessage.message
            Log.e("kotlin=",msg)
            /**
             * 注意传递的参数
             *      如果传递的是对象，这里也要传入对象
             *      如果传递的是字符串，这里要传入String
             *      以此类推...
             */
            // EventBus.getDefault().post(msg)    // 传递的是字符串
            EventBus.getDefault().postSticky(eventMessage)    // 传递的是对象
            finish()
        }
    }
}
