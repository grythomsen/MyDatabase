package com.example.mydatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Drink.class, version = 1, exportSchema = false)
public abstract class DrinkDatabase extends RoomDatabase {
    private static final String TAG = "App.DrinkDatabase    :";

    //Define the DAO and declare an abstract “getter” method // TODO what is going on here?
    public abstract DrinkDao drinkDao();

    // Define constant name for database
    private static final String DRINK_DB_NAME = "drink_db";

    private static volatile DrinkDatabase INSTANCE;


    // Synchronized is ensuring that the code is never executed concurrently by two different threads
    // Checks if DB instance has been created, create if not

    static DrinkDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (DrinkDatabase.class){
                if (INSTANCE == null){
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    DrinkDatabase.class, DRINK_DB_NAME)
                                    .addCallback(roomCallBack)
                                    .build();
                    Log.d(TAG, "DB created");

                }
            }
        }
        return INSTANCE;

    }


    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private DrinkDao drinkDao;

        private PopulateDbAsyncTask(DrinkDatabase db){
            drinkDao = db.drinkDao();
            Log.d(TAG, "DrinkDao instantiated as drinkDao. drinkDao ");
        }

        @Override
        protected Void doInBackground(Void... voids){
            drinkDao.insertDrink(new Drink("Tuborg Classic", "Mørk øl fra Tuborg", "1%"));
            drinkDao.insertDrink(new Drink("Tuborg Pilsner", "Lys øl fra Tuborg", "1%"));
            drinkDao.insertDrink(new Drink("Grimbergen", "Mørk øl til Sofie", "1%"));
            Log.d(TAG, "Mock data added to DB (Only executed on first run)");

            return null;
        }
    }


}
