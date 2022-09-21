package com.example.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.Holder> {

    WeatherData data;
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.hour_viewholder, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if (data != null){
            //holder.time.setText((int) data.hours[position].time);
            holder.time.setText(data.hours[position].getFormattedTime());
            holder.temperature.setText(WeatherData.temp(data.hours[position].temp));
            holder.icon.setImageDrawable(holder.icon.getContext().getDrawable(data.hours[position].icon));
            //holder.graph.update(position);
        }
    }

    @Override
    public int getItemCount() {
        return 24;
    }

    static class Holder extends RecyclerView.ViewHolder{
        TextView time;
        TextView temperature;
        ImageView icon;
        //TemperatureGraph graph;

        public Holder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.txt_time);
            temperature = itemView.findViewById(R.id.txt_temp);
            icon = itemView.findViewById(R.id.img_icon);
            //graph = itemView.findViewById(R.id.temperatureGraph);
        }
    }
}
