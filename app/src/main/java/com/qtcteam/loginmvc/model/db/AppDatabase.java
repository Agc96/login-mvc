package com.qtcteam.loginmvc.model.db;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.qtcteam.loginmvc.model.db.dao.UserDao;

public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "loginmvc.db";
    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                    DB_NAME).build();
        }
        return INSTANCE;
    }

    /// region DAOs
    public abstract UserDao userDao();
    /// endregion

}
