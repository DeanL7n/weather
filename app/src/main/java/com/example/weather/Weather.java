package com.example.weather;

import android.app.Application;

import com.google.android.material.color.DynamicColors;

public class Weather extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);
    }
}
