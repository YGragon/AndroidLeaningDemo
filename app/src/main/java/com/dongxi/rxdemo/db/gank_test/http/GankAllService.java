package com.dongxi.rxdemo.db.gank_test.http;

import com.dongxi.rxdemo.db.gank_test.bean.Gank;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 干货--所有
 */

public interface GankAllService {
    @GET("api/data/{type}/{pages}/{page}")
    Call<Gank> getGankAll(@Path("type") String type, @Path("pages") int pages, @Path("page") int page);
}
