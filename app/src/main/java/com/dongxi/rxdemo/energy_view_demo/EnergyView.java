package com.dongxi.rxdemo.energy_view_demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.dongxi.rxdemo.R;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/12/15.
 */

public class EnergyView extends View {

    private Paint mArcPaint, mLevelPaint, mTextPaint, mPaint;
    private float length;
    private float mRadius;
    private float mCircleXY;
    private float mSweepValue = 270;

    private String mShowText = "0%";
    private RectF mRectF;
    private int mEnergyTextColor;
    private float mEnergyTextSize;
    private int mWidth;
    private int mHeight;

    public EnergyView(Context context) {
        this(context,null,0) ;
    }

    public EnergyView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0) ;
    }

    public EnergyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EnergyView);
        mEnergyTextSize = typedArray.getDimension(R.styleable.EnergyView_current_text_size, 18);
        mEnergyTextColor = typedArray.getColor(R.styleable.EnergyView_current_text_color, Color.WHITE);

        typedArray.recycle();
        init() ;
    }
    public void init(){
        mArcPaint = new Paint();
        mArcPaint.setStrokeWidth(40);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(Color.GRAY);
        mArcPaint.setStyle(Paint.Style.STROKE);

        mLevelPaint = new Paint();
        mLevelPaint.setColor(Color.GREEN);
        mLevelPaint.setTextSize(40);
        mLevelPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mEnergyTextSize);
        mTextPaint.setColor(mEnergyTextColor);
        mTextPaint.setStrokeWidth(0);

        mPaint = new Paint();
        mPaint.setStrokeWidth(40);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.STROKE);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        mHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        length = w;
        mCircleXY = length / 2;
        mRadius = (float) (length * 0.5 / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mRectF = new RectF(100, 100, 900, 900);
        // 画圆
//        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mLevelPaint);

        // 画弧线
        canvas.drawArc(mRectF, 135, 270, false, mArcPaint); // 进度条-灰色
        canvas.drawArc(mRectF, 135, 60, false, mPaint);    // 背景条-黄色
        String level[] = {"1","2","3","4","5","6","7","8","9","10"} ;

        for(int i = 0; i < level.length; i++){
            int angle =135 + 30 * i ;
            // Math.sin(angle*Math.PI/180) sin()
            Log.e(TAG, "onDraw: y=="+(500+(400*Math.sin((180-angle)*Math.PI/180))));
            Log.e(TAG, "onDraw: x=="+(100+(400-(400*Math.sin((180-angle)*Math.PI/180)))));
            Log.e(TAG, "onDraw: position=="+i);
            if (angle < 180){
                // 第三象限
                canvas.drawText(
                        level[i],
                        (float) (100+(400-400*Math.sin((90-(180-angle))*Math.PI/180))+45),
                        (float) (500+(400*Math.sin((180-angle)*Math.PI/180))-2),
                        mLevelPaint);
            }else if (angle >= 180 && angle < 270){
                // 第二象限
                canvas.drawText(
                        level[i],
                        (float) (100+(400-400*Math.sin((90-(angle-180))*Math.PI/180))+10),
                        (float) (500-(400*Math.sin((angle-180)*Math.PI/180))+50),
                        mLevelPaint);
            }else if (angle >= 270 && angle < 360){
              // 第一象限
                Log.e(TAG, "onDraw: level[i]=="+level[i]);
                canvas.drawText(
                        level[i],
                        (float) (100+(400+400*Math.sin((90-(angle-270))*Math.PI/180))-15),
                        (float) (500-(400*Math.sin((angle-270)*Math.PI/180))+50),
                        mLevelPaint);
            } else {
                // 第四象限
                canvas.drawText(
                        level[i],
                        (float) (100+(400+400*Math.sin((90-(angle-360))*Math.PI/180))-60),
                        (float) (500+(400*Math.sin((angle-360)*Math.PI/180))-2),
                        mLevelPaint);
            }
        }

        // 绘制文字
        float textWidth = mTextPaint.measureText(mShowText);   //测量字体宽度，我们需要根据字体的宽度设置在圆环中间
        canvas.drawText(mShowText, (int)(length/2-textWidth/2), (int)(length/2+textWidth/2) , mTextPaint);

        // TODO: 2017/12/16 修改宽度、完成剩余的不会绘制、添加动画、添加颗设置的属性
    }

    public void setProgress(float mSweepValue) {
        float a = (float) mSweepValue;
        if (a != 0) {
            this.mSweepValue = (float) (360.0 * (a / 100.0));
            mShowText = mSweepValue + "%";
            Log.e("this.mSweepValue:", this.mSweepValue + "");
        } else {
            this.mSweepValue = 25;
            mShowText = 25 + "%";
        }

        invalidate();
    }
}
