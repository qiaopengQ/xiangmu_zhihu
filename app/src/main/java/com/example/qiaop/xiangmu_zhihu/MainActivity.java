package com.example.qiaop.xiangmu_zhihu;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.qiaop.xiangmu_zhihu.beans.Greendaobeans.GreenDaoBiaoshi;
import com.example.qiaop.xiangmu_zhihu.fragment.CollectFragment;
import com.example.qiaop.xiangmu_zhihu.fragment.datafragments.DataFragment;
import com.example.qiaop.xiangmu_zhihu.fragment.GankFragments.GankFragment;
import com.example.qiaop.xiangmu_zhihu.fragment.SettingFragment;
import com.example.qiaop.xiangmu_zhihu.fragment.vtexfragments.VtexFragment;
import com.example.qiaop.xiangmu_zhihu.fragment.WXFragment;
import com.example.qiaop.xiangmu_zhihu.fragment.ZhiHufragments.ZhiHuFragment;
import com.example.qiaop.xiangmu_zhihu.fragment.imfragment.IMFragment;
import com.example.qiaop.xiangmu_zhihu.utils.MyDbBiaoshiUtils;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private MenuItem menuItem;
    private MenuItem searchMenuItem;
    public static MaterialSearchView view_search;
    private Toolbar toolbar;
    private boolean isCache =false;
    private boolean isNoimage =false;
    private boolean isNight =false;
    public static AppCompatDelegate mDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view_search = findViewById(R.id.view_search);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //fragment
        //设置标识存入数据库
        MyDbBiaoshiUtils.getInstance().insert(new GreenDaoBiaoshi(null,isCache,isNoimage, this.isNight));

        NavigationView nav_view = findViewById(R.id.nav_view);
        View headerView = nav_view.getHeaderView(0);
        LinearLayout linear_nav = headerView.findViewById(R.id.linear_nav);

        List<GreenDaoBiaoshi> identification = MyDbBiaoshiUtils.getInstance().identification();
        boolean isNight = identification.get(0).getIsNight();
        if (isNight){
            linear_nav.setBackgroundResource(R.mipmap.bg_drawer);
        }else {
            linear_nav.setBackgroundResource(R.drawable.back);
        }
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*//*
                //Toast.makeText(MainActivity.this, "日历", Toast.LENGTH_SHORT).show();
                //toolbar.setTitle("选择日期");
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_content,new ZhiHuFragment());
        fragmentTransaction.commit();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        menuItem = navigationView.getMenu().findItem(R.id.drawer_zhihu);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_settings);
        //关联toolbar的搜索按钮
        view_search.setMenuItem(item);
        view_search.setVoiceSearch(true);
        /*view_search.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });*/
        searchMenuItem = item;
        searchMenuItem.setVisible(false);
        toolbar.setTitle("知乎日报");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if (id == R.id.drawer_zhihu) {
            toolbar.setTitle("知乎日报");
            fragmentTransaction.replace(R.id.fl_content,new ZhiHuFragment());
            searchMenuItem.setVisible(false);
        } else if (id == R.id.drawer_wechat) {
            toolbar.setTitle("微信精选");
            fragmentTransaction.replace(R.id.fl_content,new WXFragment());
            searchMenuItem.setVisible(true);
        } else if (id == R.id.drawer_gank) {
            toolbar.setTitle("干货集中营");
            fragmentTransaction.replace(R.id.fl_content,new GankFragment());
            searchMenuItem.setVisible(true);
        } else if (id == R.id.drawer_data) {
            toolbar.setTitle("数据智汇");
            fragmentTransaction.replace(R.id.fl_content,new DataFragment());
            searchMenuItem.setVisible(false);
        } else if (id == R.id.drawer_vtex) {
            toolbar.setTitle("V2EX");
            fragmentTransaction.replace(R.id.fl_content,new VtexFragment());
            searchMenuItem.setVisible(false);
        } else if (id == R.id.drawer_like) {
            toolbar.setTitle("收藏");
            fragmentTransaction.replace(R.id.fl_content,new CollectFragment());
            searchMenuItem.setVisible(false);
        }else if (id == R.id.drawer_setting){
            toolbar.setTitle("设置");
            fragmentTransaction.replace(R.id.fl_content,new SettingFragment());
            /*List<GreenDaoBiaoshi> identification = MyDbBiaoshiUtils.getInstance().identification();
            boolean isNight = identification.get(0).getIsNight();*/
            mDelegate = getDelegate();

            searchMenuItem.setVisible(false);
        }else if (id == R.id.drawer_IM){
            toolbar.setTitle("即时通讯");
            fragmentTransaction.replace(R.id.fl_content,new IMFragment());
            searchMenuItem.setVisible(false);
        }
        fragmentTransaction.commit();
        if (menuItem !=null){
            menuItem.setChecked(false);
        }
        item.setChecked(true);
        menuItem = item;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
