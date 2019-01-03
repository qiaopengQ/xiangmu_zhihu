package com.example.qiaop.xiangmu_zhihu.http.wechar;


import com.example.qiaop.xiangmu_zhihu.http.HttpManager;

import java.util.Map;

import io.reactivex.Observable;

public class WecharManager {
    private static WXServer wxServer;
    public static WXServer getWxServer(){
        if (wxServer == null){
            synchronized (WXServer.class){
                if (wxServer == null){
                    wxServer = HttpManager.getInstance().getServer(WXServer.URL,WXServer.class);
                }
            }
        }
        return wxServer;
    }
    public Observable<String> getWechar(Map<String,Object> map){
        return wxServer.getwxnew(map);
    }
}
