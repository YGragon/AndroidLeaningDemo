package com.dongxi.rxdemo.mulit_layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dongxi.rxdemo.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 多布局的数据的设置测试
 */
public class MulitLayoutActivity extends AppCompatActivity {

    private static final String TAG = "MulitLayoutActivity";

    @BindView(R.id.mulit_layout_recyclerView)
    RecyclerView mMulitLayoutRecyclerView;
    private ArrayList<String> mList = new ArrayList<>();
    private MulitSlefAdapter mulitAdapter;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulit_layout);
        ButterKnife.bind(this);

        intiData() ;

        // 通过
        linearLayoutManager = new LinearLayoutManager(this);
        // 通过
//        gridLayoutManager = new GridLayoutManager(this, 3);
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);     // 不支持
        mMulitLayoutRecyclerView.setLayoutManager(linearLayoutManager);
        mulitAdapter = new MulitSlefAdapter(mList,this);

        View inflate = LayoutInflater.from(this).inflate(R.layout.mulit_layout4, null);
        TextView lookMoreTv = (TextView) inflate.findViewById(R.id.tv_look_more);
        lookMoreTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshData();
            }
        });
        mulitAdapter.addFooterView(inflate);
        mulitAdapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.mulit_layout1,null));
        mMulitLayoutRecyclerView.setAdapter(mulitAdapter);
        mulitAdapter.notifyDataSetChanged();

        mMulitLayoutRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    // 停止

                    if (linearLayoutManager.findLastVisibleItemPosition() == mList.size()+1){
                        // 添加了尾布局
                        refreshData();
                    }
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    public void refreshData(){
        for (int i = 0 ; i < 5 ; i++){
            mList.add("position: "  + i ) ;
        }
        Toast.makeText(MulitLayoutActivity.this, "刷新数据啦", Toast.LENGTH_SHORT).show();
        mulitAdapter.notifyDataSetChanged();
    }

    private ArrayList<String> intiData() {
        for (int i = 0 ; i < 10; i++){
            mList.add("position: "+i) ;
        }
        return mList;
    }

}
