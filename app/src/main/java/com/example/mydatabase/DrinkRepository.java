package com.example.mydatabase;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

//This class will handle method to get and set data fra database

public class DrinkRepository {
    private static final String TAG = "App.DrinkRepository  :";

    //Add member variables for the DAO and the list of animals
    private DrinkDao mDrinkDao;
    private LiveData<List<Drink>> mAllDrinks;

    //Add a constructor that gets a handle to the database and initialises the member variables.

    public DrinkRepository(Application application){
        DrinkDatabase db = DrinkDatabase.getDatabase(application);
        mDrinkDao = db.drinkDao();
    }

    LiveData<List<Drink>> getAllDrinks() {
        mAllDrinks = mDrinkDao.getAllDrinks();
        Log.d(TAG,"Call to mDrinkDao.getAllDrinks() to fetch all drinks into mAllDrinks");
        return mAllDrinks;
    }

    public void insertDrink(Drink drink){
        new insertAsyncTask(mDrinkDao).execute(drink);
        Log.d(TAG, "Drink inserted");
    }



    public void deleteDrink(Drink drink){
        new deleteAsyncTask(mDrinkDao).execute(drink);
    }

    public void updateDrink(Drink drink){
        new updateAsyncTask(mDrinkDao).execute(drink);
    }

    public void deleteAllDrinks(){
        new deleteAllAsyncTask(mDrinkDao).execute();
    }



    private static class insertAsyncTask extends AsyncTask<Drink, Void, Void>{
        private DrinkDao mAsyncTaskDao;

        insertAsyncTask(DrinkDao mAsyncTaskDao){
            this.mAsyncTaskDao = mAsyncTaskDao;
            Log.d(TAG, "insertAsyncTask begun");

        }

        @Override
        protected Void doInBackground(Drink... drinks) {
            mAsyncTaskDao.insertDrink(drinks[0]);
            Log.d(TAG, "insertAsyncTask Completed");
            return null;
        }
    }


    private static class deleteAsyncTask extends AsyncTask<Drink, Void, Void>{
        private DrinkDao mAsyncTaskDao;

        deleteAsyncTask(DrinkDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Drink... drinks) {
            mAsyncTaskDao.deleteDrink(drinks[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Drink, Void, Void>{
        private DrinkDao mAsyncTaskDao;

        updateAsyncTask(DrinkDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Drink... drinks) {
            mAsyncTaskDao.updateDrink(drinks[0]);
            return null;
        }
    }
    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void>{
        private DrinkDao mAsyncTaskDao;

        deleteAllAsyncTask(DrinkDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }


}
