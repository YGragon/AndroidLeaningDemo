package com.dongxi.rxdemo.global;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;

import com.dongxi.rxdemo.db.gank_test.DaoMaster;
import com.dongxi.rxdemo.db.gank_test.DaoSession;
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
	private static DaoSession daoSession;
	@Override
	public void onCreate() {
		super.onCreate();


		application=this;
		mainTid = android.os.Process.myTid();
		handler=new Handler();

		CrashUtils.init();

		//配置数据库
		setupDatabase();
		
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

	/**
	 * 配置数据库
	 */
	private void setupDatabase() {
		DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "demo.db", null);
		SQLiteDatabase db = helper.getWritableDatabase();
		DaoMaster daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();
	}

	public static DaoSession getDaoInstant() {
		return daoSession;
	}
}
