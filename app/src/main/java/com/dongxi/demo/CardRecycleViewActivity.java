package com.dongxi.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.dongxi.demo.widget.CustomToolbar;
import com.dongxi.demo.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardRecycleViewActivity extends AppCompatActivity{

    private static final String TAG = "CardRecycleViewActivity";
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.recycler_view)
    EmptyRecyclerView mRecyclerView;
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
                startActivity(new Intent(CardRecycleViewActivity.this, CollapsingToolbarActivity.class));
                Toast.makeText(CardRecycleViewActivity.this, "hello", Toast.LENGTH_SHORT).show();
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
        View emptyView = findViewById(R.id.empty_view) ;
        mRecyclerView.setEmptyView(emptyView);

        mMyAdapter.setCardClickListener(new CardItemAdapter.OnMyClickListener() {
            @Override
            public void cardClickListener(View view, final int position) {
                Snackbar.make(view, "hehe", Snackbar.LENGTH_SHORT).setAction("heheda", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CardRecycleViewActivity.this,CollapsingToolbarActivity.class) ;
                        intent.putExtra("name",mStringList.get(position)) ;
                        startActivity(intent);
                        Toast.makeText(CardRecycleViewActivity.this, "hehe,null point", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
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
                        mStringList.removeAll(mStringList) ;
//                        mStringList.remove(mStringList.size()) ;
//                        initTextData();
//                        mMyAdapter.notifyDataSetChanged();
                        mSwipeRefresh.setRefreshing(false);
                        mMyAdapter.notifyDataSetChanged();
                        Toast.makeText(CardRecycleViewActivity.this, "刷新成功哟", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    /**
     * 模拟数据延迟出现，显示空的界面
     */
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
                Toast.makeText(CardRecycleViewActivity.this, "hello snackbar", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
}
