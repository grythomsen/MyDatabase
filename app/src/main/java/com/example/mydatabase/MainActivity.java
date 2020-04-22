package com.example.mydatabase;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "App.MainActivity     :";
    private int ADD_DRINK_REQUEST = 1;

    private DrinkViewModel drinkViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate called");


        FloatingActionButton buttonAddDrink = findViewById(R.id.button_add_drink);
        buttonAddDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddDrinkActivity.class);
                startActivityForResult(intent, ADD_DRINK_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        Log.d(TAG, "Recycler view created");

        final DrinkAdapter adapter = new DrinkAdapter();
        Log.d(TAG,"Drink adapter created");

        recyclerView.setAdapter(adapter);
        Log.d(TAG,"Drink adapter set on recyclerView");


        drinkViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(DrinkViewModel.class);
        Log.d(TAG, "Drink Model provided");

        drinkViewModel.getAllDrinks().observe(this, new Observer<List<Drink>>() {
            @Override
            public void onChanged(List<Drink> drinks) {
                Log.d(TAG, "Observer: onChanged() called, setting drinks");
                adapter.setDrinks(drinks);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_DRINK_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddDrinkActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddDrinkActivity.EXTRA_DESCRIPTION);

            Drink drink = new Drink(title, description, "N/A");
            drinkViewModel.insertDrink(drink);

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
