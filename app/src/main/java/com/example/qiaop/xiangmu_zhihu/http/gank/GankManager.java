package com.example.qiaop.xiangmu_zhihu.http.gank;

import com.example.qiaop.xiangmu_zhihu.http.HttpManager;

public class GankManager {
    private static GankServer gankServer;
    public static GankServer getGankServer(){
        if (gankServer==null){
            synchronized (GankServer.class){
                if (gankServer == null){
                    gankServer = HttpManager.getInstance().getServer(GankServer.GANK,GankServer.class);
                }
            }
        }
        return gankServer;
    }
}
