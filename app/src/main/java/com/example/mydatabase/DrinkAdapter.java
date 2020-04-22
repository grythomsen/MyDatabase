package com.example.mydatabase;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkHolder> {
    private static final String TAG = "App.DrinkAdapter     :";
    private List<Drink> drinks = new ArrayList<>();

    @NonNull
    @Override
    public DrinkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drink_item, parent, false);

        Log.d(TAG,"Drink holder created");

        return new DrinkHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkHolder holder, int position) {
    Drink currentDrink = drinks.get(position);
    holder.textViewTitle.setText(currentDrink.getName());
    holder.textViewDescription.setText(currentDrink.getDescription());
    holder.textViewPercentage.setText(currentDrink.getPercentage());
    Log.d(TAG, "Drink text view fields set. Drink list position:" + position);

    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public void setDrinks(List<Drink> drinks){
        this.drinks = drinks;
        Log.d(TAG,"setDrinks done");
        notifyDataSetChanged();
    }

    public class DrinkHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewPercentage;
        private TextView textViewDescription;

        public DrinkHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tv_title);
            textViewDescription = itemView.findViewById(R.id.tv_description);
            textViewPercentage = itemView.findViewById(R.id.tv_percentage);
            Log.d(TAG,"Drink holder constructed:");
        }
    }
}
