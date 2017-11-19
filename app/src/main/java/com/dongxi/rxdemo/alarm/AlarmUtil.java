package com.dongxi.rxdemo.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/11/15.
 */

public class AlarmUtil {
    public static void setAlarmTime(Context context, long timeInMillis, String action, long interval) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(action);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent,PendingIntent.FLAG_CANCEL_CURRENT);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //参数2是开始时间、参数3是下一次调用的时间
//            am.setWindow(AlarmManager.RTC, timeInMillis, interval, sender); // 因为setWindow只执行一次，所以要重新定义闹钟实现循环
//        } else {
        am.setRepeating(AlarmManager.RTC, timeInMillis, interval, sender);
//        }
    }

    public static void canalAlarm(Context context, String action) {
        Intent intent = new Intent(action);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pi);
    }

}
