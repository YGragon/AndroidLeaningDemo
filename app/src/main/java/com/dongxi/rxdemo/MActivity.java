package com.dongxi.rxdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MActivity extends AppCompatActivity {

    private static final String TAG = "MActivity";

    @BindView(R.id.img_guitar)
    ImageView mImgGuitar;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.content_text)
    TextView mContentText;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    private CustomToolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m);
        ButterKnife.bind(this);

        Intent intent = getIntent() ;
        String name = intent.getStringExtra("name");
        Log.e(TAG, "onCreate: name=="+name);
        mToolbar = (CustomToolbar) findViewById(R.id.m_custom_toolbar);

        mCollapsingToolbar.setTitle(name);
        Glide.with(this).load(R.drawable.guitar).into(mImgGuitar) ;
        String contentText = getContentText("我是好人");
        mContentText.setText(contentText);

    }

    private String getContentText(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <500 ;i++){
            stringBuilder.append(name);
        }
        return stringBuilder.toString() ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true ;
        }
        return super.onOptionsItemSelected(item);
    }
}
