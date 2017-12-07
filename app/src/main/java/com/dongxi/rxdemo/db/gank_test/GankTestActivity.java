package com.dongxi.rxdemo.db.gank_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dongxi.rxdemo.R;
import com.dongxi.rxdemo.empety_view.EmptyLayout;
import com.dongxi.rxdemo.utils.NetUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GankTestActivity extends AppCompatActivity {

    private static final String TAG = "GankTestActivity";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.emptyLayout)
    EmptyLayout emptyLayout;

    private List<ResultsEntity> datas = new ArrayList<>();
    private GankAdapter mGankAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_test);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mGankAdapter = new GankAdapter(GankTestActivity.this, R.layout.activity_gank_test_item, datas);
        mRecyclerView.setAdapter(mGankAdapter);
        emptyLayout.showLoading();

        getData();


    }

    private void getData() {
        datas.clear();
        if (!NetUtil.isConnected(this)){
            Log.e(TAG, "getData: 断网");
            datas = GankDao.queryAll();
            Log.e(TAG, "getData: list=="+datas.size());

            mGankAdapter.notifyDataSetChanged();
        }else {
            Log.e(TAG, "getData: 有网");
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://Gank.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            GankInter gankinter = retrofit.create(GankInter.class);
            gankinter.getResultBean().enqueue(new Callback<ResultsBean>() {
                @Override
                public void onResponse(Call<ResultsBean> call, Response<ResultsBean> response) {
                    Log.e(TAG, "getData: 断网111");
                    datas.addAll(response.body().getResults());
                    for (int i = 0; i < datas.size(); i++){
                        ResultsEntity resultsEntity  = datas.get(i) ;
                        GankDao.insertGank(resultsEntity);
                        Log.e(TAG, "getData: 断网1111111111111111");
                    }

                    mGankAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ResultsBean> call, Throwable t) {
                    emptyLayout.showError();

                }
            });
        }



    }
}
