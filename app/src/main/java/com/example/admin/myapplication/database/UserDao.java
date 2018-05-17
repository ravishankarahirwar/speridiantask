package com.example.admin.myapplication.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

@Dao
public interface UserDao {

    @Query("Select * from User")
    List<User> getAllUsers();

    @Query("Select COUNT(*) FROM User")
    int getUsersCount();


    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
