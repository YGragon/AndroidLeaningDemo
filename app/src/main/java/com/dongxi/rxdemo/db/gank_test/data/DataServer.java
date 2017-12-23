package com.dongxi.rxdemo.db.gank_test.data;

import android.util.Log;

import com.dongxi.rxdemo.db.gank_test.bean.Gank;
import com.dongxi.rxdemo.db.gank_test.http.GankAllService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        final List<Gank.GankItem> list = new ArrayList<>();
        Retrofit builder = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build() ;

        GankAllService gankAllService = builder.create(GankAllService.class);
        Call<Gank> gankItemCall = gankAllService.getGankAll("all", 10, 1);
        gankItemCall.enqueue(new Callback<Gank>() {
            @Override
            public void onResponse(Call<Gank> call, Response<Gank> response) {
                Log.e(TAG, "onResponse: 结果就是：----》"+response.body().toString());
                Log.e(TAG, "onResponse: 结果就是：----》"+response.body().getResults().size());
                for (int i = 0; i < response.body().getResults().size(); i++) {
                    Log.e(TAG, "onResponse: response.body().getResults().get(i).getType()======"+response.body().getResults().get(i).getType());
                    Log.e(TAG, "onResponse: response.body().getResults().get(i).url======"+ response.body().getResults().get(i).getUrl());
                    Log.e(TAG, "onResponse: response.body().getResults().get(i).desc======"+ response.body().getResults().get(i).getDesc());
                    if (response.body().getResults().get(i).getType().equals("福利")||response.body().getResults().get(i).getType().equals("休息视频")){
                        Gank.GankItem gankItem1 = new Gank.GankItem(
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                response.body().getResults().get(i).getUrl(),
                                false,
                                "",
                                Gank.GankItem.IMG, Gank.GankItem.IMG_SPAN_SIZE);
                        list.add(gankItem1);
                    }else {
                        Gank.GankItem gankItem = new Gank.GankItem(
                                "",
                                "",
                                "",
                                response.body().getResults().get(i).getDesc(),
                                "",
                                "",
                                "",
                                false,
                                "",
                                Gank.GankItem.TEXT,
                                Gank.GankItem.TEXT_SPAN_SIZE);
                        list.add(gankItem);


                    }
                }
                Log.e(TAG, "getMultipleItemData: size=="+list.size());
            }

            @Override
            public void onFailure(Call<Gank> call, Throwable t) {

            }
        });


        return list;
    }
}
