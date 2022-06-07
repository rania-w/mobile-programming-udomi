package com.example.udomiba;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM users WHERE email = :email")
    User getByEmail(String email);

    @Insert
    void addUser(User newUser);

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    User verifyLogin(String email, String password);
}
