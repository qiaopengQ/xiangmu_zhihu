package com.example.qiaop.xiangmu_zhihu.fragment.datafragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class DataSFragment extends BaseFragment<DataView, DataPresenter<DataView>> implements DataView<String> {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    Unbinder unbinder;
    private String category = null;
    private List<DataListBean.RESULTBean.NewsListBean> news = new ArrayList<>();
    private DataListAdapter dataListAdapter;

    public DataSFragment(String s) {
        if (s !=null){
            category = s;
        }

        //Toast.makeText(getContext(), category, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_data2;
    }

    @Override
    public void load() {
        super.load();
        HashMap<String, Object> map = new HashMap<>();
        map.put("appKey", "7da0648ea9a84f32bc1649f26d2db42e");
        map.put("category", category);
        Log.e("categoryfragment", category);
        presenter.getDataList(map, DataRetri.LIST);
    }

    @Override
    protected void initData() {
        dataListAdapter = new DataListAdapter(news, getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(dataListAdapter);
    }

    @Override
    public DataPresenter<DataView> createPresenter() {
        return new DataPresenter<>();
    }

    @Override
    public void show(String s) {
        Log.e("DataSFragment", s);
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
