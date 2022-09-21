package com.example.weather;

import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.divider.MaterialDividerItemDecoration;

public class DaysViewHolder extends WeatherViewHolder{
    RecyclerView recyclerView;
    DaysAdapter adapter;
    public DaysViewHolder(@NonNull View itemView) {
        super(itemView);
        recyclerView = itemView.findViewById(R.id.rv_week);
        adapter = new DaysAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        MaterialDividerItemDecoration divider = new MaterialDividerItemDecoration(itemView.getContext(), LinearLayout.VERTICAL);
        divider.setLastItemDecorated(false);
        recyclerView.addItemDecoration(divider);
    }

    @Override
    void onUpdate(WeatherData data) {
        adapter.data = data;
        adapter.notifyDataSetChanged();
    }
}
