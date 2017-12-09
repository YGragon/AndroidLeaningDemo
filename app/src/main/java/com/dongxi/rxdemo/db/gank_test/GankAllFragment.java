package com.dongxi.rxdemo.db.gank_test;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dongxi.rxdemo.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankAllFragment extends Fragment {


    public static GankAllFragment newInstance(String param1) {
        GankAllFragment fragment = new GankAllFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public GankAllFragment() {

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

        RefreshLayout refreshLayout = (RefreshLayout)view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        });

        //设置 Header 为 Material样式
        refreshLayout.setRefreshHeader(new BezierRadarHeader(getActivity()));

        return view;
    }

}
