package com.example.qiaop.xiangmu_zhihu.model;

import com.example.qiaop.xiangmu_zhihu.base.model.HttpFinishCallback;
import com.example.qiaop.xiangmu_zhihu.http.BaseObserver;
import com.example.qiaop.xiangmu_zhihu.http.data.DataManager;
import com.example.qiaop.xiangmu_zhihu.http.data.DataRetri;
import com.example.qiaop.xiangmu_zhihu.utils.RxUtils;

import java.util.Map;

public class DataModel {
    public interface DataCallback extends HttpFinishCallback{
        void setData(String s);
    }
    public void getDataList(final DataCallback dataCallback, DataRetri dataRetri, Map<String,Object> map){
        switch (dataRetri){
            case CATEGORIES:
                DataManager.getMyServer().getCategories().compose(RxUtils.<String>rxObserbaleSchedulerHelper()).subscribe(new BaseObserver<String>(dataCallback) {
                    @Override
                    public void onNext(String value) {
                        dataCallback.setData(value);
                    }
                });
                break;
            case LIST:
                //Object o = map.get("");
                DataManager.getMyServer().getList(map).compose(RxUtils.<String>rxObserbaleSchedulerHelper()).subscribe(new BaseObserver<String>(dataCallback) {
                    @Override
                    public void onNext(String value) {
                        dataCallback.setData(value);
                    }
                });
                break;
            case DETAIL:
                DataManager.getMyServer().getDetail(map).compose(RxUtils.<String>rxObserbaleSchedulerHelper()).subscribe(new BaseObserver<String>(dataCallback) {
                    @Override
                    public void onNext(String value) {
                        dataCallback.setData(value);
                    }
                });
                break;
        }
    }
}
