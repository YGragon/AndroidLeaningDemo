package com.dongxi.rxdemo.global;

import android.content.Context;
import android.os.Handler;

import com.dongxi.rxdemo.utils.CrashUtils;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/**
 * 代表当前应用程序
 * @author Aller_Dong
 *
 */
public class BaseApplication extends LitePalApplication {
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
		LitePal.initialize(this);

		
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
