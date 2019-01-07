package com.example.qiaop.xiangmu_zhihu.fragment.ZhiHufragments.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.adapter.zhihu.ZhiHuCAdapter;
import com.example.qiaop.xiangmu_zhihu.base.activity.BaseActivity;
import com.example.qiaop.xiangmu_zhihu.beans.Sectionsinfolist;
import com.example.qiaop.xiangmu_zhihu.http.zhihu.ZhiHuRetrofit;
import com.example.qiaop.xiangmu_zhihu.presenter.ZhiHuPresenter;
import com.example.qiaop.xiangmu_zhihu.view.ZhiHuView;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhiHuInfoCActivity extends BaseActivity<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView<String>,ZhiHuCAdapter.ClickList {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_container)
    FrameLayout toolbarContainer;
    HashMap<String, Object> map = new HashMap<>();
    @BindView(R.id.rv_zhihuc)
    RecyclerView rv_zhihuc;
    @BindView(R.id.smart_zhihuc)
    SmartRefreshLayout smart_zhihuc;
    private List<Sectionsinfolist.StoriesBean> storieslist = new ArrayList<>();
    private ZhiHuCAdapter zhiHuCAdapter;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        Log.e("ZhiHuInfoCActivity", id + name);
        setToolBar(toolbar, name);
        Log.e("qqqqqqqqqqqqqqq", "storieslist:" + storieslist);
        zhiHuCAdapter = new ZhiHuCAdapter(storieslist, this);
        rv_zhihuc.setLayoutManager(new LinearLayoutManager(this));
        rv_zhihuc.setAdapter(zhiHuCAdapter);
        zhiHuCAdapter.setClickList(this);
        map.clear();
        map.put("id", id);
        presenter.getDailyListBean(ZhiHuRetrofit.SECTIONSINFO, map);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public int createLayoutId() {
        return R.layout.activity_zhi_hu_info_c;
    }

    @Override
    protected ZhiHuPresenter<ZhiHuView> createPresenter() {
        return new ZhiHuPresenter<>();
    }

    @Override
    public void show(String s, ZhiHuRetrofit zhiHuRetrofit) {
        Log.e("qweqweqweqweqeqweqw", s);
        Sectionsinfolist sectionsinfolist = new Gson().fromJson(s, Sectionsinfolist.class);
        List<Sectionsinfolist.StoriesBean> stories = sectionsinfolist.getStories();
        Log.d("sssssssssssssssss", "stories:" + stories);
        storieslist.addAll(stories);
        zhiHuCAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void HideProgressbar() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void Click(int id, int position) {
        Intent intent = new Intent(this, ZhiHuInfoActivity.class);
        intent.putExtra("data",storieslist.get(position).getId());
        startActivity(intent);
    }
}
