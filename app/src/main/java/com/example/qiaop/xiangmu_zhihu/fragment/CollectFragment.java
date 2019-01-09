package com.example.qiaop.xiangmu_zhihu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.activity.TechDetailActivity;
import com.example.qiaop.xiangmu_zhihu.adapter.DataAdapter.CallBack.ItemTouchHelpCallback;
import com.example.qiaop.xiangmu_zhihu.adapter.zhihu.LikeAdapter;
import com.example.qiaop.xiangmu_zhihu.base.fragment.SimpleFragment;
import com.example.qiaop.xiangmu_zhihu.beans.Greendaobeans.GreenDaocollect;
import com.example.qiaop.xiangmu_zhihu.fragment.ZhiHufragments.activity.ZhiHuInfoActivity;
import com.example.qiaop.xiangmu_zhihu.utils.MyDbcollectUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends SimpleFragment {
    @BindView(R.id.rv_like)
    RecyclerView rvLike;
    @BindView(R.id.smart_like)
    SmartRefreshLayout smartLike;
    List<GreenDaocollect> seletlist = new ArrayList<>();
    Unbinder unbinder;
    private LikeAdapter likeAdapter;
    //收藏

    public CollectFragment() {
        // Required empty public constructor
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        likeAdapter = new LikeAdapter(seletlist,getContext());
        rvLike.setLayoutManager(new LinearLayoutManager(getContext()));
        rvLike.setAdapter(likeAdapter);
        ItemTouchHelpCallback itemTouchHelpCallback = new ItemTouchHelpCallback();
        itemTouchHelpCallback.setOnItemTouchCallbackListener(onItemTouchCallbackListener);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelpCallback);
        itemTouchHelper.attachToRecyclerView(rvLike);
        final List<GreenDaocollect> select = MyDbcollectUtils.getInstance().select();
        seletlist.clear();
        seletlist.addAll(select);
        likeAdapter.notifyDataSetChanged();
        likeAdapter.setOnClickListener(new LikeAdapter.OnClickListener() {
            @Override
            public void Click(String url, String image, String title, int id, int position) {
                if (seletlist.get(position).getName().equals("来自知乎")){
                    Intent intent = new Intent(getContext(),ZhiHuInfoActivity.class);
                    intent.putExtra("data",select.get(position).getDataid());
                    startActivity(intent);
                }else if (seletlist.get(position).getName().equals("来自微信精选")){
                    Intent intent = new Intent(getContext(), TechDetailActivity.class);
                    intent.putExtra("url",url);
                    intent.putExtra("name","来自微信精选");
                    intent.putExtra("title",title);
                    intent.putExtra("image",image);
                    startActivity(intent);
                }else if (seletlist.get(position).getName().equals("来自干货集中营_Android")){
                    Intent intent = new Intent(getContext(),TechDetailActivity.class);
                    intent.putExtra("name","来自干货集中营_Android");
                    intent.putExtra("url",url);
                    intent.putExtra("title",title);
                    startActivity(intent);
                }else if (seletlist.get(position).getName().equals("来自干货集中营_IOS")){
                    Intent intent = new Intent(getContext(),TechDetailActivity.class);
                    intent.putExtra("name","来自干货集中营_IOS");
                    intent.putExtra("url",url);
                    intent.putExtra("title",title);
                    startActivity(intent);
                }else if (seletlist.get(position).getName().equals("来自干货集中营_前端")){
                    Intent intent = new Intent(getContext(),TechDetailActivity.class);
                    intent.putExtra("name","来自干货集中营_前端");
                    intent.putExtra("url",url);
                    intent.putExtra("title",title);
                    startActivity(intent);
                }
            }
        });
        Log.e("CollectFragment", "select:" + select);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getDelete(String s){
        if (s.equals("取消收藏")){
            List<GreenDaocollect> select = MyDbcollectUtils.getInstance().select();
            seletlist.clear();
            seletlist.addAll(select);
            likeAdapter.notifyDataSetChanged();
            /*MyDbcollectUtils.getInstance().dele();
            List<GreenDaocollect> select2 = MyDbcollectUtils.getInstance().select();
            seletlist.clear();
            seletlist.addAll(select2);
            likeAdapter.notifyDataSetChanged();*/
        }
    }
    private ItemTouchHelpCallback.OnItemTouchCallbackListener onItemTouchCallbackListener = new ItemTouchHelpCallback.OnItemTouchCallbackListener() {
        @Override
        public void Onswiped(int adapterPosition) {
            if (seletlist !=null){
                seletlist.remove(adapterPosition);
                likeAdapter.notifyItemRemoved(adapterPosition);
            }
        }

        @Override
        public boolean Onmove(int adapterPosition, int adapterPosition1) {
            if (seletlist !=null){
                Log.e("拖拽下标", "adapterPosition+adapterPosition1:" + adapterPosition +"------"+ adapterPosition1);
                Collections.swap(seletlist,adapterPosition,adapterPosition1);
                likeAdapter.notifyItemMoved(adapterPosition,adapterPosition1);
                return true;
            }
            return false;
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
