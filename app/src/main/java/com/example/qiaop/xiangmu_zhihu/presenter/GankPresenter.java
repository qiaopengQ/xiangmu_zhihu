package com.example.qiaop.xiangmu_zhihu.presenter;

import android.util.Log;

import com.example.qiaop.xiangmu_zhihu.base.presenter.IBasePresenter;
import com.example.qiaop.xiangmu_zhihu.http.gank.GankRetri;
import com.example.qiaop.xiangmu_zhihu.model.GankModel;
import com.example.qiaop.xiangmu_zhihu.view.GankView;

public class GankPresenter<V extends GankView> extends IBasePresenter<V> implements GankModel.GankCallback {
    private GankModel model = new GankModel();
    public void getGankList(String tech, String page, GankRetri gankRetri){
        if (mView!=null){
            model.getGankList(this,tech,page,gankRetri);
        }
    }
   /* @Override
    public void setGankList(Object o) {
        if (mView!=null){
            mView.show(o);
            Log.e("o", "o:" + o);
        }
    }*/

    @Override
    public void setShowProgressbar() {

    }

    @Override
    public void setHideProgressbar() {

    }

    @Override
    public void setError(String error) {
        if (mView!=null){
            mView.showError(error);
        }
    }

    @Override
    public void setGankList(String success) {
        Log.e("o", "o:" + success);
        if (mView!=null){
            mView.show(success);

        }
    }
}
