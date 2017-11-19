package com.dongxi.rxdemo.pinsenction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dongxi.rxdemo.R;

import java.util.List;


public class IndexActivity extends AppCompatActivity{
    private PinnedSectionListView pinned_section_list;
    private IndexAdapter indexAdapter;
    private List<CityBean> data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        //实例化拥有悬停头的控件
        pinned_section_list = (PinnedSectionListView) findViewById(R.id.pinned_section_list);
        //模拟数据
        data = new TestData().initData();
        //
        indexAdapter = new IndexAdapter(this, data);
        pinned_section_list.setAdapter(indexAdapter);

        indexAdapter.setOnSectionClickListener(new IndexAdapter.onSectionClickListener() {
            @Override
            public void sectionClick(int type, int position) {
                if (type == 0){
//                    pinned_section_list.smoothScrollToPosition(+20);
                    pinned_section_list.setSelection(11);
                    Toast.makeText(IndexActivity.this, "精彩", Toast.LENGTH_SHORT).show();
                }else {
                    pinned_section_list.smoothScrollToPosition(-5);
                    Toast.makeText(IndexActivity.this, "全部", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
