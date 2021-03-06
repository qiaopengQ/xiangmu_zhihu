package com.example.qiaop.xiangmu_zhihu.fragment.GankFragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.activity.GankflActivity;
import com.example.qiaop.xiangmu_zhihu.activity.TechDetailActivity;
import com.example.qiaop.xiangmu_zhihu.adapter.GirlAdapter;
import com.example.qiaop.xiangmu_zhihu.base.fragment.BaseFragment;
import com.example.qiaop.xiangmu_zhihu.beans.GankListBean;
import com.example.qiaop.xiangmu_zhihu.http.gank.GankRetri;
import com.example.qiaop.xiangmu_zhihu.presenter.GankPresenter;
import com.example.qiaop.xiangmu_zhihu.view.GankView;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class Gank_DFragment extends BaseFragment<GankView, GankPresenter<GankView>> implements GankView<String> {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    Unbinder unbinder;
    private String tab;
    private List<GankListBean.ResultsBean> gank = new ArrayList<>();
    private GirlAdapter girlAdapter;

    public Gank_DFragment() {
    }

    public Gank_DFragment(String tech) {
        tab = tech;
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_gank__d;
    }

    @Override
    public void load() {
        super.load();
        presenter.getGankList(tab, "1", GankRetri.TECH_ANDROID);
    }

    @Override
    protected void initData() {
        girlAdapter = new GirlAdapter(gank, getContext());
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(staggeredGridLayoutManager);
        rv.setAdapter(girlAdapter);
        girlAdapter.setOnClickList(new GirlAdapter.OnClickList() {
            @Override
            public void Click(String url) {
                Intent intent = new Intent(getContext(),GankflActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
    }

    @Override
    public GankPresenter<GankView> createPresenter() {
        return new GankPresenter<>();
    }

    @Override
    public void show(String s) {
        Log.e("Gank_DFragment", s);
        GankListBean gankListBean = new Gson().fromJson(s, GankListBean.class);
        List<GankListBean.ResultsBean> results = gankListBean.getResults();
        gank.addAll(results);
        girlAdapter.notifyDataSetChanged();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
