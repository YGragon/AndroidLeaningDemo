package com.dongxi.rxdemo.db.gank_test;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dongxi.rxdemo.R;
import com.dongxi.rxdemo.db.gank_test.adapter.MultipleItemQuickAdapter;
import com.dongxi.rxdemo.db.gank_test.bean.Gank;
import com.dongxi.rxdemo.db.gank_test.data.DataServer;
import com.dongxi.rxdemo.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankAllFragment extends Fragment {

    private static final String TAG = "GankAllFragment";

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;


    public static GankAllFragment newInstance(String param1) {
        GankAllFragment fragment = new GankAllFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gank_all, container, false);
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");

        List<Gank.GankItem> gankList = DataServer.getInstance().getMultipleItemData();
        Log.e(TAG, "onCreateView: size========="+gankList.size());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(linearLayoutManager);
        MultipleItemQuickAdapter gankAdapter = new MultipleItemQuickAdapter(gankList);
        recyclerview.setAdapter(gankAdapter);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                ToastUtil.showShortToast("加载完成");
                // 没有数据的时候默认显示该布局
//                mQuickAdapter.setEmptyView(getView());
            }
        });

        //设置 Header 为 Material样式
        refreshLayout.setRefreshHeader(new BezierRadarHeader(getActivity()));

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
