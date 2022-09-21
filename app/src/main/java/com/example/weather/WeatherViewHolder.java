package com.example.weather;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class WeatherViewHolder extends RecyclerView.ViewHolder {
    public WeatherViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    abstract void onUpdate(WeatherData data);
}
