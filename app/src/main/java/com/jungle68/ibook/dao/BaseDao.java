package com.jungle68.ibook.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jungle68.ibook.base.AppApplication;
import com.jungle68.ibook.bean.DaoMaster;
import com.jungle68.ibook.bean.DaoSession;

/**
 * Created by jungle on 2017/6/15.
 */
public class BaseDao {

    private static final String DB_NAME = "ibook_db";
    private static final UpDBHelper sUpDBHelper = new UpDBHelper(AppApplication.sAppApplication, DB_NAME);

    public BaseDao(Context context) {
    }

    /**
     * 获取可读数据库
     */
    protected SQLiteDatabase getReadableDatabase() {
        return  sUpDBHelper.getReadableDatabase();

    }

    /**
     * 获取可写数据库
     */
    protected SQLiteDatabase getWritableDatabase() {
        return sUpDBHelper.getWritableDatabase();
    }

    /**
     * 获取可写数据库的DaoMaster
     */
    protected DaoMaster getWDaoMaster() {
        return  new DaoMaster(getWritableDatabase());
    }

    /**
     * 获取可写数据库的DaoSession
     */
    protected DaoSession getWDaoSession() {
        return getWDaoMaster().newSession();
    }

    /**
     * 获取可写数据库的DaoMaster
     */
    protected DaoMaster getRDaoMaster() {
        return new DaoMaster(getWritableDatabase());
    }

    /**
     * 获取可写数据库的DaoSession
     */
    protected DaoSession getRDaoSession() {
        return  getRDaoMaster().newSession();
    }
}
