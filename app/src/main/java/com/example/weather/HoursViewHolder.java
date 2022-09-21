package com.example.weather;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HoursViewHolder extends WeatherViewHolder{
    RecyclerView recyclerView;
    HoursAdapter adapter;
    public HoursViewHolder(@NonNull View itemView) {
        super(itemView);
        recyclerView = itemView.findViewById(R.id.rv_hours);
        adapter = new HoursAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    void onUpdate(WeatherData data) {
        if (data != null){
            adapter.data = data;
            //TemperatureGraph.setTemps(data.hours);
            adapter.notifyDataSetChanged();
        }
    }
}
