package com.dongxi.rxdemo.db.gank_test;

import com.dongxi.rxdemo.global.BaseApplication;

import java.util.List;

/**
 * Created by macmini002 on 17/12/6.
 */

public class GankDao {
    /**
     * 添加数据
     *
     * @param resultsEntity
     */
    public static void insertGank(ResultsEntity resultsEntity) {
        BaseApplication.getDaoInstant().getResultsEntityDao().insert(resultsEntity);
    }

    /**
     * 更新数据
     *
     * @param ResultsEntity
     */
    public static void updateLove(ResultsEntity ResultsEntity) {
        BaseApplication.getDaoInstant().getResultsEntityDao().update(ResultsEntity);
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public static List<ResultsEntity> queryAll() {
        return BaseApplication.getDaoInstant().getResultsEntityDao().loadAll();
    }
}
