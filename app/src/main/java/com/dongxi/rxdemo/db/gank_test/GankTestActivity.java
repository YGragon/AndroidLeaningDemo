package com.dongxi.rxdemo.db.gank_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dongxi.rxdemo.R;
import com.dongxi.rxdemo.empety_view.EmptyLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    private ArrayList<ResultsBean> datas = new ArrayList<>();
    private GankAdapter mGankAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_test);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        emptyLayout.showLoading();
        getData();

    }

    private void getData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit build = builder.baseUrl("http://Gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GankInter gankinter = build.create(GankInter.class);
        gankinter.getResultBean().enqueue(new Callback<Gank>() {
            @Override
            public void onResponse(Call<Gank> call, Response<Gank> response) {

                response.body().save();
                if (response.body().getResults().size() == 0){
                    emptyLayout.showEmpty();
                }else {
                    emptyLayout.hide();
                }
                datas.addAll(response.body().getResults());
                mGankAdapter = new GankAdapter(GankTestActivity.this, R.layout.activity_gank_test_item, datas);
                mRecyclerView.setAdapter(mGankAdapter);
                mGankAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Gank> call, Throwable t) {
                emptyLayout.showError();

            }
        });


    }
}
