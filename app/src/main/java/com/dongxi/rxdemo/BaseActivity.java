package com.dongxi.rxdemo;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    private PermissionListener mListener;
    private static final int PERMISSION_REQUESTCODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void requestRunPermission(String[] permissions, PermissionListener listener){
        mListener = listener ;
        List<String> permissionList = new ArrayList<>() ;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()){
            ActivityCompat.requestPermissions(this,permissionList.toArray(new String[permissionList.size()]),PERMISSION_REQUESTCODE);
        }else {
            // 全部授权
            mListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUESTCODE:
                if (grantResults.length > 0){
                    List<String> deniedPermissions = new ArrayList<>() ;
                    for (int i =0 ;i < grantResults.length; i++) {
                        int grantResult = grantResults[i] ;
                        String permission = permissions[i] ;
                        if (grantResult != PackageManager.PERMISSION_GRANTED){
                            deniedPermissions.add(permission) ;
                        }
                    }
                    if (deniedPermissions.isEmpty()){
                        // 全部授权
                        mListener.onGranted();
                    }else {
                        mListener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:

                break;
        }
    }


    /**
     * 已授权、未授权的接口回调
     */
    public interface PermissionListener {

        void onGranted();//已授权

        void onDenied(List<String> deniedPermission);//未授权

    }
}
