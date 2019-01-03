package com.example.qiaop.xiangmu_zhihu.fragment.datafragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.activity.DataActivity;
import com.example.qiaop.xiangmu_zhihu.adapter.DataAdapter.DataFragmentAdapter;
import com.example.qiaop.xiangmu_zhihu.adapter.FragmentAdapter;
import com.example.qiaop.xiangmu_zhihu.base.fragment.BaseFragment;
import com.example.qiaop.xiangmu_zhihu.beans.Categories;
import com.example.qiaop.xiangmu_zhihu.http.data.DataRetri;
import com.example.qiaop.xiangmu_zhihu.presenter.DataPresenter;
import com.example.qiaop.xiangmu_zhihu.view.DataView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends BaseFragment<DataView, DataPresenter<DataView>> implements DataView<String> {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.vp)
    ViewPager vp;
    Unbinder unbinder;
    private List<String> strings = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentAdapter fragmentAdapter;

    @Override
    public int createLayoutId() {
        return R.layout.fragment_data;
    }

    @Override
    protected void initData() {
        //tab.setupWithViewPager(vp);
        presenter.getDataList(null,DataRetri.CATEGORIES);

    }

    @Override
    public DataPresenter<DataView> createPresenter() {
        return new DataPresenter<>();
    }

    @Override
    public void show(String s) {
        Log.e("DataFragment", s);
                Categories categories = new Gson().fromJson(s, Categories.class);
                final List<String> result = categories.getRESULT();
                //strings.addAll(result);
                //tab.addTab(tab.newTab().setText("数据"));
                for (int i = 0; i < result.size(); i++) {
                    fragments.add(new DataSFragment(result.get(i)));
                }

        /*DataFragmentAdapter dataFragmentAdapter = new DataFragmentAdapter(getChildFragmentManager(), fragments);
        vp.setAdapter(dataFragmentAdapter);*/
                fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragments, result);
                vp.setAdapter(fragmentAdapter);
                tab.setupWithViewPager(vp);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(getContext(), DataActivity.class);
                        intent.putStringArrayListExtra("category", (ArrayList<String>) result);
                        startActivity(intent);
                    }
                });
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
}
