package com.jungle68.ibook.dao;

import android.content.Context;
import android.util.Log;

import com.jungle68.ibook.bean.*;

import org.greenrobot.greendao.database.Database;

/**
 * Created by jungle on 2017/6/15.
 */
public class UpDBHelper extends DaoMaster.OpenHelper {

    public UpDBHelper(Context context, String name) {
        super(context, name);
    }

    // 注意选择GreenDao参数的onUpgrade方法
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.i("greenDAO",
                "Upgrading schema from version " + oldVersion + " to " + newVersion + " by migrating all tables data");

        // 每次升级，将需要更新的表进行更新，第二个参数为要升级的Dao文件.
        MigrationHelper.getInstance().migrate(db, com.jungle68.ibook.bean.QuestionDao.class);

    }
}