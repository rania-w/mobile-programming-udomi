package com.example.udomiba;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PetDAO {
    @Query("SELECT * FROM pets")
    List<Pet> getAll();

    @Query("SELECT * FROM pets WHERE ownerId = :ownerId")
    List<Pet> getMine(int ownerId);
}
