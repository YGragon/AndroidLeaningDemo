package com.dongxi.demo.utils;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * base64工具类
 *      图片 path 转 string
 *      bitmap 转 base64 码
 *      base64码 转 String
 * Created by Administrator on 2017/8/12.
 */

public class Base64Utils {
    public static String image2String(String imagePath) throws IOException {

        return Base64.encodeToString(imagePath.getBytes(),Base64.DEFAULT);
    }

    /**
     * bitmap转为base64
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                int size = bitmap.getByteCount(); // 字节数
                Log.e("bitmapToBase64", "size:" + size);
                int value = 100;
                if (size > 1363148) {
                    // 1.36M, base64后大概1.8M
                    float scale = 1572864.0f / size;
                    value = (int)(scale*100);
                    value = value > 100 ? 100 : (value < 40 ? 40 : value);
                    Log.e("bitmapToBase64", "resize scale:" + scale + ", value:" + value);
                }
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, value, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (result != null) {
            result = result.replaceAll("\r", "");
            result = result.replaceAll("\n", "");
        }
        return result;
    }

    //base64字符串转化成图片
    public static String string2Image(String base64String) {   //对字节数组字符串进行Base64解码并生成图片
        String decodedString =new String(Base64.decode(base64String,Base64.DEFAULT));
        Log.e("Base64", "Base64---->" + decodedString);
        return decodedString ;
    }
}
