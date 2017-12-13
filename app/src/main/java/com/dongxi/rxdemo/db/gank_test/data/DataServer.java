package com.dongxi.rxdemo.db.gank_test.data;

import android.util.Log;

import com.dongxi.rxdemo.db.gank_test.bean.Gank;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据中心
 */

public class DataServer {
    private static final String TAG = "DataServer";

    private static DataServer mDataServer = null;
    private DataServer(){}
    public static DataServer getInstance() {
        synchronized (DataServer.class) {
            if (mDataServer == null) {
                mDataServer = new DataServer();
            }
        }
        return mDataServer;
    }


    public List<Gank.GankItem> getMultipleItemData() {
        List<Gank.GankItem> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Gank.GankItem gankItem = new Gank.GankItem(Gank.GankItem.TEXT);
            gankItem.setDesc("111");
            list.add(gankItem);
            list.add(gankItem);
        }
        for (int i = 0; i < 5; i++) {
            Gank.GankItem gankItem = new Gank.GankItem(Gank.GankItem.IMG);
            gankItem.setDesc("222");
            list.add(gankItem);
            list.add(gankItem);
        }
        for (int i = 0; i < 5; i++) {
            Gank.GankItem gankItem = new Gank.GankItem(Gank.GankItem.VIDEO);
            gankItem.setDesc("333");
            list.add(gankItem);
        }
        Log.e(TAG, "getMultipleItemData: size=="+list.size());
        return list;
    }
}
