package com.dongxi.rxdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.annotations.NonNull;

public class MDOneActivity extends AppCompatActivity {

    private static final String TAG = "MDOneActivity";
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private List<String> mStringList = new ArrayList<>();
    private CardItemAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_test);
        ButterKnife.bind(this);

        initTextData();

        CustomToolbar customToolbar = (CustomToolbar) findViewById(R.id.custom_toolbar);
        customToolbar.setLeftButtonText("返回");
        customToolbar.setTitle("我是居中标题");
        customToolbar.setRightButtonIcon(R.drawable.add);
        customToolbar.setRightTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MDOneActivity.this, MDTwoActivity.class));
                Toast.makeText(MDOneActivity.this, "hello", Toast.LENGTH_SHORT).show();
            }
        });
        customToolbar.setLeftTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
                Snackbar.make(v, "退出", Snackbar.LENGTH_SHORT).setAction("确定退出", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }).show();
            }
        });

        mSwipeRefresh.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshText() ;
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mMyAdapter = new CardItemAdapter(this, mStringList);
        mRecyclerView.setAdapter(mMyAdapter);

        mMyAdapter.setCardClickListener(new CardItemAdapter.OnMyClickListener() {
            @Override
            public void cardClickListener(View view, final int position) {
                Snackbar.make(view, "hehe", Snackbar.LENGTH_SHORT).setAction("heheda", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MDOneActivity.this,MDTwoActivity.class) ;
                        intent.putExtra("name",mStringList.get(position)) ;
                        startActivity(intent);
                        Toast.makeText(MDOneActivity.this, "hehe,null point", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });


        // 用于学习RxJava2
        Flowable<Integer> observable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR);
        Subscriber<Integer> observer = new Subscriber<Integer>() {


            @Override
            public void onSubscribe(Subscription s) {
                Log.e(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext: ");
            }

            @Override
            public void onError(Throwable t) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: ");
            }
        };
        observable.subscribe(observer);
    }

    private void refreshText() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initTextData();
                        mMyAdapter.notifyDataSetChanged();
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initTextData() {
        for (int i = 0; i < 50; i++) {
            mStringList.add("card " + i);
        }
    }

    @OnClick(R.id.fab)
    public void onViewClicked(View view) {
        Snackbar.make(view, "undo", Snackbar.LENGTH_LONG).setAction("hello", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MDOneActivity.this, "hello snackbar", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
}
