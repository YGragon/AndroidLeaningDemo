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
import com.dongxi.rxdemo.utils.DensityUtil;

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

        mRectF = new RectF((float) (length * 0.2), (float) (length * 0.1),
                (float) (length * 0.8), (float) (length * 0.7));
        // 画圆
//        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mLevelPaint);

        // 画弧线
        canvas.drawArc(mRectF, 145, 250, false, mArcPaint); // 进度条-灰色
        canvas.drawArc(mRectF, 145, 60, false, mPaint);    // 背景条-黄色

        for (int i = 1; i <= 10; i++) {
            canvas.save();
            // 以屏幕的宽高为中心，顺时针旋转
//            canvas.rotate(angle, mWidth / 2, mHeight / 2);
            canvas.rotate(220 + 270 / 10 * i , mWidth / 2, mWidth / 2-110);
//            canvas.drawLine(mWidth / 2, mHeight / 2 - 200, mWidth / 2, mHeight / 2 - 180, mPaintline); //起点左边X,Y、终点坐标X,Y、画笔
//            canvas.drawText("" + i, mWidth / 2-300, mHeight / 2 - 300, mTextPaint);
            canvas.drawText("" + i, mWidth / 2-60, mWidth / 2 - 360, mTextPaint);
//            canvas.drawText("" + i, (float) ((length * 0.1) / 2 - 30), (float) ((length * 0.9) / 2 - 50), mLevelPaint);
            canvas.restore();
        }
        // 绘制当前等级
        canvas.drawText("8",mWidth / 2, mWidth / 2,mLevelPaint);
        // 绘制距离下一等级的经验
        mTextPaint.setTextSize(DensityUtil.dp2px(getContext(),12));
        canvas.drawText("距离下一等级需要 888 经验",mWidth / 2 - 200, mWidth / 2 + 200,mTextPaint);
    }

//            if (angle < 180){
//                // 第三象限
//                canvas.drawText(
//                        level[i],
//                        (float) (100+(400-400*Math.sin((90-(180-angle))*Math.PI/180))+45),
//                        (float) (500+(400*Math.sin((180-angle)*Math.PI/180))-2),
//                        mLevelPaint);
//            }else if (angle >= 180 && angle < 270){
//                // 第二象限
//                canvas.drawText(
//                        level[i],
//                        (float) (100+(400-400*Math.sin((90-(angle-180))*Math.PI/180))+10),
//                        (float) (500-(400*Math.sin((angle-180)*Math.PI/180))+50),
//                        mLevelPaint);
//            }else if (angle >= 270 && angle < 360){
//              // 第一象限
//                Log.e(TAG, "onDraw: level[i]=="+level[i]);
//                canvas.drawText(
//                        level[i],
//                        (float) (100+(400+400*Math.sin((90-(angle-270))*Math.PI/180))-15),
//                        (float) (500-(400*Math.sin((angle-270)*Math.PI/180))+50),
//                        mLevelPaint);
//            } else {
//                // 第四象限
//                canvas.drawText(
//                        level[i],
//                        (float) (100+(400+400*Math.sin((90-(angle-360))*Math.PI/180))-60),
//                        (float) (500+(400*Math.sin((angle-360)*Math.PI/180))-2),
//                        mLevelPaint);
//            }
//        }
//
//        // 绘制文字
//        float textWidth = mTextPaint.measureText(mShowText);   //测量字体宽度，我们需要根据字体的宽度设置在圆环中间
//        canvas.drawText(mShowText, (int)(length/2-textWidth/2), (int)(length/2+textWidth/2) , mTextPaint);

        // TODO: 2017/12/16 修改宽度、完成剩余的不会绘制、添加动画、添加可设置的属性
//    }

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
