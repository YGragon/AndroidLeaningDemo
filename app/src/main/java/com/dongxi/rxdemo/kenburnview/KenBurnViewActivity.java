package com.dongxi.rxdemo.kenburnview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.dongxi.rxdemo.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;

public class KenBurnViewActivity extends AppCompatActivity {

    private static final String TAG = "KenBurnViewActivity";
    private RelativeLayout mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ken_burn_view);
        KenBurnsView kbv = (KenBurnsView) findViewById(R.id.image);
        kbv.setTransitionListener(new KenBurnsView.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                Log.e(TAG, "onTransitionStart:");
            }
            @Override
            public void onTransitionEnd(Transition transition) {
                Log.e(TAG, "onTransitionEnd: ");
            }
        });

        mRoot = (RelativeLayout) findViewById(R.id.root_view);
        //键盘显示，触摸键盘以外隐藏键盘
        findViewById(R.id.content_view).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });
        mRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                View focus = mRoot.findFocus();
                ScrollView sv = ((ScrollView) findViewById(R.id.scrollView));
                sv.fullScroll(ScrollView.FOCUS_DOWN);

                if (focus != null && focus instanceof EditText) {//保证滑动之后 焦点依然未变
                    focus.requestFocus();
                }

//                int loc[] = new int[2];
//                View view = findViewById(R.id.bottom_view);
//                view.getLocationOnScreen(loc);
//                if (getScreenHeight(KenBurnViewActivity.this) - loc[1] < Math.abs(getScreenHeight(KenBurnViewActivity.this) / 2 - loc[1])) {//无压缩状态
//                    view.setVisibility(View.VISIBLE);
//                } else {
//                    view.setVisibility(View.INVISIBLE);
//                }
            }
        });
    }
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
}
