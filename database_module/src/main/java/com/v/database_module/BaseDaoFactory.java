package com.v.database_module;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-07 16:49
 */
public class BaseDaoFactory {
    private static final BaseDaoFactory ourInstance = new BaseDaoFactory();
    private SQLiteDatabase sqLiteDatabase;
    private String sqliteDatabasePath;

    public static BaseDaoFactory getInstance() {
        return ourInstance;
    }

    private BaseDaoFactory() {
        sqliteDatabasePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dongnao.db";
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(sqliteDatabasePath, null);
    }

    public synchronized <T> BaseDao<T> getBaseDao(Class<T> entityClass) {
        BaseDao<T> baseDao = null;

        try {
            baseDao = BaseDao.class.newInstance();

            baseDao.init(entityClass, sqLiteDatabase);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return baseDao;
    }
}
