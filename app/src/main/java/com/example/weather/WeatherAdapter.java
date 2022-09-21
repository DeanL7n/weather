package com.example.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {

    WeatherData data;

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType){
            case 0:
                view = inflater.inflate(R.layout.current_viewholder, parent, false);
                return new CurrentViewHolder(view);
            case 1:
                view = inflater.inflate(R.layout.hours_viewholder, parent, false);
                return new HoursViewHolder(view);
            case 2:
                view = inflater.inflate(R.layout.week_viewholder, parent, false);
                return new DaysViewHolder(view);
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
        //return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        holder.onUpdate(data);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    void update(WeatherData data){
        this.data = data;
        this.notifyDataSetChanged();
    }

}
