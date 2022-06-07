package com.example.udomiba;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM users WHERE email= :email AND password = :password")
    boolean logIn(String email, String password);
}
