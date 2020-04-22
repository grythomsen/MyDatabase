package com.example.mydatabase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "drinkTable")
public class Drink {
    private static final String TAG = "App.Drink            :";

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String description;
    private String percentage;

    public Drink(String name, String description, String percentage) {
        this.name = name;
        this.description = description;
        this.percentage = percentage;
        Log.d(TAG, "Drink instance constructed " + this.getName());
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
