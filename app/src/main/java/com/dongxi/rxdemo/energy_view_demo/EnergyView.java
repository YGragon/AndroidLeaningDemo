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
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

//        if (widthSpecMode == MeasureSpec.AT_MOST && heightMeasureSpec == MeasureSpec.AT_MOST){
//            setMeasuredDimension(200, 200);
//        }else if (widthSpecMode == MeasureSpec.AT_MOST){
//            setMeasuredDimension(200, heightSpecSize);
//        }else if (heightSpecMode == MeasureSpec.AT_MOST){
//            setMeasuredDimension(widthSpecSize, 200);
//        }
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
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;

        Log.e("EnergyView", "onDraw: width=="+width );
        Log.e("EnergyView", "onDraw: height=="+height );
        mRectF = new RectF(100, 100, 900, 900);
        // 画圆
//        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mLevelPaint);

        // 画弧线
        canvas.drawArc(mRectF, 180, 180, false, mArcPaint); // 进度条-灰色
        canvas.drawArc(mRectF, 180, 120, false, mPaint);    // 背景条-黄色
        String level[] = {"1","2","3","4","5","6","7","8","9","10"} ;

        for (int i = 0; i <level.length; i++ ){

            int angle = 20 * i ;
            // Math.sin(angle*Math.PI/180) sin() 里面的参数时弧度不是角度，弧度和角度的转换方式：角度*Math.PI/180
            float dX = (100 + (float)(800/9 * i)) ;
            float y = 360 * (float)(Math.sin(angle*Math.PI/180)) ;

            if (i == 0){
                canvas.drawText(
                        level[i],
                        100+50,
                        500,
                        mLevelPaint);  // 刻度
            }else if (i == level.length-1){
                Log.e(TAG, "onDraw: "+level[i]);
                canvas.drawText(
                        level[i],
                        900-50,
                        500,
                        mLevelPaint);  // 刻度
            }else {
                canvas.drawText(
                        level[i],
                        dX,
                        (500 - y + 40),
                        mLevelPaint);  // 刻度
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
