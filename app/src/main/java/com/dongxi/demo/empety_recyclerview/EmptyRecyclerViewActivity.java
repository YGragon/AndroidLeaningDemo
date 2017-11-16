package com.dongxi.demo.empety_recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dongxi.demo.R;
import com.dongxi.demo.widget.EmptyRecyclerView;

public class EmptyRecyclerViewActivity extends AppCompatActivity {

    private static final String TAG = "EmptyRecyclerViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_recycler_view);

        EmptyRecyclerView emptyRecyclerViewActivity = (EmptyRecyclerView) findViewById(R.id.empty_recyclerView) ;
    }
}
