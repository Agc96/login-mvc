package com.qtcteam.loginmvc.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qtcteam.loginmvc.model.db.entities.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM USER WHERE USERNAME = :username AND PASSWORD = :password LIMIT 1")
    User login(String username, String password);

    @Insert
    void insertAll(User... usuarios);

    @Delete
    void delete(User usuario);

}
