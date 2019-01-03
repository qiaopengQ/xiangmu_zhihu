package com.example.qiaop.xiangmu_zhihu.model;

import android.util.Log;

import com.example.qiaop.xiangmu_zhihu.base.model.HttpFinishCallback;
import com.example.qiaop.xiangmu_zhihu.http.BaseObserver;
import com.example.qiaop.xiangmu_zhihu.http.gank.GankManager;
import com.example.qiaop.xiangmu_zhihu.http.gank.GankRetri;
import com.example.qiaop.xiangmu_zhihu.utils.RxUtils;

public class GankModel {
    public interface GankCallback extends HttpFinishCallback {
        void setGankList(String success);
    }
    public void getGankList(final GankCallback callback, String tech, String page, GankRetri gankRetri){
        callback.setShowProgressbar();
        switch (gankRetri){
            case TECH_ANDROID://基础信息
                GankManager.getGankServer().getTech(tech,page).compose(RxUtils.<String>rxObserbaleSchedulerHelper()).subscribe(new BaseObserver<String>(callback) {
                    @Override
                    public void onNext(String value) {
                        Log.e("Qiaodfdgfhghgp", value);
                        callback.setGankList(value);
                    }
                });
                break;
            case TECH_RANDOM://随机妹子
                GankManager.getGankServer().getRandom().compose(RxUtils.<String>rxObserbaleSchedulerHelper()).subscribe(new BaseObserver<String>(callback) {
                    @Override
                    public void onNext(String value) {
                        Log.e("Qiaodfdgfhghgp", value);
                        callback.setGankList(value);
                    }
                });
                break;
            case TECH_CATEGORY://搜索
                break;
        }

    }
}
