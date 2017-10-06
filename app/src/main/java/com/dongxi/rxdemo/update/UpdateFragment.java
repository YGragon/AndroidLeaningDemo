package com.dongxi.rxdemo.update;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.dongxi.rxdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 升级弹窗
 */
public class UpdateFragment extends DialogFragment {


    public UpdateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog() ;
        if (dialog != null){
            DisplayMetrics displayMetrics = new DisplayMetrics() ;
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            dialog.getWindow().setLayout((int)(displayMetrics.widthPixels * 0.75),ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE) ; // 去掉标题
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.update_dialog,container) ;

        RecyclerView updateDescList = (RecyclerView) view.findViewById(R.id.rv_update_list_desc);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        updateDescList.setLayoutManager(linearLayoutManager);
        UpdateAdapter updateAdapter = new UpdateAdapter(getActivity(),R.layout.update_desc_item,getStringData()) ;
        updateDescList.setAdapter(updateAdapter);

        ImageView cancelUpdateImg = (ImageView) view.findViewById(R.id.cancel_update_img);
        cancelUpdateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        Button updateNowBtn = (Button) view.findViewById(R.id.update_now_btn);
        updateNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("UpdateFragment", "onClick: "+"正在升级...");
                // TODO: 17/9/28 需要判断网络类型
                SystemDownLoadUtils.getInstance().download(getActivity(),"http://p1.exmmw.cn/p1/wn/zhuishushenqi.apk","我的民大啊");
                //

                dismiss();
            }
        });
        return view;
    }

    /**
     * 模拟服务器返回的desc
     * @return
     */
    private List<String> getStringData() {
        List<String> list = new ArrayList<>() ;
        list.add("1. 增加热门讨论话题的顶部弹窗");
        list.add("2. 优化话题流样式");
        list.add("3. 首页增加回复数、圈子等内容");
        list.add("4. 首页菜单及侧边栏部分UI修改");
        list.add("5. 其他已知bug");

        return list;
    }

}
