package com.dongxi.rxdemo.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.dongxi.rxdemo.R;


/**
 * Created by Administrator on 2017/8/17.
 */

public class CustomToolbar extends Toolbar {

    private LayoutInflater mInflater;
    private View mView;

    private TextView mLeftButtonText;
    private TextView mTitleText;
    private TextView mRightButtonText;
    private EditText mSearchView;


    public CustomToolbar(Context context) {
        this(context,null) ;
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0) ;
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView() ;

        if (attrs != null){
            final TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                    R.styleable.CustomToolbar, defStyleAttr, 0);

            CharSequence leftText = tintTypedArray.getText(R.styleable.CustomToolbar_leftButtonText);
            if (leftText != null){
                setLeftButtonText(leftText) ;
            }

            final Drawable leftIcon = tintTypedArray.getDrawable(R.styleable.CustomToolbar_leftIcon);
            if (leftIcon != null) {
                //setNavigationIcon(navIcon);
                setLeftIcon(leftIcon);
            }

            final Drawable rightIcon = tintTypedArray.getDrawable(R.styleable.CustomToolbar_rightButtonIcon);
            if (rightIcon != null) {
                //setNavigationIcon(navIcon);
                setRightButtonIcon(rightIcon);
            }


            boolean isShowSearchView = tintTypedArray.getBoolean(R.styleable.CustomToolbar_isShowSearchView,false);

            if(isShowSearchView){

                showSearchView();
                hideTitleView();

            }

            CharSequence rightButtonText = tintTypedArray.getText(R.styleable.CustomToolbar_rightButtonText);
            if(rightButtonText !=null){
                setRightButtonText(rightButtonText);
            }

            tintTypedArray.recycle();
        }



    }

    public void setLeftIcon(Drawable leftIcon) {
        // 默认显示返回箭头，文字先不需要
        mLeftButtonText.setVisibility(VISIBLE);
        mLeftButtonText.setCompoundDrawablesWithIntrinsicBounds(leftIcon, null, null, null);
    }

    public void setLeftButtonText(CharSequence leftButtonText) {
        mLeftButtonText.setVisibility(VISIBLE);
        mLeftButtonText.setText(leftButtonText);
    }

    public  void setRightTextOnClickListener(OnClickListener li){

        mRightButtonText.setOnClickListener(li);
    }
    public  void setLeftTextOnClickListener(OnClickListener li){

        mLeftButtonText.setOnClickListener(li);
    }

    public void setRightButtonText(CharSequence text){
        mRightButtonText.setVisibility(VISIBLE);
        mRightButtonText.setText(text);
    }

    public void setRightButtonText(int id){
        setRightButtonText(getResources().getString(id));
    }


    public TextView getRightButtonText(){

        return this.mRightButtonText;
    }

    public TextView getLeftButton(){

        return this.mLeftButtonText;
    }

    @Override
    public void setTitle(int resId) {

        setTitle(getContext().getText(resId));
    }

    @Override
    public void setTitle(CharSequence title) {

        initView();
        if(mTitleText !=null) {
            showTitleView();
            mTitleText.setText(title);
        }

    }

    public void showTitleView(){
        if(mTitleText !=null)
            mTitleText.setVisibility(VISIBLE);
    }

    public void hideTitleView() {
        if (mTitleText != null)
            mTitleText.setVisibility(GONE);
    }

    public void showSearchView() {
        if(mSearchView !=null)
            mSearchView.setVisibility(VISIBLE);
    }

    public void hideSearchView(){
        if(mSearchView !=null)
            mSearchView.setVisibility(GONE);
    }

    public void setRightButtonIcon(Drawable rightIcon) {
        if(mRightButtonText !=null){

            mRightButtonText.setVisibility(VISIBLE);
            mRightButtonText.setCompoundDrawablesWithIntrinsicBounds(null, null, rightIcon, null);
        }
    }

    public void  setRightButtonIcon(int icon){

        setRightButtonIcon(getResources().getDrawable(icon));
    }

    private void initView() {
        if(mView == null) {

            mInflater = LayoutInflater.from(getContext());

            mView = mInflater.inflate(R.layout.custom_toolbar, null);


            mSearchView = (EditText) mView.findViewById(R.id.toolbar_searchview);
            mLeftButtonText = (TextView) mView.findViewById(R.id.lt_main_title_left);
            mTitleText = (TextView) mView.findViewById(R.id.lt_main_title);
            mRightButtonText = (TextView) mView.findViewById(R.id.lt_main_title_right);

            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER_HORIZONTAL);

            addView(mView, lp);
        }



    }
}
