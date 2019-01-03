package com.example.qiaop.xiangmu_zhihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.adapter.DataAdapter.CallBack.ItemTouchHelpCallback;
import com.example.qiaop.xiangmu_zhihu.adapter.DataAdapter.DataAdapter;
import com.example.qiaop.xiangmu_zhihu.adapter.DataAdapter.DefaultItemTouchHelpCallback;
import com.example.qiaop.xiangmu_zhihu.adapter.DataAdapter.DefaultItemTouchHelper;
import com.example.qiaop.xiangmu_zhihu.base.activity.SimpleActivity;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataActivity extends SimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_container)
    FrameLayout toolbarContainer;
    @BindView(R.id.rv)
    RecyclerView rv;
    private ArrayList<String> category;
    private DataAdapter dataAdapter;

    @Override
    protected void initData() {
        setToolBar(toolbar, "首页特别展示");
        Intent intent = getIntent();
        category = intent.getStringArrayListExtra("category");
        Log.e("DataActivity", "category:" + category);
        dataAdapter = new DataAdapter(category,this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelpCallback itemTouchHelpCallback = new ItemTouchHelpCallback();
        itemTouchHelpCallback.setOnItemTouchCallbackListener(onItemTouchCallbackListener);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelpCallback);
        itemTouchHelper.attachToRecyclerView(rv);

        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(dataAdapter);

    }
    private ItemTouchHelpCallback.OnItemTouchCallbackListener onItemTouchCallbackListener = new ItemTouchHelpCallback.OnItemTouchCallbackListener() {
        @Override
        public void Onswiped(int adapterPosition) {
                if (category !=null){
                    category.remove(adapterPosition);
                    dataAdapter.notifyItemRemoved(adapterPosition);
                }
        }

        @Override
        public boolean Onmove(int adapterPosition, int adapterPosition1) {
            if (category !=null){
                Collections.swap(category,adapterPosition,adapterPosition1);
                dataAdapter.notifyItemMoved(adapterPosition,adapterPosition1);
                return true;
            }
            return false;
        }
    };
        @Override
    public void onBackPressed() {
        //Toolbar回退按钮
        super.onBackPressed();
        Toast.makeText(this, "回退按钮", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int createLayoutId() {
        return R.layout.activity_data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
