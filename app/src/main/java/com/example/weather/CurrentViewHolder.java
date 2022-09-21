package com.example.weather;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CurrentViewHolder extends WeatherViewHolder{
    TextView currentTemp;
    TextView condition;

    public CurrentViewHolder(@NonNull View itemView) {
        super(itemView);
        currentTemp = itemView.findViewById(R.id.current_temp);
        condition = itemView.findViewById(R.id.current_condition);
    }

    @Override
    void onUpdate(WeatherData data) {
        if(data != null){
            currentTemp.setText(WeatherData.temp(data.current.temp));
            condition.setText(data.current.description);
        }
    }
}
