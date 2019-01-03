package com.example.qiaop.xiangmu_zhihu.view;

import com.example.qiaop.xiangmu_zhihu.base.view.BaseView;
import com.example.qiaop.xiangmu_zhihu.http.data.DataRetri;

public interface DataView<T> extends BaseView {
    void show(T t);
}
