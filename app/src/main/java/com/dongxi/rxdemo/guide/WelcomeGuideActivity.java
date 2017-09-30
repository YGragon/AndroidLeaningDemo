package com.dongxi.rxdemo.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dongxi.rxdemo.MainActivity;
import com.dongxi.rxdemo.R;
import com.dongxi.rxdemo.global.AppConstants;
import com.dongxi.rxdemo.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

public class WelcomeGuideActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "WelcomeGuideActivity";

    private ViewPager mVpGuide;
    private Button mBtnEnter;
    private LinearLayout mLl;

    private GuideViewPagerAdapter mGuideViewPagerAdapter ;
    private List<View> mViews ;

    private static final int[] guideImgs = {R.layout.guid_view1,R.layout.guid_view2,
            R.layout.guid_view3,R.layout.guid_view4 } ;
    private ImageView[] points ;

    private int currentIndex ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_guide);

        Log.e(TAG, "onCreate: 哈哈哈");
        mViews = new ArrayList<>() ;

        for (int i = 0; i < guideImgs.length; i++){
            View view = LayoutInflater.from(this).inflate(guideImgs[i],null) ;
            if (i == guideImgs.length-1){
                mBtnEnter = (Button)view.findViewById(R.id.btn_login) ;
                mBtnEnter.setTag("enter");
                mBtnEnter.setOnClickListener(this);
            }
            mViews.add(view) ;
        }

        mVpGuide = (ViewPager) findViewById(R.id.vp_guide) ;

        mGuideViewPagerAdapter = new GuideViewPagerAdapter(mViews) ;
        mVpGuide.setAdapter(mGuideViewPagerAdapter);
        mVpGuide.setOnPageChangeListener(new PageChanceListener());

        initDots() ;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SpUtils.putBoolean(WelcomeGuideActivity.this,AppConstants.FIRST_OPEN,true);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initDots() {
        mLl = (LinearLayout)findViewById(R.id.ll) ;
        points = new ImageView[guideImgs.length] ;

        for (int i = 0; i < guideImgs.length; i++){
            points[i] = (ImageView) mLl.getChildAt(i);
            points[i].setEnabled(false);
            points[i].setOnClickListener(this);
            points[i].setTag(i);
        }

        currentIndex = 0 ;
        points[currentIndex] .setEnabled(true); // 选中
    }

    private void setCurView(int position) {
        if (position < 0 || position > guideImgs.length){
            return;
        }
        mVpGuide.setCurrentItem(position);
    }

    @Override
    public void onClick(View v) {
        if (v.getTag().equals("enter")){
            enterMainActivity();
            return;
        }
        int position = (int) v.getTag();
        setCurView(position) ;
        setCurDot(position);

    }

    private class PageChanceListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setCurDot(position) ;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private void setCurDot(int position) {
        if (position < 0 || position > guideImgs.length || currentIndex == position){
            return;
        }
        points[position].setEnabled(true);
        points[currentIndex].setEnabled(false);
        currentIndex = position ;

    }

    private void enterMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        SpUtils.putBoolean(WelcomeGuideActivity.this, AppConstants.FIRST_OPEN,true);
        finish();
    }
}
