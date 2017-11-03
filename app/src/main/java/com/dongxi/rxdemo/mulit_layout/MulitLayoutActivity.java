package com.dongxi.rxdemo.mulit_layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private ArrayList<Test> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulit_layout);
        ButterKnife.bind(this);

        intiData() ;

        int[] layoutId = new int[]{
                R.layout.mulit_layout1,
                R.layout.mulit_layout2,
                R.layout.mulit_layout3,
                R.layout.mulit_layout4
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mMulitLayoutRecyclerView.setLayoutManager(linearLayoutManager);
//        MulitSlefAdapter mulitAdapter = new MulitSlefAdapter(this,intiData());
        final MulitAdapter mulitAdapter = new MulitAdapter(this,layoutId,mList);

        // tv1 setText
//        mulitAdapter.setTv1("第一条");

        // tv2 setText
//        mulitAdapter.setTv2("第二条");

        mMulitLayoutRecyclerView.setAdapter(mulitAdapter);

        mulitAdapter.setOnClick(new MulitAdapter.OnItemInterface() {
            @Override
            public void onItemClick(int position) {
                for (int i = 0 ; i < 5 ; i++){
                    Test test = new Test();
                    test.text = "position: " + position + i ;
                    mList.add(test) ;
                }
                Log.e(TAG, "onItemClick: size=="+mList.size());
                Toast.makeText(MulitLayoutActivity.this, "刷新数据啦", Toast.LENGTH_SHORT).show();
                mulitAdapter.notifyDataSetChanged();
            }
        });

    }

    private ArrayList<Test> intiData() {
        mList = new ArrayList<>();
        Test tv1 = new Test();
        tv1.text = "哈哈" ;
        mList.add(tv1) ;
        Test tv2 = new Test();
        tv2.text = "嘻嘻" ;
        mList.add(tv2) ;

        for (int i = 0 ; i < 10; i++){
            Test test = new Test();
            test.text = "position: "+i ;
            mList.add(test) ;
        }
//        // 用于显示查看更多的position
        Test test = new Test();
//        test.text = "position: last normal";
        mList.add(test) ;

        return mList;
    }

    public class Test{
        public String text ;
    }
}
