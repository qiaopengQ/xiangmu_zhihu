package com.example.qiaop.xiangmu_zhihu.fragment.datafragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.activity.DataActivity;
import com.example.qiaop.xiangmu_zhihu.adapter.DataAdapter.DataFragmentAdapter;
import com.example.qiaop.xiangmu_zhihu.adapter.FragmentAdapter;
import com.example.qiaop.xiangmu_zhihu.base.fragment.BaseFragment;
import com.example.qiaop.xiangmu_zhihu.beans.Categories;
import com.example.qiaop.xiangmu_zhihu.beans.Greendaobeans.Greendaolistbeans;
import com.example.qiaop.xiangmu_zhihu.http.data.DataRetri;
import com.example.qiaop.xiangmu_zhihu.presenter.DataPresenter;
import com.example.qiaop.xiangmu_zhihu.utils.MyDbUtils;
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
    //private boolean isBiaoshi = true;
    private SharedPreferences sp;

    @Override
    public int createLayoutId() {
        return R.layout.fragment_data;
    }

    @Override
    protected void initData() {
        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragments, strings);
        vp.setAdapter(fragmentAdapter);
        tab.setupWithViewPager(vp);
        //tab.setupWithViewPager(vp);
        sp = getActivity().getSharedPreferences("data_biaoshi", Context.MODE_PRIVATE);
      //  SharedPreferences.Editor edit = sp.edit();
      /*  edit.putBoolean("isBiaoshi",isBiaoshi);
        edit.commit();*/
        boolean sp_isBiaoshi = sp.getBoolean("isBiaoshi",false);
        Log.e("sp_isbiaoshi", "sp:" + sp_isBiaoshi);
        if (sp_isBiaoshi){

            Toast.makeText(getContext(), "读取数据库", Toast.LENGTH_SHORT).show();
            List<Greendaolistbeans> select = MyDbUtils.getInstance().select(true);
            Log.e("select", "select:" + select);
            tab.removeAllTabs();
            strings.clear();
            fragments.clear();
            for (int i = 0; i < select.size(); i++) {
                fragments.add(new DataSFragment(select.get(i).getName()));
                strings.add(select.get(i).getName());
            }
            fragmentAdapter.notifyDataSetChanged();
            /*fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragments, strings);
            vp.setAdapter(fragmentAdapter);
            tab.setupWithViewPager(vp);*/

        }else {
            Toast.makeText(getContext(), "网络请求", Toast.LENGTH_SHORT).show();
            presenter.getDataList(null,DataRetri.CATEGORIES);
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), DataActivity.class);
                startActivityForResult(intent,2000);
            }
        });
    }

    @Override
    public DataPresenter<DataView> createPresenter() {
        return new DataPresenter<>();
    }

    @Override
    public void show(String s) {
        Log.e("DataFragment", s);
       // isBiaoshi = false;
        boolean sp_isBiaoshi = sp.getBoolean("isBiaoshi",false);
        sp_isBiaoshi=true;
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("isBiaoshi",sp_isBiaoshi);
        edit.commit();
        Categories categories = new Gson().fromJson(s, Categories.class);
        List<String> result = categories.getRESULT();
        strings.clear();
        fragments.clear();
        tab.removeAllTabs();
        for (int i = 0; i < result.size(); i++) {
            fragments.add(new DataSFragment(result.get(i)));
            strings.addAll(result);
        }
        fragmentAdapter.notifyDataSetChanged();
        for (int i = 0; i < result.size(); i++) {
            MyDbUtils.getInstance().insert(new Greendaolistbeans(null,result.get(i),sp_isBiaoshi));
        }
                //strings.addAll(result);
                //tab.addTab(tab.newTab().setText("数据"));
        /*for (int i = 0; i < result.size(); i++) {
            fragments.add(new DataSFragment(result.get(i)));
        }*/

        /*DataFragmentAdapter dataFragmentAdapter = new DataFragmentAdapter(getChildFragmentManager(), fragments);
        vp.setAdapter(dataFragmentAdapter);*/
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode ==1000){
            List<Greendaolistbeans> select = MyDbUtils.getInstance().select(true);
            Log.e("返回查询数据库", "select:" + select);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < select.size(); i++) {
                list.add(select.get(i).getName());
            }

            tab.removeAllTabs();
            strings.clear();
            fragments.clear();
                for (int i = 0; i < select.size(); i++) {
                    fragments.add(new DataSFragment(select.get(i).getName()));
                    strings.add(select.get(i).getName());
                    Log.e("返回name", select.get(i).getName());
                }
            fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragments,list);
            vp.setAdapter(fragmentAdapter);
            tab.setupWithViewPager(vp);

        }
    }
}
