package com.example.qiaop.xiangmu_zhihu.fragment.ZhiHufragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.adapter.zhihu.ZhiHuAdapter;
import com.example.qiaop.xiangmu_zhihu.base.fragment.BaseFragment;
import com.example.qiaop.xiangmu_zhihu.beans.SectionListBean;
import com.example.qiaop.xiangmu_zhihu.fragment.ZhiHufragments.activity.ZhiHuInfoActivity;
import com.example.qiaop.xiangmu_zhihu.fragment.ZhiHufragments.activity.ZhiHuInfoCActivity;
import com.example.qiaop.xiangmu_zhihu.http.zhihu.ZhiHuRetrofit;
import com.example.qiaop.xiangmu_zhihu.presenter.ZhiHuPresenter;
import com.example.qiaop.xiangmu_zhihu.view.ZhiHuView;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhiHu_CFragment extends BaseFragment<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView<String>,ZhiHuAdapter.ClickListener {

    Unbinder unbinder;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    Unbinder unbinder1;
    private List<SectionListBean.DataBean> sectionList = new ArrayList<>();
    private ZhiHuAdapter zhiHuAdapter;

    @Override
    public int createLayoutId() {
        return R.layout.fragment_zhi_hu__c;
    }

    @Override
    public void load() {
        super.load();
        presenter.getDailyListBean(ZhiHuRetrofit.SECTIONS, null);
    }

    @Override
    protected void initData() {
        zhiHuAdapter = new ZhiHuAdapter(sectionList, getContext());
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(staggeredGridLayoutManager);
        rv.setAdapter(zhiHuAdapter);
        zhiHuAdapter.setClickListener(this);
    }


    @Override
    public ZhiHuPresenter<ZhiHuView> createPresenter() {
        return new ZhiHuPresenter<>();
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
    public void show(String s, ZhiHuRetrofit zhiHuRetrofit) {
        Log.e("ZhiHu_CFragment", s);
        Gson gson = new Gson();
        final SectionListBean sectionListBean = gson.fromJson(s, SectionListBean.class);
        final List<SectionListBean.DataBean> data = sectionListBean.getData();
        sectionList.addAll(data);
        zhiHuAdapter.notifyDataSetChanged();
        smart.setEnableLoadMore(false);
        smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                sectionList.clear();
                presenter.getDailyListBean(ZhiHuRetrofit.SECTIONS, null);
                sectionList.addAll(data);
                zhiHuAdapter.notifyDataSetChanged();
                smart.finishRefresh();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void Click(String title,int id, int position) {
        Intent intent = new Intent(getContext(),ZhiHuInfoCActivity.class);
        intent.putExtra("id",sectionList.get(position).getId());
        intent.putExtra("name",sectionList.get(position).getName());
        startActivity(intent);
    }
}
