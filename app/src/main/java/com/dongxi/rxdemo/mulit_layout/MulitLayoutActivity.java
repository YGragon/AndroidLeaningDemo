package com.dongxi.rxdemo.mulit_layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dongxi.rxdemo.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 多布局的数据的设置测试
 */
public class MulitLayoutActivity extends AppCompatActivity {

    @BindView(R.id.mulit_layout_recyclerView)
    RecyclerView mMulitLayoutRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulit_layout);
        ButterKnife.bind(this);

        int[] layoutId = new int[]{
                R.layout.mulit_layout1,
                R.layout.mulit_layout2,
                R.layout.mulit_layout3
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mMulitLayoutRecyclerView.setLayoutManager(linearLayoutManager);
        MulitAdapter mulitAdapter = new MulitAdapter(this,layoutId,intiData());

        // tv1 setText
//        mulitAdapter.setTv1("第一条");

        // tv2 setText
//        mulitAdapter.setTv2("第二条");

        mMulitLayoutRecyclerView.setAdapter(mulitAdapter);

    }

    private ArrayList<Test> intiData() {
        ArrayList<Test> list = new ArrayList<>();
        Test tv1 = new Test();
        tv1.text = "哈哈" ;
        list.add(tv1) ;
        Test tv2 = new Test();
        tv2.text = "嘻嘻" ;
        list.add(tv2) ;

        for (int i = 0 ; i < 5; i++){
            Test test = new Test();
            test.text = "position: "+i ;
            list.add(test) ;
        }

        return list;
    }

    public class Test{
        public String text ;
    }
}
