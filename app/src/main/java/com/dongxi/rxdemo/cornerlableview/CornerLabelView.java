package com.dongxi.rxdemo.cornerlableview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.dongxi.rxdemo.R;

/**
 * Created by Administrator on 2017/10/20.
 */

public class CornerLabelView extends View {
    private static final String TAG = "CornerLabelView";
    private int mHalfWidth;//View宽度的一半
    private Paint mPaint;//角标画笔
    private TextPaint mTextPaint;//文字画笔
    private Path mPath;//角标路径

    private int position;//角标位置，0：右上角、1：右下角、2：左下角、3：左上角

    //角标的显示边长
    private int sideLength = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            40,
            getResources().getDisplayMetrics());
    //字体大小
    private int textSize = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            14,
            getResources().getDisplayMetrics());
    //字体颜色
    private int textColor = Color.WHITE;
    private String text;
    //角标背景
    private int bgColor = Color.RED;    // 背景可以是颜色或者是图片
    //文字到斜边的距离
    private int marginLeanSide = -1;

    public CornerLabelView(Context context) {
        this(context, null);
    }

    public CornerLabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init();
    }

    /** 属性的初始化 */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CornerLabelView, 0, 0);
        for (int i = 0; i < ta.getIndexCount(); i++) {
            int attr = ta.getIndex(i);
            if (attr == R.styleable.CornerLabelView_position) {
                position = ta.getInt(attr, 0);
            } else if (attr == R.styleable.CornerLabelView_side_length) {
                sideLength = ta.getDimensionPixelSize(attr, sideLength);
            } else if (attr == R.styleable.CornerLabelView_text_size) {
                textSize = ta.getDimensionPixelSize(attr, textSize);
            } else if (attr == R.styleable.CornerLabelView_text_color) {
                textColor = ta.getColor(attr, textColor);
            } else if (attr == R.styleable.CornerLabelView_text) {
                text = ta.getString(attr);
            } else if (attr == R.styleable.CornerLabelView_bg_color) {
                bgColor = ta.getColor(attr, bgColor);
            } else if (attr == R.styleable.CornerLabelView_margin_lean_side) {
                marginLeanSide = ta.getDimensionPixelSize(attr, marginLeanSide);
            }
        }
        ta.recycle();
    }

    private void init() {
        mPath = new Path();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(bgColor);

        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(textColor);
        mTextPaint.setTextSize(textSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);  //  return (measureSpec & ~MODE_MASK);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);  //  return (measureSpec & MODE_MASK);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(sideLength * 2, sideLength * 2);   // 宽和高都为最大模式 则宽高的值 为角标的边长*2
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(heightSpecSize, heightSpecSize);      // 宽为最大模式 则宽高的值 为高的值
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, widthSpecSize);      // 高为最大模式 则宽高的值 为宽的值
        } else if (widthSpecSize != heightSpecSize) {
            int size = Math.min(widthSpecSize, heightSpecSize);      // 宽不等于高 则宽高的值 为宽高中的最小值
            setMeasuredDimension(size, size);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHalfWidth = Math.min(w, h) / 2;    // 获取控件的宽、高度，mHalfWidth == 宽高中的最小值 / 2
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将原点移动到画布中心
        Log.e(TAG, "onDraw: mHalfWidth=="+mHalfWidth);
        Log.e(TAG, "onDraw: position=="+position);
        Log.e(TAG, "onDraw: sideLength=="+sideLength);
        Log.e(TAG, "onDraw: marginLeanSide=="+marginLeanSide);
        canvas.translate(mHalfWidth, mHalfWidth);
        //根据用户设定的角标位置旋转画布
        canvas.rotate(getPosition() * 90);   // 0：右上角、1：右下角、2：左下角、3：左上角

        if (sideLength > mHalfWidth * 2) {  // 角标显示的边长 > 整个宽度，则边长为整个宽度的值
            sideLength = mHalfWidth * 2;
        }

        //绘制角标背景
        mPath.moveTo(-mHalfWidth, -mHalfWidth); // 移动到画布的起点
        mPath.lineTo(sideLength - mHalfWidth, -mHalfWidth); // 角标背景的宽度
        mPath.lineTo(mHalfWidth, mHalfWidth - sideLength);  // 角标背景的高度
        mPath.lineTo(mHalfWidth, mHalfWidth);   // 角标背景的中间线，最长的线？？？
        mPath.close();                          // 图形闭合，连接最短的线？？？
        canvas.drawPath(mPath, mPaint);

        //绘制文字前画布旋转45度
        canvas.rotate(45);  // 文字显示的角度
        //角标实际高度
        int h1 = (int) (Math.sqrt(2) / 2.0 * sideLength);               // ？？？
        Log.e(TAG, "onDraw: h1=实际高度="+h1);
        int h2 = (int) -(mTextPaint.ascent() + mTextPaint.descent());   // 文字的上高度+文字的下高度==文字的高度
        //文字绘制坐标
        int x = (int) -mTextPaint.measureText(text) / 2;
        int y;
        if (marginLeanSide >= 0) { //使用clv:margin_lean_side属性时，文字到斜边的距离 >= 0
            if (getPosition() == 1 || getPosition() == 2) {   // 右下方，左下方
                Log.e(TAG, "onDraw下方: h1---=="+(h1 - (marginLeanSide - mTextPaint.ascent())));
                Log.e(TAG, "onDraw下方: h1+++=="+((h1 - h2) / 2));
                if (h1 - (marginLeanSide - mTextPaint.ascent()) < (h1 - h2) / 2) {
                    y = -(h1 - h2) / 2;
                } else {
                    y = (int) -(h1 - (marginLeanSide - mTextPaint.ascent()));
                }
            } else {
                if (marginLeanSide < mTextPaint.descent()) {
                    marginLeanSide = (int) mTextPaint.descent();
                }
                Log.e(TAG, "onDraw上方: h1+++=="+((h1 - h2) / 2));
                if (marginLeanSide > (h1 - h2) / 2) {
                    marginLeanSide = (h1 - h2) / 2;
                }
                y = -marginLeanSide;
            }
        } else { //默认情况下
            if (sideLength > mHalfWidth) {
                sideLength = mHalfWidth;    // 角标的边长大于宽度的一半，则用宽度的一半
            }
            y = (int) (-Math.sqrt(2) / 2.0 * sideLength + h2) / 2;
        }

        //如果角标在右下、左下则进行画布平移、翻转，已解决绘制的文字显示问题
        if (getPosition() == 1 || getPosition() == 2) {
            canvas.translate(0, (float) (-Math.sqrt(2) / 2.0 * sideLength));
            canvas.scale(-1, -1);
        }
        //绘制文字
        canvas.drawText(text, x, y, mTextPaint);
    }

    /**
     * 获取角标的方向
     * @return
     */
    public int getPosition(){
        return this.position ;
    }

    /**
     * 设置角标的方向
     * @param position
     */
    public void setPosition(int position){
        this.position = position ;
        invalidate();
    }


    public int getSideLength() {
        return this.sideLength;
    }
    /**
     * 设置角标显示的边长
     * @param sideLength
     */
    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
        invalidate();
    }

    public int getMarginLeanSide() {
        return this.marginLeanSide;
    }

    /**
     * 设置文字到斜边的距离
     * @param marginLeanSide
     */
    public void setMarginLeanSide(int marginLeanSide) {
        this.marginLeanSide = marginLeanSide;
        invalidate();
    }

    /**
     * 设置角标背景色
     *
     * @param bgColorId
     * @return
     */

    public CornerLabelView setBgColorId(int bgColorId) {
        this.bgColor = getResources().getColor(bgColorId);
        mPaint.setColor(bgColor);
        invalidate();
        return this;
    }

    /**
     * 设置角标背景色
     *
     * @param bgColor
     * @return
     */
    public CornerLabelView setBgColor(int bgColor) {
        mPaint.setColor(bgColor);
        invalidate();
        return this;
    }

    /**
     * 设置文字颜色
     *
     * @param colorId
     * @return
     */
    public CornerLabelView setTextColorId(int colorId) {
        this.textColor = getResources().getColor(colorId);
        mTextPaint.setColor(textColor);
        invalidate();
        return this;
    }

    /**
     * 设置文字颜色
     *
     * @param color
     * @return
     */
    public CornerLabelView setTextColor(int color) {
        mTextPaint.setColor(color);
        invalidate();
        return this;
    }

    /**
     * 设置文字
     *
     * @param textId
     * @return
     */
    public CornerLabelView setText(int textId) {
        this.text = getResources().getString(textId);
        invalidate();
        return this;
    }

    /**
     * 设置文字
     *
     * @param text
     * @return
     */
    public CornerLabelView setText(String text) {
        this.text = text;
        invalidate();
        return this;
    }
}
