package com.example.weather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LocationSelection extends Fragment {

    RecyclerView locationsRecyclerView;
    WeatherFragment weatherFragment;

    public LocationSelection() {
        // Required empty public constructor
    }

    public static LocationSelection newInstance(/*LocationSelectedCallback callback*/) {
        LocationSelection fragment = new LocationSelection();
        fragment.weatherFragment = WeatherFragment.newInstance();
        //fragment.callback = callback;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_selection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        locationsRecyclerView = requireActivity().findViewById(R.id.rv_locations);
        locationsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        LocationsAdapter locationsAdapter = new LocationsAdapter(position -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, weatherFragment, null);

            if (position == 0){

            }
            transaction.commit();
        });
        locationsRecyclerView.setAdapter(locationsAdapter);
    }
}