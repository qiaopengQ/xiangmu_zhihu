package com.example.qiaop.xiangmu_zhihu.presenter;

import com.example.qiaop.xiangmu_zhihu.base.presenter.IBasePresenter;
import com.example.qiaop.xiangmu_zhihu.model.WecharModel;
import com.example.qiaop.xiangmu_zhihu.view.WecharView;

import java.util.Map;

public class WecharPresenter<V extends WecharView> extends IBasePresenter<V> implements WecharModel.WecharCallback{
    private WecharModel model = new WecharModel();
    public void getWecharList(Map<String,Object> map){
        if (mView !=null){
            model.getWecharList(this,map);
        }
    }
    @Override
    public void setWecharList(Object o) {
        if (mView!=null){

            mView.show(o);
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
        if (mView !=null){
            mView.showError(error);
        }
    }
}
