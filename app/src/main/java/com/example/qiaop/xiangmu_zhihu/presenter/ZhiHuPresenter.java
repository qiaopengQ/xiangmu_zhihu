package com.example.qiaop.xiangmu_zhihu.presenter;

import com.example.qiaop.xiangmu_zhihu.base.presenter.IBasePresenter;
import com.example.qiaop.xiangmu_zhihu.http.zhihu.ZhiHuRetrofit;
import com.example.qiaop.xiangmu_zhihu.model.ZhiHuModel;
import com.example.qiaop.xiangmu_zhihu.view.ZhiHuView;

import java.util.Map;

public class ZhiHuPresenter<V extends ZhiHuView> extends IBasePresenter<V> implements ZhiHuModel.ZhiHuCallback{
    private ZhiHuModel zhiHuModel = new ZhiHuModel();
    public void getDailyListBean(ZhiHuRetrofit zhiHuRetrofit, Map<String,Object> map){
        if (mView !=null){
            zhiHuModel.getDailyListBean(this,zhiHuRetrofit,map);
        }
    }

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
    public void setDailyListBean(Object o, ZhiHuRetrofit zhiHuRetrofit) {
        if (mView !=null){
            mView.show(o,zhiHuRetrofit);
        }
    }
}
