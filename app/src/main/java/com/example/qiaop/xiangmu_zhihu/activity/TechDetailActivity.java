package com.example.qiaop.xiangmu_zhihu.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.app.MyApp;
import com.example.qiaop.xiangmu_zhihu.base.activity.SimpleActivity;
import com.example.qiaop.xiangmu_zhihu.beans.Greendaobeans.GreenDaocollect;
import com.example.qiaop.xiangmu_zhihu.utils.MyDbcollectUtils;
import com.google.gson.internal.bind.util.ISO8601Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TechDetailActivity extends SimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_container)
    FrameLayout toolbarContainer;
    @BindView(R.id.wv_tech_content)
    WebView wvTechContent;
    private MenuItem item;
    boolean isLiked =true;
    private String url;
    private String title;
    private String image;
    private String name;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        image = intent.getStringExtra("image");
        name = intent.getStringExtra("name");
        if (title !=null){
            setToolBar(toolbar, title);
        }
        wvTechContent.setWebViewClient(new WebViewClient());
        wvTechContent.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        wvTechContent.loadUrl(url);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tech_meun, menu);
        item = menu.findItem(R.id.action_like);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.action_like:
                if(isLiked) {
                    item.setIcon(R.mipmap.ic_toolbar_like_p);
                    Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                    if (name.equals("微信精选")){
                        MyDbcollectUtils.getInstance().insert(new GreenDaocollect(null,title,url,image,"来自微信精选",1,true));
                    }else if (name.equals("来自干货集中营_Android")){
                        MyDbcollectUtils.getInstance().insert(new GreenDaocollect(null,title,url,null,"来自干货集中营_Android",1,true));
                    }else if (name.equals("来自干货集中营_IOS")){
                        MyDbcollectUtils.getInstance().insert(new GreenDaocollect(null,title,url,null,"来自干货集中营_IOS",1,true));
                    }else if (name.equals("来自干货集中营_前端")){
                        MyDbcollectUtils.getInstance().insert(new GreenDaocollect(null,title,url,null,"来自干货集中营_前端",1,true));
                    }

                    isLiked = false;
                } else {
                    item.setIcon(R.mipmap.ic_toolbar_like_n);
                    isLiked = true;
                    Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.action_copy:
                if (url!=null){
                    copyToClipboard(this,url);
                    Toast.makeText(this, "已复制到剪切板", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.action_share:
                Toast.makeText(this, "分享一篇文章", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public static void copyToClipboard(Context context, String text) {
        ClipboardManager systemService = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        systemService.setPrimaryClip(ClipData.newPlainText("text", text));
    }
    /*private void setLikeState(boolean state) {

    }*/

    @Override
    public int createLayoutId() {
        return R.layout.activity_tech_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
