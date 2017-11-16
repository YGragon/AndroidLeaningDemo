package com.dongxi.demo.update;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dongxi.demo.R;
import com.dongxi.demo.guide.SplashActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by macmini002 on 17/9/28.
 */

public class DownLoadActivity extends AppCompatActivity implements SystemDownLoadingReceiver.OnSystemDownLoadingListener {

    private static final String TAG = "DownLoadActivity";

    @BindView(R.id.banner)
    TextView banner;
    @BindView(R.id.img_guitar)
    ImageView imgGuitar;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.tab1)
    TextView tab1;
    @BindView(R.id.tab2)
    TextView tab2;
    @BindView(R.id.tab3)
    TextView tab3;
    @BindView(R.id.guideline_h)
    Guideline guidelineH;
    @BindView(R.id.guideline_w)
    Guideline guidelineW;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constaintLayout;
    private SystemDownLoadingReceiver systemDownLoadingReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_constraintlayout);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                // 升级
                UpdateFragment updateFragment = new UpdateFragment();
                updateFragment.show(getSupportFragmentManager(),"updateFragment");
                break;
            case R.id.btn2:
                // 强制升级
                startActivity(new Intent(this, SplashActivity.class));
                break;
        }
    }

    @Override
    public void OnEventOccur(int receiverId, Intent intent) {
        Log.e(TAG, "OnEventOccur: 走这里");
        long completeDownloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
        Log.e(TAG, "OnEventOccur: completeDownloadId=="+completeDownloadId);
        if (completeDownloadId != -1){
            SystemDownLoadUtils.getInstance().checkStatus(this,completeDownloadId);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
