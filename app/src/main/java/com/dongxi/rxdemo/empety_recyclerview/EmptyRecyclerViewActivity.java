package com.dongxi.rxdemo.empety_recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dongxi.rxdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmptyRecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.empty_recyclerView)
    EmptyRecyclerViewActivity mEmptyRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_recycler_view);
        ButterKnife.bind(this);
    }
}
