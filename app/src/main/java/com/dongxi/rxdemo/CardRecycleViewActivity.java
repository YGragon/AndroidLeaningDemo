package com.dongxi.rxdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.dongxi.rxdemo.coordinatorlayout.CollapsingToolbarActivity;
import com.dongxi.rxdemo.utils.DensityUtil;
import com.dongxi.rxdemo.utils.ToastUtil;
import com.dongxi.rxdemo.widget.CustomToolbar;
import com.dongxi.rxdemo.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardRecycleViewActivity extends AppCompatActivity {

    private static final String TAG = "CardRecycleViewActivity";
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.recycler_view)
    EmptyRecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;

    private List<String> mStringList = new ArrayList<>();
    private CardItemAdapter mMyAdapter;
    private boolean isMenuOpen = false;

    private List<TextView> textViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_test);
        ButterKnife.bind(this);

        initTextData();

        CustomToolbar customToolbar = (CustomToolbar) findViewById(R.id.custom_toolbar);
        customToolbar.setLeftButtonText("返回");
        customToolbar.setTitle("我是居中标题");
        customToolbar.setRightButtonIcon(R.drawable.add);
        customToolbar.setRightTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardRecycleViewActivity.this, CollapsingToolbarActivity.class));
                Toast.makeText(CardRecycleViewActivity.this, "hello", Toast.LENGTH_SHORT).show();
            }
        });
        customToolbar.setLeftTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
                Snackbar.make(v, "退出", Snackbar.LENGTH_SHORT).setAction("确定退出", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }).show();
            }
        });

        textViews.add(tv1);
        textViews.add(tv2);


        mSwipeRefresh.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                refreshText() ;
                mSwipeRefresh.setRefreshing(false);
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mMyAdapter = new CardItemAdapter(this, mStringList);
        mRecyclerView.setAdapter(mMyAdapter);
        View emptyView = findViewById(R.id.empty_view);
        mRecyclerView.setEmptyView(emptyView);
        mFab.setVisibility(View.VISIBLE);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    mFab.setVisibility(View.GONE);
                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(View.GONE);
                    showCloseAnim(80);
                } else {
                    mFab.setVisibility(View.VISIBLE);
                }
            }
        });

        mMyAdapter.setCardClickListener(new CardItemAdapter.OnMyClickListener() {
            @Override
            public void cardClickListener(View view, final int position) {
                Snackbar.make(view, "hehe", Snackbar.LENGTH_SHORT).setAction("heheda", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CardRecycleViewActivity.this, CollapsingToolbarActivity.class);
                        intent.putExtra("name", mStringList.get(position));
                        startActivity(intent);
                        Toast.makeText(CardRecycleViewActivity.this, "hehe,null point", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showShortToast("标题1");
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showShortToast("标题2");
            }
        });
    }
    //打开扇形菜单的属性动画， dp为半径长度
    private void showOpenAnim(int dp) {
        tv1.setVisibility(View.VISIBLE);
        tv2.setVisibility(View.VISIBLE);


        //for循环来开始小图标的出现动画
        for (int i = 0; i < textViews.size(); i++) {
            AnimatorSet set = new AnimatorSet();
            //标题1与x轴负方向角度为20°，标题2为100°，转换为弧度
            double a = -Math.cos(20 * Math.PI / 180 * (i * 2 + 1));
            double b = -Math.sin(20 * Math.PI / 180 * (i * 2 + 1));
            double x = a * DensityUtil.dp2px(this,dp);
            double y = b * DensityUtil.dp2px(this,dp);

            set.playTogether(
                    ObjectAnimator.ofFloat(textViews.get(i), "translationX", (float) (x * 0.25), (float) x),
                    ObjectAnimator.ofFloat(textViews.get(i), "translationY", (float) (y * 0.25), (float) y),
                    ObjectAnimator.ofFloat(textViews.get(i), "alpha", 0, 1).setDuration(2000)
            );
            set.setInterpolator(new BounceInterpolator());  // 弹球差值器效果
            set.setDuration(500).setStartDelay(100);
            set.start();

            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {

                    //菜单状态置打开
                    isMenuOpen = true;
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }

        //转动加号大图标本身45°
        ObjectAnimator rotate = ObjectAnimator.ofFloat(mFab, "rotation", 0, 90).setDuration(300);
        rotate.setInterpolator(new BounceInterpolator());
        rotate.start();

    }

    //关闭扇形菜单的属性动画，参数与打开时相反
    private void showCloseAnim(int dp) {


        //for循环来开始小图标的出现动画
        for (int i = 0; i < textViews.size(); i++) {
            AnimatorSet set = new AnimatorSet();
            double a = -Math.cos(20 * Math.PI / 180 * (i * 2 + 1));
            double b = -Math.sin(20 * Math.PI / 180 * (i * 2 + 1));
            double x = a * DensityUtil.dp2px(this,dp);
            double y = b * DensityUtil.dp2px(this,dp);

            set.playTogether(
                    ObjectAnimator.ofFloat(textViews.get(i), "translationX", (float) x, (float) (x * 0.25)),
                    ObjectAnimator.ofFloat(textViews.get(i), "translationY", (float) y, (float) (y * 0.25)),
                    ObjectAnimator.ofFloat(textViews.get(i), "alpha", 1, 0).setDuration(2000)
            );
//      set.setInterpolator(new AccelerateInterpolator());
            set.setDuration(500);
            set.start();

            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {

                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(View.GONE);

                    //菜单状态置关闭
                    isMenuOpen = false;
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }


        //转动加号大图标本身45°
        ObjectAnimator rotate = ObjectAnimator.ofFloat(mFab, "rotation", 90, 0).setDuration(300);
        rotate.setInterpolator(new BounceInterpolator());
        rotate.start();


    }

    private void refreshText() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mStringList.removeAll(mStringList);
//                        mStringList.remove(mStringList.size()) ;
//                        initTextData();
//                        mMyAdapter.notifyDataSetChanged();
                        mSwipeRefresh.setRefreshing(false);
                        mMyAdapter.notifyDataSetChanged();
                        Toast.makeText(CardRecycleViewActivity.this, "刷新成功哟", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    /**
     * 模拟数据延迟出现，显示空的界面
     */
    private void initTextData() {
        for (int i = 0; i < 50; i++) {
            mStringList.add("card " + i);
        }

    }

    @OnClick(R.id.fab)
    public void onViewClicked(View view) {
//        Snackbar.make(view, "undo", Snackbar.LENGTH_LONG).setAction("hello", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(CardRecycleViewActivity.this, "hello snackbar", Toast.LENGTH_SHORT).show();
//            }
//        }).show();
        if (!isMenuOpen) {
            showOpenAnim(80);
//                    imgPublish.setImageResource(R.mipmap.publish_select);
        }else {
            showCloseAnim(80);
//                    imgPublish.setImageResource(R.mipmap.fabu);
        }
    }
}
