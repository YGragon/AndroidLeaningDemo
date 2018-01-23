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
        for (int i = 0; i <= 4; i++) {
            Gank.GankItem gankItem = new Gank.GankItem(
                    "",
                    "",
                    "",
                    "desc"+i,
                    "",
                    "",
                    "",
                    false,
                    "",
                    Gank.GankItem.TEXT,
                    Gank.GankItem.TEXT_SPAN_SIZE);
            gankItem.setDesc("desc=="+i);
            list.add(gankItem);

            Gank.GankItem gankItem1 = new Gank.GankItem(
                    "",
                    "",
                    "",
                    "desc"+i,
                    "",
                    "",
                    "https://avatars1.githubusercontent.com/u/7698209?s=400&v=4",
                    false,
                    "",
                    Gank.GankItem.IMG, Gank.GankItem.IMG_SPAN_SIZE);
            list.add(gankItem1);
        }
        Log.e(TAG, "getMultipleItemData: size=="+list.size());
        return list;
    }

}
