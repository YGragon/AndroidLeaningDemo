package com.dongxi.demo.utils;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.dongxi.demo.global.BaseApplication;
import com.dongxi.demo.global.ShowCrashActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class CrashUtils {

    private static boolean mInitialized;
    private static String  defaultDir;
    private static String  dir;
    private static String  versionName;
    private static int     versionCode;

    private static final String FILE_SEP = System.getProperty("file.separator");
    private static final Format FORMAT   = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.getDefault());

    private static final String CRASH_HEAD;

    private static final UncaughtExceptionHandler DEFAULT_UNCAUGHT_EXCEPTION_HANDLER;
    private static final UncaughtExceptionHandler UNCAUGHT_EXCEPTION_HANDLER;

    static {
        try {
            PackageInfo pi = BaseApplication.getApplication().getPackageManager().getPackageInfo(BaseApplication.getApplication().getPackageName(), 0);
            if (pi != null) {
                versionName = pi.versionName;
                versionCode = pi.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        CRASH_HEAD = "\n************* 哦no  崩溃了****************" +
                "\nDevice Manufacturer: " + Build.MANUFACTURER +// 设备厂商
                "\nDevice Model       : " + Build.MODEL +// 设备型号
                "\nAndroid Version    : " + Build.VERSION.RELEASE +// 系统版本
                "\nAndroid SDK        : " + Build.VERSION.SDK_INT +// SDK版本
                "\nCPU ABI            : " + Build.CPU_ABI +
                "\nApp VersionName    : " + versionName +
                "\nApp VersionCode    : " + versionCode +
                "\n************* Crash Cause ****************\n\n";

        DEFAULT_UNCAUGHT_EXCEPTION_HANDLER = Thread.getDefaultUncaughtExceptionHandler();


        UNCAUGHT_EXCEPTION_HANDLER = new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                if (e == null) {
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(0);
                    return;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (flag[0]){

                        }
                    }
                }).start();
                showCrashActivity(e);

                uploadExceptionToServer(e);

                if (writeToExternalStorage(e)) return;
                if (DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                    DEFAULT_UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(t, e);
                }
            }
        };
    }

    private static void showCrashActivity(Throwable e) {
        Intent i = new Intent(BaseApplication.getApplication(),ShowCrashActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("crash",CRASH_HEAD+e+"\n\n应用发生崩溃，对您造成使用上的不便，深感抱歉！后台工程师正在加紧时间处理。");
        BaseApplication.getApplication().startActivity(i);
    }

    public static final boolean[] flag = {true};

    private static void uploadExceptionToServer(Throwable e) {
    }

    private static boolean writeToExternalStorage(final Throwable e) {
        Date now = new Date(System.currentTimeMillis());
        String fileName = FORMAT.format(now) + ".txt";
        final String fullPath = (dir == null ? defaultDir : dir) + fileName;
        if (!createOrExistsFile(fullPath)) return true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                PrintWriter pw = null;
                try {
                    pw = new PrintWriter(new FileWriter(fullPath, false));
                    pw.write(CRASH_HEAD);
                    e.printStackTrace(pw);
                    Throwable cause = e.getCause();
                    while (cause != null) {
                        cause.printStackTrace(pw);
                        cause = cause.getCause();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (pw != null) {
                        pw.close();
                    }
                }
            }
        }).start();
        return false;
    }

    private CrashUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>}</p>
     *
     * @return {@code true}: 初始化成功<br>{@code false}: 初始化失败
     */
    public static boolean init() {
        return init("");
    }

    /**
     * 初始化
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>}</p>
     *
     * @param crashDir 崩溃文件存储目录
     * @return {@code true}: 初始化成功<br>{@code false}: 初始化失败
     */
    public static boolean init(@NonNull final File crashDir) {
        return init(crashDir.getAbsolutePath() + FILE_SEP);
    }

    /**
     * 初始化
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>}</p>
     *
     * @param crashDir 崩溃文件存储目录
     * @return {@code true}: 初始化成功<br>{@code false}: 初始化失败
     */
    public static boolean init(final String crashDir) {
        if (isSpace(crashDir)) {
            dir = null;
        } else {
            dir = crashDir.endsWith(FILE_SEP) ? dir : dir + FILE_SEP;
        }
        if (mInitialized) return true;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                && BaseApplication.getApplication().getExternalCacheDir() != null)
            defaultDir = BaseApplication.getApplication().getExternalCacheDir() + FILE_SEP + "crash" + FILE_SEP;
        else {
            defaultDir = BaseApplication.getApplication().getCacheDir() + FILE_SEP + "crash" + FILE_SEP;
        }
        Thread.setDefaultUncaughtExceptionHandler(UNCAUGHT_EXCEPTION_HANDLER);
        return mInitialized = true;
    }

    private static boolean createOrExistsFile(final String filePath) {
        File file = new File(filePath);
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createOrExistsDir(final File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
