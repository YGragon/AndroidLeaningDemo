package com.dongxi.rxdemo.cornerlableview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.dongxi.rxdemo.R;

public class CornerLabelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corner_lable);

        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setNumColumns(2);
        gridView.setAdapter(new Adapter(this));
    }
}
