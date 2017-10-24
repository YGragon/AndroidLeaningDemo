package com.dongxi.rxdemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;



/**
 * Created by Administrator on 2017/10/24.
 */

public class RewardCircleView extends View {

    private Paint mPaint;
    private int mWidth;

    public RewardCircleView(Context context) {
        super(context);
        init() ;
    }

    public RewardCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public RewardCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mWidth = getLayoutParams().width / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(128,128,64,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN)) ;
        canvas.drawCircle(128,128,60,mPaint);
    }
}

