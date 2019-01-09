package com.example.qiaop.xiangmu_zhihu.utils;

import com.example.qiaop.xiangmu_zhihu.app.MyApp;
import com.example.qiaop.xiangmu_zhihu.beans.Greendaobeans.GreenDaocollect;
import com.example.qiaop.xiangmu_zhihu.beans.Greendaobeans.Greendaolistbeans;
import com.example.qiaop.xiangmu_zhihu.dao.DaoMaster;
import com.example.qiaop.xiangmu_zhihu.dao.DaoSession;
import com.example.qiaop.xiangmu_zhihu.dao.GreenDaocollectDao;
import com.example.qiaop.xiangmu_zhihu.dao.GreendaolistbeansDao;

import java.util.List;

public class MyDbcollectUtils {
    private static MyDbcollectUtils myDbcollectUtils;
    private final GreenDaocollectDao greenDaocollectDao;

    private MyDbcollectUtils() {
        //数据库初始化
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApp.instance, "collect.db");
        //获取可读写数据库
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        //数据库表管理器
        DaoSession daoSession = daoMaster.newSession();
        //获取当前实体类的操作对象
        greenDaocollectDao = daoSession.getGreenDaocollectDao();
    }

    public static MyDbcollectUtils getInstance() {
        if (myDbcollectUtils == null) {
            synchronized (MyDbUtils.class) {
                if (myDbcollectUtils == null) {
                    myDbcollectUtils = new MyDbcollectUtils();
                }
            }
        }
        return myDbcollectUtils;
    }

    /**
     * 插入
     * @param
     */
    public void insert(GreenDaocollect greenDaocollect) {
        greenDaocollectDao.insertInTx(greenDaocollect);
    }

    /**
     * 查询所有数据
     * @return
     */
    public List<GreenDaocollect> select() {
        return greenDaocollectDao.queryBuilder().list();
    }

    //条件查询数据库
    public  List<GreenDaocollect> select(int id) {
        return greenDaocollectDao.queryBuilder().where(GreenDaocollectDao.Properties.Dataid.eq(id)).list();
    }

    //条件删除
    public void delete(GreenDaocollect greenDaocollect){
        greenDaocollectDao.delete(greenDaocollect);
    }
    //条件查询数据库
    public  List<GreenDaocollect> selecttitle(String title) {
        return greenDaocollectDao.queryBuilder().where(GreenDaocollectDao.Properties.Title.eq(title)).list();
    }
}
