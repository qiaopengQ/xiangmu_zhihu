package com.example.qiaop.xiangmu_zhihu.model;

import com.example.qiaop.xiangmu_zhihu.base.model.HttpFinishCallback;
import com.example.qiaop.xiangmu_zhihu.http.BaseObserver;
import com.example.qiaop.xiangmu_zhihu.http.wechar.WecharManager;
import com.example.qiaop.xiangmu_zhihu.utils.RxUtils;

import java.util.Map;

public class WecharModel {
    public interface WecharCallback<T> extends HttpFinishCallback {
        void setWecharList(T t);
    }
    public void getWecharList(final WecharCallback wecharCallback, Map<String,Object> map){
        wecharCallback.setShowProgressbar();
                WecharManager.getWxServer().getwxnew(map).compose(RxUtils.<String>rxObserbaleSchedulerHelper()).subscribe(new BaseObserver<String>(wecharCallback) {
                    @Override
                    public void onNext(String value) {
                        //Log.e("liangxq",value.toString());
                        //zhiHuCallback.setDailyListBean(value);
                        //Log.e("Qiaop", value);
                        wecharCallback.setWecharList(value);
                    }
                });
    }
}
