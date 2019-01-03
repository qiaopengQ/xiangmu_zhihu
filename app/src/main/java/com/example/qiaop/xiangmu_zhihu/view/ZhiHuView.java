package com.example.qiaop.xiangmu_zhihu.view;

import com.example.qiaop.xiangmu_zhihu.base.view.BaseView;
import com.example.qiaop.xiangmu_zhihu.http.zhihu.ZhiHuRetrofit;

public interface ZhiHuView<T> extends BaseView {
    void show(T t, ZhiHuRetrofit zhiHuRetrofit);
}
