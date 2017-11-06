package com.dongxi.rxdemo;

import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dongxi.rxdemo.callback.Li;
import com.dongxi.rxdemo.callback.Wang;
import com.dongxi.rxdemo.common.dialog.DialogFragmentHelper;
import com.dongxi.rxdemo.common.dialog.IDialogResultListener;
import com.dongxi.rxdemo.cornerlableview.CornerLabelActivity;
import com.dongxi.rxdemo.db.gank_test.GankTestActivity;
import com.dongxi.rxdemo.home.SimpleFragmentPagerAdapter;
import com.dongxi.rxdemo.mulit_layout.MulitLayoutActivity;
import com.dongxi.rxdemo.pinsenction.IndexActivity;
import com.dongxi.rxdemo.thumbup.ThumbUpActivity;
import com.dongxi.rxdemo.update.Update;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.permission;

public class MainActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {

    private static final String TAG = "MainActivity";

    String[] tags = {"婚姻育儿", "散文", "设计", "上班这点事儿", "影视天堂", "大学生活", "美人说", "运动和健身"};
    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;
    @BindView(R.id.tv_test_html_text)
    TextView mTvTestHtmlText;
    @BindView(R.id.tv_test_spanner_text)
    TextView mTvTestSpannerText;
    @BindView(R.id.reward_tv)
    TextView mRewardTv;
    @BindView(R.id.content_top_view)
    FrameLayout mContenTopView ;


