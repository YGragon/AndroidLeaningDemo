package com.dongxi.rxdemo.update;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/11.
 */

public class UpdateApkInfo implements Serializable{
    // 升级的数据结构
    // 请求接口
    // 下载apk我们分别使用DownloadManager和普通的httpurlconnection
    // 通过BroadcastReceiver来监听是否下载完成

    private String apkVersion;     //apk版本号
    private String apkName;      //apk名字
    private String apkDownloadUrl;  //下载地址
    private int aplVerCode;    //apk升级标示

    public UpdateApkInfo(String apkVersion, String apkName, String apkDownloadUrl, int aplVerCode) {
        this.apkVersion = apkVersion;
        this.apkName = apkName;
        this.apkDownloadUrl = apkDownloadUrl;
        this.aplVerCode = aplVerCode;
    }

    public String getApkVersion() {
        return apkVersion;
    }

    public void setApkVersion(String apkVersion) {
        this.apkVersion = apkVersion;
    }

    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
    }

    public String getApkDownloadUrl() {
        return apkDownloadUrl;
    }

    public void setApkDownloadUrl(String apkDownloadUrl) {
        this.apkDownloadUrl = apkDownloadUrl;
    }

    public int getAplVerCode() {
        return aplVerCode;
    }

    public void setAplVerCode(int aplVerCode) {
        this.aplVerCode = aplVerCode;
    }
}
