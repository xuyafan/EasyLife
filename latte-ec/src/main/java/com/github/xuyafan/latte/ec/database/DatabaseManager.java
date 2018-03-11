package com.github.xuyafan.latte.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * author： xuyafan
 * description:
 */

public class DatabaseManager {

    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private DatabaseManager() {
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper =
                new ReleaseOpenHelper(context, "easylife.db");
        final Database db = helper.getWritableDb();

        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();


    }

    public final UserProfileDao getDao() {
        return mDao;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }
}
