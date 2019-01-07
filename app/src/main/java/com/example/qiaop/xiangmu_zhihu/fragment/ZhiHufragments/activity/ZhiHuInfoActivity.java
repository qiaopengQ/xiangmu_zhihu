package com.example.qiaop.xiangmu_zhihu.fragment.ZhiHufragments.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.base.activity.BaseActivity;
import com.example.qiaop.xiangmu_zhihu.beans.Greendaobeans.GreenDaocollect;
import com.example.qiaop.xiangmu_zhihu.beans.ZhihuDetailBean;
import com.example.qiaop.xiangmu_zhihu.http.zhihu.ZhiHuRetrofit;
import com.example.qiaop.xiangmu_zhihu.presenter.ZhiHuPresenter;
import com.example.qiaop.xiangmu_zhihu.utils.MyDbcollectUtils;
import com.example.qiaop.xiangmu_zhihu.view.ZhiHuView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.URL;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhiHuInfoActivity extends BaseActivity<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView<String> {


    @BindView(R.id.detail_bar_image)
    ImageView detailBarImage;
    @BindView(R.id.toolbar_datainfo)
    Toolbar toolbarDatainfo;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    /*@BindView(R.id.web_spanned)
    WebView web_spanned;*/
    @BindView(R.id.nsv_scroller)
    NestedScrollView nsvScroller;
    @BindView(R.id.tv_detail_bottom_like)
    TextView tvDetailBottomLike;
    @BindView(R.id.tv_detail_bottom_comment)
    TextView tvDetailBottomComment;
    @BindView(R.id.tv_detail_bottom_share)
    TextView tvDetailBottomShare;
    @BindView(R.id.ll_detail_bottom)
    FrameLayout llDetailBottom;
    @BindView(R.id.fab_like)
    FloatingActionButton fabLike;
    @BindView(R.id.tv_spanned)
    TextView tvSpanned;
    private boolean isBottonShow = true;
    String mimeType = "text/html";
    String enCoding = "utf-8";
    private String title;
    private String image;
    private int data;

    @Override
    protected void initData() {

        Intent intent = getIntent();
        data = intent.getIntExtra("data", 0);
        //Log.e("ZhiHuInfoActivity", newsid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("newsid", data);
        presenter.getDailyListBean(ZhiHuRetrofit.NEWSIDINFO, map);
        List<GreenDaocollect> select = MyDbcollectUtils.getInstance().select(data);
        for (int i = 0; i < select.size(); i++) {
            boolean isCollect = select.get(i).getIsCollect();
            fabLike.setSelected(isCollect);
        }
//        setSupportActionBar(toolbar_datainfo);
        //  EventBus.getDefault().register(this);
        FloatingActionButton fab_like = (FloatingActionButton) findViewById(R.id.fab_like);
        fab_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                if (fabLike.isSelected()) {
                    fabLike.setSelected(false);
                    Toast.makeText(ZhiHuInfoActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    //mPresenter.deleteLikeData();
                } else {
                    fabLike.setSelected(true);
                    MyDbcollectUtils.getInstance().insert(new GreenDaocollect(null,title,data+"",image,"来自知乎",data,true));
                    Toast.makeText(ZhiHuInfoActivity.this, "收藏", Toast.LENGTH_SHORT).show();
                    //mPresenter.insertLikeData();
                }
            }
        });
        nsvScroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrol1X, int scrol1Y, int oldScrol1X, int oldScrol1Y) {
                Log.e("liangxq", "滑动了！");
                if (scrol1Y - oldScrol1Y > 0 && isBottonShow) {
                    isBottonShow = false;//下标隐藏
                    llDetailBottom.animate().translationY(llDetailBottom.getHeight());
                    Log.e("liangxq", "上拉");
                } else if (scrol1Y - oldScrol1Y < 0 && !isBottonShow) {
                    isBottonShow = true;
                    llDetailBottom.animate().translationY(0);
                    Log.e("liangxq", "下啦");
                }
            }
        });

    }

    /*@Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getListBean(String newsid) {
        Log.e("ZhiHuInfoActivity", "list:" + newsid);
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("liangxq", "回退！！");
    }

    @Override
    public int createLayoutId() {
        return R.layout.activity_scrolling;
    }


    @Override
    protected ZhiHuPresenter<ZhiHuView> createPresenter() {
        return new ZhiHuPresenter<>();
    }

    @Override
    public void show(String s, ZhiHuRetrofit zhiHuRetrofit) {
        switch (zhiHuRetrofit) {
            case NEWSIDINFO:
                Log.e("qwer", s);
                ZhihuDetailBean zhihuDetailBean = new Gson().fromJson(s, ZhihuDetailBean.class);
                String body = zhihuDetailBean.getBody();
                title = zhihuDetailBean.getTitle();
                image = zhihuDetailBean.getImage();
                setToolBar(toolbarDatainfo, title);
                Glide.with(this).load(image).into(detailBarImage);
                //web_spanned.loadDataWithBaseURL(null, body, mimeType, enCoding, null);
                getHttp(zhihuDetailBean);
                break;
        }
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

    public void getHttp(final ZhihuDetailBean zhihuDetailBean) {
        //耗时操作 所以搞个Thread出来
        new Thread() {
            @Override
            public void run() {
                Html.ImageGetter imageGetter = new Html.ImageGetter() {
                    @Override
                    public Drawable getDrawable(String source) {
                        //source为 图片的url  注意:图片不能太大 否则也会加载不出来
                        Drawable drawable = null;
                        URL url;
                        try {
                            url = new URL(source);
                            drawable = Drawable.createFromStream(url.openStream(), "jpg");
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                        //图片设置大小宽度 间距
                        drawable.setBounds(0, 0, 200, 200);
                        return drawable;
                    }
                };
                //调用方法 Html 调用 放入图片加载器	//此参数为对象内需解析的html数据
                final CharSequence spanned = Html.fromHtml(zhihuDetailBean.getBody(), imageGetter, null);
                //切换线程修改
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //toolbarDatainfo.setTitle(spanned);
                        if (spanned!=null){
                            tvSpanned.setText(spanned);
                        }

                        //Log.e("spanned", "spanned:" + spanned);
                    }
                });
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
