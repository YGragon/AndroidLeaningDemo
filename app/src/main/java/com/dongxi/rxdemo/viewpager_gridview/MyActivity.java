package com.dongxi.rxdemo.viewpager_gridview;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dongxi.rxdemo.R;
import com.dongxi.rxdemo.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    private ViewPager viewPager;
    private LinearLayout group;//圆点指示器
    private ImageView[] ivPoints;//小圆点图片的集合
    private int totalPage; //总的页数
    private int mPageSize = 8; //每页显示的最大的数量
    private List<ProdctBean> listDatas;//总的数据源
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
    //private int currentPage;//当前页

    private String[] proName = {"名称0","名称1","名称2","名称3","名称4","名称5",
            "名称6","名称7","名称8","名称9","名称10","名称11","名称12","名称13",
            "名称14","名称15","名称16","名称17","名称18","名称19","名称20"};
    private MyGridViewAdpter mMyGridViewAdpter;
    private ArrayList<MyGridViewAdpter> girdViewList = new ArrayList<>() ;
    private int checkPosition = 0 ;
    private Map<Integer,Integer> realList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        //初始化控件
        initView();
        //添加业务逻辑
        initData();

    }




    private void initView() {
        // TODO Auto-generated method stub
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        group = (LinearLayout)findViewById(R.id.points);
        listDatas = new ArrayList<ProdctBean>();
        for(int i =0 ; i < proName.length; i++){
            if (i == 1){
                listDatas.add(new ProdctBean(proName[i], R.mipmap.ic_launcher,true));
            }else {
                listDatas.add(new ProdctBean(proName[i], R.mipmap.ic_launcher,false));
            }
        }
    }
    private void initData() {
        // TODO Auto-generated method stub
        //总的页数向上取整
        totalPage = (int) Math.ceil(listDatas.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<View>();
        viewPagerList.clear();
        for(int i = 0; i < totalPage; i++){
            //每个页面都是inflate出一个新实例
            final GridView gridView = (GridView) View.inflate(this, R.layout.item_gridview, null);
            mMyGridViewAdpter = new MyGridViewAdpter(this, listDatas, i, mPageSize);    // adapter 三个不一样
            Log.e(TAG, "initData: adapter=="+mMyGridViewAdpter);
            gridView.setAdapter(mMyGridViewAdpter);

            final int index = i ;


            mMyGridViewAdpter.setOnPositionClick(new MyGridViewAdpter.OnPositionClick() {
                @Override
                public void click(int pos) {
                    for (int i = 0 ; i < listDatas.size(); i++){
                        if (i == pos){
                            checkPosition = index ;
                            realList.put(index,pos) ;
                            listDatas.get(pos).setSelect(true);
                        }else {
                            listDatas.get(i).setSelect(false);
                        }
                    }
                    Log.e(TAG, "click: adapter=="+mMyGridViewAdpter );


                    ToastUtil.showShortToast(listDatas.get(pos).getName());
                }
            });

            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
            girdViewList.add(mMyGridViewAdpter) ;
            mMyGridViewAdpter.notifyDataSetChanged();

        }


        //设置ViewPager适配器
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(viewPagerList);
        viewPager.setAdapter(myViewPagerAdapter);

        //添加小圆点
        ivPoints = new ImageView[totalPage];
        for(int i = 0; i < totalPage; i++){
            //循坏加入点点图片组
            ivPoints[i] = new ImageView(this);
            if(i==0){
                ivPoints[i].setImageResource(R.drawable.dot_focused);
            }else {
                ivPoints[i].setImageResource(R.drawable.dot_normal);
            }
            ivPoints[i].setPadding(8, 8, 8, 8);
            group.addView(ivPoints[i]);
        }
        //设置ViewPager的滑动监听，主要是设置点点的背景颜色的改变
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                //currentPage = position;
                for(int i=0 ; i < totalPage; i++){
                    if(i == position){
                        ivPoints[i].setImageResource(R.drawable.dot_focused);
                    }else {
                        ivPoints[i].setImageResource(R.drawable.dot_normal);
                    }
                }

                Log.e(TAG, "onPageSelected:checkPosition== "+checkPosition);
                Log.e(TAG, "onPageSelected: position=="+position);
                if (checkPosition != position){
                    for (int i = 0; i < listDatas.size(); i++){
                        listDatas.get(i).setSelect(false);
                    }
                    girdViewList.get(position).notifyDataSetChanged();
//                    if (realList.get(position) != null ){
//
//
//                    listDatas.get(realList.get(position)).setSelect(false);
//                    }
                }
            }
        });
    }

}
