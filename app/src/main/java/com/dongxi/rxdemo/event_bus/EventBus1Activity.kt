package com.dongxi.rxdemo.event_bus

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.dongxi.rxdemo.R
import kotlinx.android.synthetic.main.activity_event_bus1.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class EventBus1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus1)

        /**
         * 注册事件 --------- 切记放在onCreate（）方法中
         * 放在 onStart() 中不起作用
         * */
        EventBus.getDefault().register(this)
        Log.e("kotlin==注册啦","")

        val txt:String? = "EventBus1"
        event_bus1_tv.setText(txt)

        bt_message.setText("发送消息")
        bt_subscription.setText("订阅消息")

        bt_message.setOnClickListener{
            startActivity(Intent(this, EventBus2Activity::class.java))
        }

        // 粘性事件---订阅
        bt_subscription.setOnClickListener{
            //注册事件
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this)
            } else {
                Toast.makeText(this, "请勿重复注册事件", Toast.LENGTH_SHORT).show()
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onEvent(msg: String) {
    fun onEvent(messageEvent: EventBusMessages) {
        /**
         * 注意传递的参数
         *      如果传递的是对象，这里也要传入对象
         *      如果传递的是字符串，这里要传入String
         *      以此类推...
         */
        event_bus1_tv.setText(messageEvent.message)
    }
}
