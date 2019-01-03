package com.example.qiaop.xiangmu_zhihu.http.data;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface DataMyServer {
    //http://api.shujuzhihui.cn/api/news/categories?appKey=7da0648ea9a84f32bc1649f26d2db42e
    String URL = "http://api.shujuzhihui.cn/api/news/";

    //标题
    @GET("categories?appKey=7da0648ea9a84f32bc1649f26d2db42e")
    Observable<String> getCategories();

    //内容   http://api.shujuzhihui.cn/api/news/list?appKey=7da0648ea9a84f32bc1649f26d2db42e&category=%E5%A8%B1%E4%B9%90
    @GET("list?")
    Observable<String> getList(@QueryMap Map<String,Object> map);

    //详情      http://api.shujuzhihui.cn/api/news/detail?appKey=7da0648ea9a84f32bc1649f26d2db42e&newsId=fb6944064486ccbe7216fb5ce9773c8c
    @GET("detail?")
    Observable<String> getDetail(@QueryMap Map<String,Object> map);
}