    private FlexboxLayout mFlexboxLayout;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private RecyclerView mRecyclerViewRightMenu;
    private TopViewBroadcastReceiver mTopViewBroadcastReceiver;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        iniView();
        iniData();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("topview");
        mTopViewBroadcastReceiver = new TopViewBroadcastReceiver();
        registerReceiver(mTopViewBroadcastReceiver,intentFilter) ;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void iniData() {
        setStatus();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewRightMenu.setLayoutManager(linearLayoutManager);
        RightMenuAdapter rightMenuAdapter = new RightMenuAdapter(this);
        mRecyclerViewRightMenu.setAdapter(rightMenuAdapter);

        setupDrawerContent(mNavigationView);

        // 侧边栏
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.nav_message) {
                    Toast.makeText(MainActivity.this, "点击了私信", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.nav_me) {
                    permission();
                } else if (itemId == R.id.nav_setting) {
                    startActivity(new Intent(MainActivity.this, GankTestActivity.class));
                } else if (itemId == R.id.nav_manage) {
                    startActivity(new Intent(MainActivity.this, ThumbUpActivity.class));
                } else if (itemId == R.id.nav_friend) {
                    startActivity(new Intent(MainActivity.this, CornerLabelActivity.class));
                }else if (itemId == R.id.nav_notification) {
                    //
                    startActivity(new Intent(MainActivity.this, MulitLayoutActivity.class));
                }

                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        mToolbar.setTitle("DemoToolbar");
        setSupportActionBar(mToolbar);
        //设置字的颜色
        mToolbar.setTitleTextColor(Color.WHITE);
        //显示NavigationIcon


        mToolbar.setOnMenuItemClickListener(this);

        //设置返回的图标
        mToolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.slide));
    }

    private void iniView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mRecyclerViewRightMenu = (RecyclerView) findViewById(R.id.recycler_view_right_menu);

        SimpleFragmentPagerAdapter simpleFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager.setAdapter(simpleFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);   // 在setAdapter时候调用
        tabLayout.setTabMode(TabLayout.MODE_FIXED); // MODE_SCROLLABLE 适合多Tab

        // 流式布局
        mFlexboxLayout = (FlexboxLayout) findViewById(R.id.flexboxLayout);
        //动态添加子View
        for (int i = 0; i < tags.length; i++) {
            mFlexboxLayout.addView(getFlexboxLayoutItemView(i));
        }
        // 星级评分
        RatingBar ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        ratingBar.setNumStars(3);
        ratingBar.setRating(3f);

        // 字符串动态设置,虽然能够加载图片，但是代码实现太麻烦
        String str1 = "我要打赏这个内容，作者太帅了balabala，谁也不许拦我，除非她给我发红包，或者点个Star";
        String str2 = "我要<font color='#FF4081'>打赏</font>这个内容，<strong><font color='#FF4081'>作者太TM帅</font></strong>了balabala，谁也不许拦我，除非她给我发红包，或者点个Star";

        SpannableStringBuilder builder = new SpannableStringBuilder(str1);
        // "我要"字体颜色变为粉色，Spanned.SPAN_EXCLUSIVE_INCLUSIVE 表示起始和终止的模式为：包左不包右
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#FF4081")), 0, 2, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        // 设置背景色
        builder.setSpan(new BackgroundColorSpan(Color.parseColor("#009ad6")), 4, 6, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        // 设置字体大小（绝对值,单位：像素）
        builder.setSpan(new AbsoluteSizeSpan(80), 12, 14, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        // 设置粗体和斜体
        builder.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 15, 23, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        // 设置删除线
        builder.setSpan(new StrikethroughSpan(), 23, 29, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        // 设置下划线
        builder.setSpan(new UnderlineSpan(), 29, 35, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        // 设置图片
        builder.setSpan(new ImageSpan(this, R.mipmap.ic_launcher), 35, 38, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        // 设置点击
        builder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this, "点击了打赏", Toast.LENGTH_SHORT).show();
                // 测试回掉接口
                Li li = new Li();
                Wang wang = new Wang(li);
                wang.askQuestion("1 + 1 = ?");
            }
        }, 2, 4, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        mTvTestSpannerText.setText(builder);
        // 设置点击
        mTvTestSpannerText.setMovementMethod(LinkMovementMethod.getInstance());
        mTvTestHtmlText.setText(Html.fromHtml(str2));
    }

    private View getFlexboxLayoutItemView(final int position) {
        View view = getLayoutInflater().inflate(R.layout.item_flex_box_layout, null, false);
        TextView itemTv = (TextView) view.findViewById(R.id.item_tv);
        itemTv.setText(tags[position]);
        itemTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Update.class));
                Toast.makeText(MainActivity.this, "点击了 " + tags[position], Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }

    /**
     * 测试封装的权限工具
     */
    private void permission() {
        requestRunPermission(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA}, new PermissionListener() {
            @Override
            public void onGranted() {
                //表示所有权限都授权了
                Toast.makeText(MainActivity.this, "所有权限都授权了，可以搞事情了", Toast.LENGTH_SHORT).show();
                //我们可以执行打电话的逻辑
                call();
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                for (int i = 0; i < deniedPermission.size(); i++) {

                    PermissionTip.showTipGoSetting(MainActivity.this, deniedPermission.get(0), deniedPermission.get(0) + "被拒绝");
                    Toast.makeText(MainActivity.this, "被拒绝的权限：" + permission, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            Uri uri = Uri.parse("tel:" + "10086");
            intent.setData(uri);
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }


    //设置状态栏透明
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setStatus() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialogListView() {
        final View bottomView = View.inflate(MainActivity.this, R.layout.dialog_recyclerview, null);//填充ListView布局
        RecyclerView recyclerView = (RecyclerView) bottomView.findViewById(R.id.dialog_recyclerView);//初始化ListView控件

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("酒水：10==" + i);
        }

        list.add("合计：100块");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new DialogRecyclerViewAdapter(this, list));//ListView设置适配器

        // 居中标题
        TextView title = new TextView(this);
        title.setText("费用详细");
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
// title.setTextColor(getResources().getColor(R.color.greenBG));
        title.setTextSize(23);

        AlertDialog dialogRecyclerView = new AlertDialog.Builder(this)
                .setCustomTitle(title).setView(bottomView)//在这里把写好的这个listview的布局加载dialog中
                .setNegativeButton("我知道了", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                }).create();
        dialogRecyclerView.show();
        //控制弹窗的高度
        bottomView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                                       int oldRight, int oldBottom) {
                int contentHeight = bottomView.getHeight();

                int needHeight = 500;

                if (contentHeight > needHeight) {
                    //注意：这里的 LayoutParams 必须是 FrameLayout的！！
                    bottomView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                            needHeight));
                }
            }
        });
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                startActivity(new Intent(MainActivity.this, CardRecycleViewActivity.class));
                Toast.makeText(this, "menu1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_share:
                mDrawerLayout.openDrawer(GravityCompat.END);
                Toast.makeText(this, "menu2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "menu3", Toast.LENGTH_SHORT).show();
                showDialogListView();
                break;
            default:
                break;

        }
        return true;
    }


    @OnClick({R.id.btn_1, R.id.btn_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                DialogFragmentHelper.showTimeDialog(getSupportFragmentManager(), "日期选择器",Calendar.getInstance(), new IDialogResultListener<Calendar>() {
                    @Override
                    public void onDataResult(Calendar result) {

                    }
                },true);
                break;
            case R.id.btn_2:
                // 跳转悬浮头的列表页
                startActivity(new Intent(MainActivity.this, IndexActivity.class));
                break;
        }
    }

    @OnClick(R.id.reward_tv)
    public void onViewClicked() {
        Log.e(TAG, "onViewClicked: 发送");
        //  发送广播
        Intent intent = new Intent();
        intent.setAction("topview") ;
        sendBroadcast(intent);



        // 弹窗出来在显示帧动画
//        DialogFragmentHelper.showEmptyDialog(getSupportFragmentManager(),true);

//        ShowAnimFragment showAnimaFragment = new ShowAnimFragment();
//        showAnimaFragment.show(getSupportFragmentManager(),"showAnimaFragment");

    }

    class TopViewBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("topview")){
                // TODO: 2017/11/6 show top view
                Log.e(TAG, "onReceive: show top view");
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                final View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.top_view_notify, null);
                view.setLayoutParams(lp);
                mContenTopView.addView(view);
                final TextView topViewTv = (TextView) view.findViewById(R.id.top_view_tv);
//                topViewTv.setVisibility(View.VISIBLE);
//                Timer timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        Log.e(TAG, "run: 等待两秒试试看");
//                        topViewTv.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Log.e(TAG, "onClick: 点击了");
////                                topViewTv.setVisibility(View.GONE);
//                                mContenTopView.removeView(view);
//                                startActivity(new Intent(MainActivity.this,MulitLayoutActivity.class));
//                            }
//                        });
////                        mContenTopView.removeView(view);
//                    }
//                },2000);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        Log.e(TAG, "run: 隐藏啦");
                        view.setVisibility(View.GONE); //view是要隐藏的控件
                    }
                }, 3000);  //3000毫秒后执行
                topViewTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this,MulitLayoutActivity.class));
                    }
                });
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mTopViewBroadcastReceiver);
    }
}
