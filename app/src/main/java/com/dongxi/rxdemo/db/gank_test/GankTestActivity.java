package com.dongxi.rxdemo.db.gank_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dongxi.rxdemo.R;

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

    private ArrayList<ResultsBean> datas = new ArrayList<>() ;
    private GankAdapter mGankAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_test);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) ;
        mRecyclerView.setLayoutManager(linearLayoutManager);


//        List<Gank> gankList = DataSupport.findAll(Gank.class);
//        List<ResultsBean> resultsBeanList = DataSupport.findAll(ResultsBean.class);
//
//        if (gankList.size() != 0){
//            Toast.makeText(this, "Nihao size="+resultsBeanList.size(), Toast.LENGTH_SHORT).show();
//            mGankAdapter = new GankAdapter(this, R.layout.activity_gank_test_item, gankList.get(0).getResults());
////            mGankAdapter = new GankAdapter(this, R.layout.activity_gank_test_item, mResultsBeanList);
//            mRecyclerView.setAdapter(mGankAdapter);
//            mGankAdapter.notifyDataSetChanged();
//        }else {
            getData() ;
//        }
//        mGankAdapter= new GankAdapter(GankTestActivity.this, R.layout.activity_gank_test_item, datas);
//        mRecyclerView.setAdapter(mGankAdapter);
//        mGankAdapter.notifyDataSetChanged();
    }

    private void getData() {
        Retrofit.Builder builder = new Retrofit.Builder() ;
        Retrofit build = builder.baseUrl("http://Gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GankInter gankinter = build.create(GankInter.class);
        gankinter.getResultBean().enqueue(new Callback<Gank>() {
            @Override
            public void onResponse(Call<Gank> call, Response<Gank> response) {
                Toast.makeText(GankTestActivity.this, "成功=="+response.body().getResults().size(), Toast.LENGTH_SHORT).show();
                Toast.makeText(GankTestActivity.this, "成功=s="+response.body().toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(GankTestActivity.this, "url=="+call.request().url(), Toast.LENGTH_SHORT).show();

                response.body().save() ;
                datas.addAll(response.body().getResults()) ;
                mGankAdapter= new GankAdapter(GankTestActivity.this, R.layout.activity_gank_test_item, datas);
                mRecyclerView.setAdapter(mGankAdapter);
                mGankAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Gank> call, Throwable t) {
                Toast.makeText(GankTestActivity.this, "失败咯", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
