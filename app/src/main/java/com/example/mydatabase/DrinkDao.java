package com.example.mydatabase;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;



import java.util.List;

@Dao
public interface DrinkDao {

    @Insert
    void insertDrink(Drink drink);

    @Delete
    void deleteDrink(Drink drink);

    @Update
    void updateDrink(Drink drink);

    @Query("SELECT * FROM drinkTable ORDER BY name ASC")
    LiveData<List<Drink>> getAllDrinks();

    @Query("DELETE FROM drinkTable")
    void deleteAll();
}
