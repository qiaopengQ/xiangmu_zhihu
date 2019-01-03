package com.example.qiaop.xiangmu_zhihu.presenter;

import com.example.qiaop.xiangmu_zhihu.base.presenter.IBasePresenter;
import com.example.qiaop.xiangmu_zhihu.http.data.DataManager;
import com.example.qiaop.xiangmu_zhihu.http.data.DataRetri;
import com.example.qiaop.xiangmu_zhihu.model.DataModel;
import com.example.qiaop.xiangmu_zhihu.view.DataView;

import java.util.Map;

public class DataPresenter<V extends DataView> extends IBasePresenter<V> implements DataModel.DataCallback {
    private DataModel model = new DataModel();
    public void getDataList(Map<String,Object> map, DataRetri dataRetri){
        if (mView!=null){
            model.getDataList(this,dataRetri,map);
        }
    }
    @Override
    public void setData(String s) {
        if (mView!=null){
            mView.show(s);
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
}
