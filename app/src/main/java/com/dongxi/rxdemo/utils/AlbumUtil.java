package com.dongxi.rxdemo.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;

import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 选择相册的工具类
 *      支持4.4以下的相册选择
 * Created by fengkai on 17/8/14.
 */

public class AlbumUtil {
    private static final String TAG="AlbumUtil";
    public static final int REQUET_CODE_CROP = 2017;

    /**
     * 启动裁剪页面。在调用activity里重载
     * protected void onActivityResult(int requestCode, int resultCode, Intent data)方法，
     * 并读取requestCode == REQUET_CODE_CROP的回调。
     * @param activity
     * @param bitmap
     * @return
     */
    public static void cropImage(Activity activity, Bitmap bitmap) {
        try {
            // 在/SD/Android/data/com.meiqu.pianxin/cache下创建临时图片
            AlbumUtil.deleteAllFiles(activity.getExternalCacheDir());
            String sourceFilePath = activity.getExternalCacheDir() + "/tmpCropSource.jpg";
            FileOutputStream fos = new FileOutputStream(sourceFilePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            File sourceFile = new File(sourceFilePath);
            Uri uri = Uri.fromFile(sourceFile);

            File destFile = File.createTempFile("tmpCropDest", ".jpg", activity.getExternalCacheDir());
            Uri destinationUri = Uri.fromFile(destFile);

            UCrop.Options options = new UCrop.Options();

            options.setHideBottomControls(true); // 隐藏底部的控制栏
//        options.setCircleDimmedLayer(true); // 裁剪圆形
            //设置裁剪图片可操作的手势
            options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
//        Log.w(TAG,"result uri == "+uri) ;

            //第三方实现
            UCrop.of(uri, destinationUri)
                    .withAspectRatio(1,1)
                    .withMaxResultSize(400, 400)
                    .withOptions(options)
                    .start(activity, REQUET_CODE_CROP);
        }catch (Exception e) {
        }

    }


    /**
     * 传入系统相册返回的data Intent，返回选中图片的bitmap.
     * @param activity 调用的activity
     * @param data
     * @return
     */
    public static Bitmap getAlbum(Activity activity, Intent data) {

        if (data != null){
            try {
                Uri uri = data.getData();
                if (uri == null) {
                    return null;
                }
                String filePath = getRealPathFromURI(activity, data.getData());
                FileInputStream fis = new FileInputStream(filePath); // not found exception.
                // 先读取图片的宽高，不真正的加载图片。
                // 防止图片过大导致的内容问题。
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true; // 仅读取图片宽高等信息
                BitmapFactory.decodeFileDescriptor(fis.getFD(), null, options);
                float srcWidth = options.outWidth;
                float srcHeight = options.outHeight;
                int inSampleSize = 1;

                Resources resources = activity.getResources();
                DisplayMetrics dm = resources.getDisplayMetrics();
                float density = dm.density;
                int width = dm.widthPixels;
                int height = dm.heightPixels;

                if (srcHeight > height || srcWidth > width) {
                    if (srcWidth > srcHeight) {
                        inSampleSize = Math.round(srcHeight / height);
                    } else {
                        inSampleSize = Math.round(srcWidth / width);
                    }
                }
                options.inJustDecodeBounds = false;
                options.inSampleSize = inSampleSize;
                return BitmapFactory.decodeFileDescriptor(fis.getFD(), null, options);
            }catch (Exception ex) {
                // 找不到文件
                Log.e(TAG, "AlbumUtil.getAlbum:找不到文件");
                return null;
            }
        }
        return null;
    }

    public static void deleteAllFiles(File root) {
        File files[] = root.listFiles();
        if (files != null)
            for (File f : files) {
                if (f.isDirectory()) { // 判断是否为文件夹
                    deleteAllFiles(f);
                    try {
                        f.delete();
                    } catch (Exception e) {
                    }
                } else {
                    if (f.exists()) { // 判断是否存在
//                        deleteAllFiles(f);
                        try {
                            f.delete();
                        } catch (Exception e) {
                        }
                    }
                }
            }
    }

    public static Bitmap getBitmapFromUri(Activity activity ,Uri uri) throws IOException {
        if (uri == null) {
            return null;
        }
        String filePath = getRealPathFromURI(activity, uri);
        Log.e(TAG, "filePath " + filePath);
        FileInputStream fis = new FileInputStream(filePath); // not found exception.
        // 先读取图片的宽高，不真正的加载图片。
        // 防止图片过大导致的内容问题。
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 仅读取图片宽高等信息
        options.inDither = true;
//        BitmapFactory.decodeFileDescriptor(fis.getFD(), null, options);
        BitmapFactory.decodeFile(filePath, options);
        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
        int inSampleSize = 1;
        Log.e(TAG, "图片width:" + srcWidth + ", height:" + srcHeight);

        Resources resources = activity.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        if (srcHeight > height || srcWidth > width) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.round(srcHeight / height);
            } else {
                inSampleSize = Math.round(srcWidth / width);
            }
        }
        Log.e(TAG, "处理之后的inSampleSize:" + inSampleSize);
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
//        return BitmapFactory.decodeFileDescriptor(fis.getFD(), null, options);
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static String getAlbumStringPath(Activity activity, Intent data){
        if (data != null){
            String filePath = getRealPathFromURI(activity, data.getData());
            return filePath;
        }
        return null;
    }

    private static String getRealPathFromURI(Activity activity, Uri uri) {

        int sdkVersion = Build.VERSION.SDK_INT;
        if (sdkVersion >= 19) { // api >= 19
            return getRealPathFromUriAboveApi19(activity, uri);
        } else { // api < 19
            return getRealPathFromUriBelowAPI19(activity, uri);
        }
    }

    private static String getRealPathFromUriBelowAPI19(Activity activity, Uri uri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = activity.getContentResolver().query(uri, proj, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(proj[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }

    /**
     * 适配api19及以上,根据uri获取图片的绝对路径
     *
     * @param activity 上下文对象
     * @param uri     图片的Uri
     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
     */
    @SuppressLint("NewApi")
    private static String getRealPathFromUriAboveApi19(Activity activity, Uri uri) {
        String filePath = null;
        if (DocumentsContract.isDocumentUri(activity, uri)) {
            // 如果是document类型的 uri, 则通过document id来进行处理
            String documentId = DocumentsContract.getDocumentId(uri);
            if (isMediaDocument(uri)) { // MediaProvider
                // 使用':'分割
                String id = documentId.split(":")[1];

                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = {id};
                filePath = getDataColumn(activity, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, selectionArgs);
            } else if (isDownloadsDocument(uri)) { // DownloadsProvider
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                filePath = getDataColumn(activity, contentUri, null, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())){
            // 如果是 content 类型的 Uri
            filePath = getDataColumn(activity, uri, null, null);
        } else if ("file".equals(uri.getScheme())) {
            // 如果是 file 类型的 Uri,直接获取图片对应的路径
            filePath = uri.getPath();
        }
        Log.e(TAG,"real path = "+filePath) ;
        return filePath;
    }

    /**
     * 获取数据库表中的 _data 列，即返回Uri对应的文件路径
     * @return
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        String path = null;

        String[] projection = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
                path = cursor.getString(columnIndex);
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return path;
    }

    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is MediaProvider
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is DownloadsProvider
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
}
