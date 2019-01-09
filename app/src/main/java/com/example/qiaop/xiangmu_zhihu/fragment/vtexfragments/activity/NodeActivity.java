package com.example.qiaop.xiangmu_zhihu.fragment.vtexfragments.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.base.activity.SimpleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NodeActivity extends SimpleActivity {


    @BindView(R.id.toolbar_v2ex)
    Toolbar toolbarV2ex;
    @BindView(R.id.toolbar_container)
    FrameLayout toolbarContainer;
    @BindView(R.id.rv_v2ex)
    RecyclerView rvV2ex;
    @BindView(R.id.tv_node_title)
    TextView tvNodeTitle;

    @Override
    public int createLayoutId() {
        return R.layout.activity_node;
    }

    @Override
    protected void initData() {
        setToolBar(toolbarV2ex,"节点导航");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
