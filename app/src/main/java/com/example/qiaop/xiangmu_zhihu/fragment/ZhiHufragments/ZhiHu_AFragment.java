package com.example.qiaop.xiangmu_zhihu.fragment.ZhiHufragments;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.activity.ZhiHuActivity;
import com.example.qiaop.xiangmu_zhihu.adapter.zhihu.RecycleAdapter;
import com.example.qiaop.xiangmu_zhihu.base.fragment.BaseFragment;
import com.example.qiaop.xiangmu_zhihu.beans.DailyListBean;
import com.example.qiaop.xiangmu_zhihu.fragment.ZhiHufragments.activity.ZhiHuInfoActivity;
import com.example.qiaop.xiangmu_zhihu.http.zhihu.ZhiHuRetrofit;
import com.example.qiaop.xiangmu_zhihu.presenter.ZhiHuPresenter;
import com.example.qiaop.xiangmu_zhihu.utils.CircularAnimUtil;
import com.example.qiaop.xiangmu_zhihu.view.ZhiHuView;
import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhiHu_AFragment extends BaseFragment<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView<String> {


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    @BindView(R.id.fab_calender)
    FloatingActionButton fabCalender;
    Unbinder unbinder;
    @BindView(R.id.tv)
    TextView tv;
    private List<DailyListBean.StoriesBean> data = new ArrayList<>();
    private List<DailyListBean.TopStoriesBean> beforedata = new ArrayList<>();
    private RecycleAdapter recycleAdapter;

    @Override
    public int createLayoutId() {
        return R.layout.fragment_zhi_hu__a;
    }

    @Override
    public void load() {
        super.load();

    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        presenter.getDailyListBean(ZhiHuRetrofit.LATEST, null);
        recycleAdapter = new RecycleAdapter(data,beforedata, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        rv.setAdapter(recycleAdapter);

    }

    @Subscribe(threadMode = ThreadMode.MAIN ,sticky = true)
    public void getDate(CalendarDay data) {
        Log.e("zzzzzzzzzz", "data:" + data);
        int day = data.getDay()+1;//天
        int month = data.getMonth() + 1;//月
        int year = data.getYear();//年
        String date = null;
        HashMap<String, Object> map = new HashMap<>();
        Log.e("calend", day + month + "" + year);
        if (day < 10 && month <10) {
            date = year + "" +"0"+ month + "" + "0" + day;
            map.put("date", date);
            Log.e("aaaaaaa", date);
        } else if (day < 10){
            date = year + "" + month + "0" + day;
            map.clear();
            map.put("date", date);
        }else {
            date = year + "" + month + "" + day;
            map.clear();
            map.put("date", date);
        }
        Log.e("qwer", "data:" + map);
        presenter.getDailyListBean(ZhiHuRetrofit.BEFORE, map);
        //Toast.makeText(getContext(), "data:" + date, Toast.LENGTH_SHORT).show();
        date = null;
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
    public ZhiHuPresenter<ZhiHuView> createPresenter() {
        return new ZhiHuPresenter<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void show(String o, ZhiHuRetrofit zhiHuRetrofit) {
        Log.e("ZhiHu_AFragment", o);
        Gson gson = new Gson();
        DailyListBean dailyListBean = gson.fromJson(o, DailyListBean.class);
        switch (zhiHuRetrofit){
            case LATEST://最新
                List<DailyListBean.StoriesBean> stories = dailyListBean.getStories();
                List<DailyListBean.TopStoriesBean> top_stories = dailyListBean.getTop_stories();
                data.addAll(stories);
                beforedata.addAll(top_stories);
                recycleAdapter.addDailyList(dailyListBean);
                break;
            case BEFORE:
                List<DailyListBean.StoriesBean> stories2 = dailyListBean.getStories();
                List<DailyListBean.TopStoriesBean> top_stories2 = dailyListBean.getTop_stories();
                Log.e("ZhiHu_AFragment", "top_stories2:" + top_stories2);
                data.clear();
                beforedata.clear();
                data.addAll(stories2);
                beforedata.addAll(top_stories2);
                recycleAdapter.addDailyList(dailyListBean);
                break;
        }
        //smart.setEnableLoadMore(false);
        /*smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                smart.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                *//*data.clear();
                data.addAll(stories);*//*
                //recycleAdapter.notifyDataSetChanged();
                //presenter.getDailyListBean(ZhiHuRetrofit.LATEST, null);
                smart.finishRefresh();
            }
        });*/
        recycleAdapter.setOnclickLienet(new RecycleAdapter.OnclickLienet() {
            @Override
            public void Click(String ga_prefix, int position) {
                Intent intent = new Intent(getContext(),ZhiHuInfoActivity.class);
                Log.e("点击条目", "data.get(position).getId():" + data.get(position).getId());
                intent.putExtra("data",data.get(position).getId());
                //intent.setClass(getActivity(),ZhiHuInfoActivity.class);
                //EventBus.getDefault().postSticky(data.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.fab_calender)
    public void onViewClicked() {
        //copyToClipboard(getContext(),"qqq");
        Intent intent = new Intent();
        intent.setClass(getContext(), ZhiHuActivity.class);
        CircularAnimUtil.startActivity(getActivity(), intent, fabCalender, R.color.fab_bg);
    }
    /*public static void copyToClipboard(Context context, String text) {
        ClipboardManager systemService = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        systemService.setPrimaryClip(ClipData.newPlainText("text", text));
    }*/
}
