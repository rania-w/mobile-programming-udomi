package com.example.udomiba;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PetDAO {
    @Query("SELECT * FROM pets")
    List<Pet> getAll();

    @Query("SELECT * FROM pets WHERE ownerId = :ownerId")
    List<Pet> getMine(int ownerId);

    @Insert
    void addPet(Pet pet);

    @Query("SELECT * FROM pets WHERE petId = :id")
    Pet getById(int id);

    @Query("SELECT u.phoneNumber FROM users AS u " +
            "JOIN pets AS p ON  u.userId=p.ownerId " +
            "WHERE p.petId= :id")
    String getPhoneByPetId(int id);

    @Update
    void updatePet(Pet pet);

    @Query("SELECT * FROM pets WHERE species = :species")
    List<Pet> getSpecies(String species);
}
