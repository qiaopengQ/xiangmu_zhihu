package com.example.qiaop.xiangmu_zhihu.fragment.datafragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.adapter.DataAdapter.DataListAdapter;
import com.example.qiaop.xiangmu_zhihu.base.fragment.BaseFragment;
import com.example.qiaop.xiangmu_zhihu.beans.DataListBean;
import com.example.qiaop.xiangmu_zhihu.http.data.DataRetri;
import com.example.qiaop.xiangmu_zhihu.presenter.DataPresenter;
import com.example.qiaop.xiangmu_zhihu.view.DataView;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DataFragments extends BaseFragment<DataView, DataPresenter<DataView>> implements DataView<String> {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    Unbinder unbinder;

    private String mParam1;
    private List<DataListBean.RESULTBean.NewsListBean> news = new ArrayList<>();
    private DataListAdapter dataListAdapter;

    public DataFragments() {
        // Required empty public constructor
    }

    public static DataFragments newInstance(String param1) {
        DataFragments fragment = new DataFragments();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_data_fragments;
    }

    @Override
    protected void initData() {
        Toast.makeText(getContext(), mParam1, Toast.LENGTH_SHORT).show();
        dataListAdapter = new DataListAdapter(news, getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(dataListAdapter);
    }

    @Override
    public void load() {
        super.load();

    }

    @Override
    public DataPresenter<DataView> createPresenter() {
        return new DataPresenter<>();
    }

    @Override
    public void show(String s) {
        Log.e("DataFragments", s);
        DataListBean dataListBean = new Gson().fromJson(s, DataListBean.class);
        List<DataListBean.RESULTBean.NewsListBean> newsList = dataListBean.getRESULT().getNewsList();
        news.addAll(newsList);
        dataListAdapter.notifyDataSetChanged();
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
