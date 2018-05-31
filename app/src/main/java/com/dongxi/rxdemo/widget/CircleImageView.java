package com.dongxi.rxdemo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextPaint;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/8/8.
 * 圆形图片
 *     圆角
 *     设置边框
 *     设置边框颜色
 *     设置边框渐变
 */

public class CircleImageView extends AppCompatImageView {
    private static final ScaleType SCALE_TYPE = ScaleType.CENTER_CROP;

    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 2;

    // 圆形边框的厚度默认值。
    // 如果是0，则没有天蓝色渐变的边框。
    private static final int DEFAULT_BORDER_WIDTH = 0;

    private static final int DEFAULT_BORDER_COLOR = Color.BLACK;

    private final RectF mDrawableRect = new RectF();
    private final RectF mBorderRect = new RectF();

    private final Matrix mShaderMatrix = new Matrix();
    private final Paint mBitmapPaint = new Paint();
    private final Paint mBorderPaint = new Paint();

    private int mBorderColor = DEFAULT_BORDER_COLOR;
    private int mBorderWidth = DEFAULT_BORDER_WIDTH;

    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private int mBitmapHeight;

    private float mDrawableRadius;
    private float mBorderRadius;

    private boolean mReady;
    private boolean mSetupPending;
    private final Paint mFlagBackgroundPaint = new Paint();
    private final TextPaint mFlagTextPaint = new TextPaint();
    private String mFlagText;
    private boolean mShowFlag = false;
    private Rect mFlagTextBounds = new Rect();

    // 渐变
    Shader mSweepGradient = null;

    public CircleImageView(Context context) {
        super(context);
        init();
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        super.setScaleType(SCALE_TYPE);
        mReady = true;
        if (mSetupPending) {
            setup();
            mSetupPending = false;
        }
    }

    /**
     * 设置支持wrap_content
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(200,200);
        }else if (widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(200, heightSpecSize);
        }else if (heightMeasureSpec == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize, 200);
        }
    }

    @Override
    public ScaleType getScaleType() {
        return SCALE_TYPE;
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        if (scaleType != SCALE_TYPE) {
            throw new IllegalArgumentException(String.format(
                    "ScaleType %s not supported.", scaleType));
        }
    }

    @Override
    public void setAdjustViewBounds(boolean adjustViewBounds) {
        if (adjustViewBounds) {
            throw new IllegalArgumentException(
                    "adjustViewBounds not supported.");
        }
    }

    /**
     * 核心
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mDrawableRadius,
                mBitmapPaint);

        if (mBorderWidth != 0) {
            canvas.save();
            canvas.rotate(20, getWidth() / 2, getHeight() / 2);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mBorderRadius,
                    mBorderPaint);
            canvas.restore();
        }
        if (mShowFlag && mFlagText != null) {
            canvas.drawArc(mBorderRect, 40, 100, false, mFlagBackgroundPaint);
            mFlagTextPaint.getTextBounds(mFlagText, 0, mFlagText.length(),
                    mFlagTextBounds);
            canvas.drawText(mFlagText, getWidth() / 2,
                    (float) ((3 + Math.cos((float) (Math.PI * 5 / 18)))
                            * getHeight() / 4 + mFlagTextBounds.height() / 3),
                    mFlagTextPaint);

        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setup();
    }

    /**
     * 获取边框的颜色
     * @return
     */
    public int getBorderColor() {
        return mBorderColor;
    }

    /**
     * 设置边框的颜色
     * @param borderColor
     */
    public void setBorderColor(int borderColor) {
        if (borderColor == mBorderColor) {
            return;
        }
        mBorderColor = borderColor;
        mBorderPaint.setColor(mBorderColor);
        invalidate();
    }

    /**
     * 获取边框的宽度
     * @return
     */
    public int getBorderWidth() {
        return mBorderWidth;
    }

    /**
     * @param borderWidth
     *   圆形的边框厚度。
     */
    public void setBorderWidth(int borderWidth) {
        if (borderWidth == mBorderWidth) {
            return;
        }

        mBorderWidth = borderWidth;
        setup();
    }

    /**
     * 设置bitmap
     * @return
     */
    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mBitmap = bm;
        setup();
    }

    /**
     * 设置drawable
     * @return
     */
    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        mBitmap = getBitmapFromDrawable(drawable);
        setup();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }
    /**
     * 设置路径
     * @return
     */
    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;

            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION,
                        COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    /**
     * 核心
     */
    private void setup() {
        if (!mReady) {
            mSetupPending = true;
            return;
        }

        if (mBitmap == null) {
            return;
        }

        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);

        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setShader(mBitmapShader);

        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);

        mBitmapHeight = mBitmap.getHeight();
        mBitmapWidth = mBitmap.getWidth();

        mBorderRect.set(0, 0, getWidth(), getHeight());
        mBorderRadius = Math.min((mBorderRect.height() - mBorderWidth) / 2,
                (mBorderRect.width() - mBorderWidth) / 2);

        mDrawableRect.set(mBorderWidth, mBorderWidth, mBorderRect.width()
                - mBorderWidth, mBorderRect.height() - mBorderWidth);
        mDrawableRadius = Math.min(mDrawableRect.height() / 2,
                mDrawableRect.width() / 2);

        mFlagBackgroundPaint.setColor(Color.BLACK & 0x66FFFFFF);
        mFlagBackgroundPaint.setFlags(TextPaint.ANTI_ALIAS_FLAG);

        mFlagTextPaint.setFlags(TextPaint.ANTI_ALIAS_FLAG);
        mFlagTextPaint.setTextAlign(Paint.Align.CENTER);
        mFlagTextPaint.setColor(Color.WHITE);
        mFlagTextPaint
                .setTextSize(getResources().getDisplayMetrics().density * 18);

        // 边框内的颜色渐变
        mSweepGradient = new SweepGradient(getWidth() / 2, getHeight() / 2,
                new int[] { Color.rgb(255, 255, 255), Color.rgb(1, 209, 255) },
                null);

        mBorderPaint.setShader(mSweepGradient);

        updateShaderMatrix();
        invalidate();
    }

    private void updateShaderMatrix() {
        float scale;
        float dx = 0;
        float dy = 0;

        mShaderMatrix.set(null);

        if (mBitmapWidth * mDrawableRect.height() > mDrawableRect.width()
                * mBitmapHeight) {
            scale = mDrawableRect.height() / (float) mBitmapHeight;
            dx = (mDrawableRect.width() - mBitmapWidth * scale) * 0.5f;
        } else {
            scale = mDrawableRect.width() / (float) mBitmapWidth;
            dy = (mDrawableRect.height() - mBitmapHeight * scale) * 0.5f;
        }

        mShaderMatrix.setScale(scale, scale);
        mShaderMatrix.postTranslate((int) (dx + 0.5f) + mBorderWidth,
                (int) (dy + 0.5f) + mBorderWidth);

        mBitmapShader.setLocalMatrix(mShaderMatrix);
    }

    public void setShowFlag(boolean show) {
        mShowFlag = show;
        invalidate();
    }

    public void setFlagText(String text) {
        mFlagText = text;
        invalidate();
    }
}
