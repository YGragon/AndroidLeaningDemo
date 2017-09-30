package com.dongxi.rxdemo.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.dongxi.rxdemo.utils.CrashUtils;

/**
 * 代表当前应用程序
 * @author Aller_Dong
 *
 */
public class BaseApplication extends Application {
	private static BaseApplication application;
	private static int mainTid;
	private static Handler handler;

	@Override
	public void onCreate() {
		super.onCreate();


		application=this;
		mainTid = android.os.Process.myTid();
		handler=new Handler();

		CrashUtils.init();

		
	}
	public static Context getApplication() {
		return application;
	}
	public static int getMainTid() {
		return mainTid;
	}
	public static Handler getHandler() {
		return handler;
	}
	
}
