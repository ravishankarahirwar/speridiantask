package com.example.admin.myapplication.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = { User.class }, version = 1)
public  abstract class UserDataBase extends RoomDatabase {
    private static final String DB_NAME = "userDatabase.db";
    private static volatile UserDataBase instance;

    public static synchronized UserDataBase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static UserDataBase create(final Context context) {
        return Room.databaseBuilder(
                context,
                UserDataBase.class,
                DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    public abstract UserDao getUserDao();
}
