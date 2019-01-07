package com.example.qiaop.xiangmu_zhihu.utils;

import com.example.qiaop.xiangmu_zhihu.app.MyApp;
import com.example.qiaop.xiangmu_zhihu.beans.Greendaobeans.GreenDaoBiaoshi;
import com.example.qiaop.xiangmu_zhihu.beans.Greendaobeans.Greendaolistbeans;
import com.example.qiaop.xiangmu_zhihu.dao.DaoMaster;
import com.example.qiaop.xiangmu_zhihu.dao.DaoSession;
import com.example.qiaop.xiangmu_zhihu.dao.GreendaolistbeansDao;

import java.util.List;

public class MyDbUtils {
    private static MyDbUtils myDbUtils;
    private final GreendaolistbeansDao greendaolistbeansDao;

    private MyDbUtils() {
        //数据库初始化
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApp.instance, "bean.db");
        //获取可读写数据库
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        //数据库表管理器
        DaoSession daoSession = daoMaster.newSession();
        //获取当前实体类的操作对象
        greendaolistbeansDao = daoSession.getGreendaolistbeansDao();
    }

    public static MyDbUtils getInstance() {
        if (myDbUtils == null) {
            synchronized (MyDbUtils.class) {
                if (myDbUtils == null) {
                    myDbUtils = new MyDbUtils();
                }
            }
        }

        return myDbUtils;
    }

    /**
     * 插入
     *
     * @param beans
     */
    public void insert(Greendaolistbeans beans) {
        greendaolistbeansDao.insertInTx(beans);
    }

    /**
     * 查询所有数据
     * @return
     */
    public List<Greendaolistbeans> select() {
        return greendaolistbeansDao.queryBuilder().list();
    }
    //修改数据库
    public void update(Greendaolistbeans greendaolistbeans){
        greendaolistbeansDao.update(greendaolistbeans);
    }
    //条件查询数据库
    public  List<Greendaolistbeans> select(boolean isbiaoshi) {
        return greendaolistbeansDao.queryBuilder().where(GreendaolistbeansDao.Properties.IsBiaoshi.eq(isbiaoshi)).list();
    }

}
