package com.example.udomiba;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={Pet.class, User.class}, version=1, exportSchema=false)
public abstract class PetDatabase extends RoomDatabase {
    public abstract PetDAO petDAO();
    public abstract UserDAO userDAO();
    public static PetDatabase instance=null;

    public static PetDatabase getInstance(Context context){
        if(instance==null){
            instance=Room.databaseBuilder(context, PetDatabase.class, "pet database").allowMainThreadQueries().build();
        }
        return instance;
    }
}
