package com.dongxi.rxdemo;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.permission;

public class MainActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {

    private static final String TAG = "MainActivity";
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private RecyclerView mRecyclerViewRightMenu;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniView() ;
        iniData() ;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void iniData() {
        setStatus() ;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewRightMenu.setLayoutManager(linearLayoutManager);
        RightMenuAdapter rightMenuAdapter = new RightMenuAdapter(this);
        mRecyclerViewRightMenu.setAdapter(rightMenuAdapter);

        setupDrawerContent(mNavigationView);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.nav_message){
                    Toast.makeText(MainActivity.this, "点击了私信", Toast.LENGTH_SHORT).show();
                }else if (itemId == R.id.nav_me){
                    permission();
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
                for(int i = 0; i < deniedPermission.size(); i++){

                    PermissionTip.showTipGoSetting(MainActivity.this,deniedPermission.get(0),deniedPermission.get(0)+"被拒绝");
                    Toast.makeText(MainActivity.this, "被拒绝的权限：" + permission, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void call(){
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            Uri uri = Uri.parse("tel:" + "10086");
            intent.setData(uri);
            startActivity(intent);
        }catch (SecurityException e){
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
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true ;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDialogListView() {
        final View bottomView = View.inflate(MainActivity.this,R.layout.dialog_recyclerview,null);//填充ListView布局
        RecyclerView recyclerView = (RecyclerView) bottomView.findViewById(R.id.dialog_recyclerView);//初始化ListView控件

        List<String> list = new ArrayList<>() ;
        for (int i= 0 ; i < 20; i++){
            list.add("酒水：100=="+i) ;
        }

        list.add("合计：300块") ;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new DialogRecyclerViewAdapter(this,list));//ListView设置适配器

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
                startActivity(new Intent(MainActivity.this,MDOneActivity.class));
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
}
