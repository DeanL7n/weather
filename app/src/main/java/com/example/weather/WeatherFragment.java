package com.example.weather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.concurrent.Callable;

public class WeatherFragment extends Fragment {

    RecyclerView weatherRecycler;
    SwipeRefreshLayout swipeRefreshLayout;
    WeatherViewModel viewModel;
    WeatherAdapter adapter;
    MaterialToolbar toolbar;

    public WeatherFragment() {
        // Required empty public constructor
    }

    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = requireActivity().findViewById(R.id.tbr_weather);
        toolbar.setTitle("Enniskillen");
        swipeRefreshLayout = requireActivity().findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(() -> viewModel.update(() -> {
            swipeRefreshLayout.setRefreshing(false);
            adapter.update(viewModel.weatherData);
        }));
        weatherRecycler = requireActivity().findViewById(R.id.weatherrecycler);
        weatherRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new WeatherAdapter();
        weatherRecycler.setAdapter(adapter);
    }
}