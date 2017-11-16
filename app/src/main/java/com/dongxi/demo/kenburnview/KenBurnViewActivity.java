package com.dongxi.demo.kenburnview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dongxi.demo.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;

public class KenBurnViewActivity extends AppCompatActivity {

    private static final String TAG = "KenBurnViewActivity";

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
    }
}
