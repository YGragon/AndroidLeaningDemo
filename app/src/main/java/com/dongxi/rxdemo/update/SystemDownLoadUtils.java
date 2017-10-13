package com.dongxi.rxdemo.update;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Administrator on 2017/9/12.
 */

public class SystemDownLoadUtils {
    private static final String TAG = "SystemDownLoadUtils";
    private static DownloadManager mDownloadManager;
    private Context mContext;
    private static long downloadId;
    private static String apkName;

    /**
     * 单例
     */
    private static SystemDownLoadUtils instance = null ;
    public static SystemDownLoadUtils getInstance(){
        if (instance == null) {
            instance = new SystemDownLoadUtils() ;
        }
        return instance ;
    }

    public void download(final Context mContext, String url, String name) {
        final String packageName = "com.android.providers.downloads";
        int state = mContext.getPackageManager().getApplicationEnabledSetting(packageName);
        //检测下载管理器是否被禁用
        if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext).setTitle("温馨提示").setMessage
                    ("系统下载管理器被禁止，需手动打开").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    try {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + packageName));
                        mContext.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                        mContext.startActivity(intent);
                    }
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        } else {
            //正常下载流程
            apkName = name;
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            Log.e(TAG, "download: url=="+Uri.parse(url));
            request.setAllowedOverRoaming(false);

            //通知栏显示
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setTitle("我的民大");
            request.setDescription("正在下载中...");
            request.setVisibleInDownloadsUi(true);

            //设置下载的路径
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, apkName+".apk"); // 后缀名要以.apk结尾

            //获取DownloadManager
            mDownloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
            downloadId = mDownloadManager.enqueue(request);

            // 这段代码在 不用添加，因为再manifest文件中静态注册过了，可以再DataCenter中直接发
//            mContext.registerReceiver(mReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
            // 再返回的 event 中 调用 checkStatus()
        }
    }


    /**
     * 检查下载状态
     */
    public void checkStatus(Context mContext, long downloadId) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(downloadId);
        Cursor cursor = mDownloadManager.query(query);
        if (cursor.moveToFirst()) {
            int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
            String loadUrl = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)); // 下载链接

            switch (status) {
                //下载暂停
                case DownloadManager.STATUS_PAUSED:
                    // 提示用户下载暂停
                    break;
                //下载延迟
                case DownloadManager.STATUS_PENDING:
                    // 过了多少秒，提示用户网络不好
                    break;
                //正在下载
                case DownloadManager.STATUS_RUNNING:
                    // 可以弹出下载进度框
                    break;
                //下载完成
                case DownloadManager.STATUS_SUCCESSFUL:
//                    installAPK(mContext);
                    Log.e(TAG, "checkStatus: 完成啦");
                    install(mContext,downloadId);
                    break;
                //下载失败
                case DownloadManager.STATUS_FAILED:
                    Toast.makeText(mContext, "下载失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        cursor.close();
    }

    /**
     * 7.0兼容
     */
    private void installAPK(Context mContext) {
        File apkFile =
                new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), apkName);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Uri apkUri = FileProvider.getUriForFile(mContext, mContext.getPackageName() + ".provider", apkFile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        mContext.startActivity(intent);
    }

    /**
     * 安装
     * @param mContext      上下文
     * @param downloadId    下载的ID
     */
    private void install(Context mContext, long downloadId) {
        DownloadManager systemService = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uriForDownloadedFile = systemService.getUriForDownloadedFile(downloadId);
        if (uriForDownloadedFile != null){
            Intent intent = new Intent(Intent.ACTION_VIEW) ;
            intent.setDataAndType(uriForDownloadedFile, "application/vnd.android.package-archive") ;
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
            mContext.startActivity(intent);
        }else {
            Toast.makeText(mContext, "下载失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 取消一个下载任务
     *      可以实现取消多个下载任务
     * @param id
     */
    public void cancelDownloading(long id){
        mDownloadManager.remove(id) ;
    }
}
