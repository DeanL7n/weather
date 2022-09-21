package com.example.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.Holder> {
    WeatherData data;

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.day_viewholder, parent, false);
        return new DaysAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if (data != null){
            holder.day.setText(data.days[position].getFormattedDate());
            holder.condition.setText(data.days[position].condition);
            holder.high.setText(WeatherData.temp(data.days[position].maxTemp));
            holder.low.setText(WeatherData.temp(data.days[position].minTemp));
            holder.icon.setImageDrawable(holder.icon.getContext().getDrawable(data.days[position].icon));
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    static class Holder extends RecyclerView.ViewHolder{
        TextView day;
        TextView condition;
        TextView high;
        TextView low;
        ImageView icon;

        public Holder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.txt_day);
            condition = itemView.findViewById(R.id.txt_day_condition);
            high = itemView.findViewById(R.id.txt_day_high);
            low = itemView.findViewById(R.id.txt_day_low);
            icon = itemView.findViewById(R.id.img_day_icon);
        }
    }
}
