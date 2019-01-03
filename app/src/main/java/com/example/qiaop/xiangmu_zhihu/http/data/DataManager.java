package com.example.qiaop.xiangmu_zhihu.http.data;

import com.example.qiaop.xiangmu_zhihu.http.HttpManager;
import com.example.qiaop.xiangmu_zhihu.http.gank.GankServer;

public class DataManager {
    private static DataMyServer myServer;
    public static DataMyServer getMyServer(){
        if (myServer==null){
            synchronized (GankServer.class){
                if (myServer == null){
                    myServer = HttpManager.getInstance().getServer(DataMyServer.URL,DataMyServer.class);
                }
            }
        }
        return myServer;
    }
}
