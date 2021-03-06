package com.dongxi.rxdemo.anim;

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.dongxi.rxdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShowAnimFragment extends DialogFragment {

    Unbinder unbinder;
    @BindView(R.id.dialog_layout)
    FrameLayout mDialogLayout;

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_anima, container, false);
        unbinder = ButterKnife.bind(this, view);

        mDialogLayout.setBackgroundResource(R.drawable.boom);
        AnimationDrawable resources = (AnimationDrawable) mDialogLayout.getBackground();
        resources.start();
        int duration = 0;

        for (int i = 0; i < resources.getNumberOfFrames(); i++) {

            duration += resources.getDuration(i);

        }

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {

            public void run() {

                //此处调用第二个动画播放方法
                // 隐藏显示的图片
                mDialogLayout.setBackground(getResources().getDrawable(R.drawable.guitar));
                Toast.makeText(getActivity(), "动画执行完毕", Toast.LENGTH_SHORT).show();
                dismiss();
            }

        }, duration+3000);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

//    @OnClick({R.id.dialog_cancel, R.id.dialog_commit})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.dialog_cancel:
//                dismiss();
//                break;
//            case R.id.dialog_commit:
//                mDialogLayout.setBackgroundResource(R.drawable.boom);
//                AnimationDrawable resources = (AnimationDrawable) mDialogLayout.getBackground();
//                resources.start();
////        resources.setOneShot(true); // 已经在boom中设置
//                int duration = 0;
//
//                for (int i = 0; i < resources.getNumberOfFrames(); i++) {
//
//                    duration += resources.getDuration(i);
//
//                }
//
//                Handler handler = new Handler();
//
//                handler.postDelayed(new Runnable() {
//
//                    public void run() {
//
//                        //此处调用第二个动画播放方法
//                        // 隐藏显示的图片
//                        mDialogLayout.setBackground(getResources().getDrawable(R.drawable.guitar));
//                        Toast.makeText(getActivity(), "动画执行完毕", Toast.LENGTH_SHORT).show();
//                        dismiss();
//                    }
//
//                }, duration+3000);
//                break;
//        }
//    }
}
