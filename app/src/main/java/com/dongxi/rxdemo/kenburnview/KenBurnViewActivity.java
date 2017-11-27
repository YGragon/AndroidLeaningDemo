package com.dongxi.rxdemo.kenburnview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.dongxi.rxdemo.R;
import com.dongxi.rxdemo.anim.NumAnim;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;

public class KenBurnViewActivity extends AppCompatActivity {

    private static final String TAG = "KenBurnViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ken_burn_view);
        KenBurnsView kbv = (KenBurnsView) findViewById(R.id.image);
        // 滚动的数字
        TextView runText = (TextView) findViewById(R.id.runText);
        NumAnim.startAnim(runText, 0.03f);   //第二个参数是textView要显示的价格

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
    }
}
