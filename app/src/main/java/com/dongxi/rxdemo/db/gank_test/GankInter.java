package com.dongxi.rxdemo.db.gank_test;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/10/5.
 */

public interface GankInter {
    @GET("api/data/福利/10/1")
    Call<Gank> getResultBean() ;
}
