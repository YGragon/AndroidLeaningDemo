package com.dongxi.rxdemo.update;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dongxi.rxdemo.R;
import com.dongxi.rxdemo.guide.SplashActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

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
    @BindView(R.id.img_blur)
    ImageView imgBlur;
    @BindView(R.id.img_blur2)
    ImageView imgBlur2;
    private SystemDownLoadingReceiver systemDownLoadingReceiver;
    String url = "http://www.jcodecraeer.com/uploads/userup/9887/1F6151HP6-2109-1.png" ;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){
                Bitmap newBitmap = blurBitmap((Bitmap) msg.obj ,20.0f);
                imgBlur2.setImageBitmap(newBitmap);
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_constraintlayout);
        ButterKnife.bind(this);

        // 利用 RenderScript 实现
        new Thread(new Runnable() {
            @Override
            public void run() {

                Bitmap bitmap = getBitMBitmap(url);
                Message msg = Message.obtain();
                msg.what = 0;
                msg.obj = bitmap;
                handler.sendMessage(msg);

            }
        }).start();
        // 利用  实现
//        Glide.with(this).load(url)
//        .bitmapTransform(new BlurTransformation(this,15))
//        .into(imgBlur);
        // 原图
        Glide.with(this).load(url).into(imgBlur) ;
    }


    @OnClick({R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                // 升级
                UpdateFragment updateFragment = new UpdateFragment();
                updateFragment.show(getSupportFragmentManager(), "updateFragment");
                break;
            case R.id.btn2:
                // 强制升级
                startActivity(new Intent(this, SplashActivity.class));
                break;
        }
    }

    public static Bitmap getBitMBitmap(String urlpath) {
        Bitmap map = null;
        try {
            URL url = new URL(urlpath);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream in;
            in = conn.getInputStream();
            map = BitmapFactory.decodeStream(in);
            // TODO Auto-generated catch block
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public Bitmap blurBitmap(Bitmap bitmap, float radius){

        //Let's create an empty bitmap with the same size of the bitmap we want to blur
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //Instantiate a new Renderscript
        RenderScript rs = RenderScript.create(getApplicationContext());

        //Create an Intrinsic Blur Script using the Renderscript
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);

        //Set the radius of the blur: 0 < radius <= 25
        blurScript.setRadius(radius);

        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);

        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);

        //recycle the original bitmap
        bitmap.recycle();

        //After finishing everything, we destroy the Renderscript.
        rs.destroy();

        return outBitmap;

    }

    @Override
    public void OnEventOccur(int receiverId, Intent intent) {
        long completeDownloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
        Log.e(TAG, "OnEventOccur: completeDownloadId==" + completeDownloadId);
        if (completeDownloadId != -1) {
            SystemDownLoadUtils.getInstance().checkStatus(this, completeDownloadId);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
