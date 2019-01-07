package com.example.qiaop.xiangmu_zhihu.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.base.activity.SimpleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GankflActivity extends SimpleActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_container)
    FrameLayout toolbarContainer;
    @BindView(R.id.img_gankfl)
    ImageView imgGankfl;
    boolean isLiked =true;
    private MenuItem item;

    @Override
    protected void initData() {
        setToolBar(toolbar,"");
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        //Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        Glide.with(this).load(url).into(imgGankfl);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.girl_menu,menu);
        item = menu.findItem(R.id.action_like);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_like:
                if(isLiked) {
                    item.setIcon(R.mipmap.ic_toolbar_like_p);
                    isLiked = false;
                } else {
                    item.setIcon(R.mipmap.ic_toolbar_like_n);
                    isLiked = true;
                }
                break;
            case R.id.action_save:
                Toast.makeText(this, "分享图片", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_share:
                Toast.makeText(this, "保存到本地", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public int createLayoutId() {
        return R.layout.activity_gankfl;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
