package com.example.mydatabase;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DrinkViewModel extends AndroidViewModel {

    static String TAG = "Drink VM       :";

    private LiveData<List<Drink>> allDrinks;
    private DrinkRepository mRepository;

    public DrinkViewModel(Application application){
        super(application);

        mRepository = new DrinkRepository(application);
        allDrinks = mRepository.getAllDrinks();
        Log.d(TAG,"Drink model constructed + ");
    }

    public LiveData<List<Drink>> getAllDrinks() {
        return allDrinks;
    }

    public void insertDrink(Drink drink) {
        mRepository.insertDrink(drink);
    }
}
