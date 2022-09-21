package com.example.weather;

import android.graphics.drawable.Drawable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class WeatherData {
    Current current;
    Hour[] hours = new Hour[24];
    Day[] days = new Day[7];

    SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
    SimpleDateFormat dayFormat = new SimpleDateFormat("EEE");

    static HashMap<String, Integer> icons = new HashMap<>();


    public WeatherData(){
        current = new Current();
        for (int i = 0; i < 24; i++){
            hours[i] = new Hour();
        }
        for (int i = 0; i < 7; i++){
            days[i] = new Day();
        }

        icons.put("01d", R.drawable.clear_day);
        icons.put("01n", R.drawable.clear_night);
        icons.put("02d", R.drawable.partly_cloudy_day);
        icons.put("02n", R.drawable.partly_cloudy_night);
        icons.put("03d", R.drawable.cloudy);
        icons.put("03n", R.drawable.cloudy);
        icons.put("04d", R.drawable.cloudy);
        icons.put("04n", R.drawable.cloudy);
        icons.put("09d", R.drawable.rainy);
        icons.put("09n", R.drawable.rainy);
        icons.put("10d", R.drawable.rainy);
        icons.put("10n", R.drawable.rainy);
        icons.put("11d", R.drawable.thunderstorm);
        icons.put("11n", R.drawable.thunderstorm);
        icons.put("13d", R.drawable.snowy);
        icons.put("13n", R.drawable.snowy);
        icons.put("50d", R.drawable.foggy);
        icons.put("50n", R.drawable.foggy);














    }

    void update(JSONObject data){
        try {
            current.update(data.getJSONObject("current"));
            JSONArray hourly = data.getJSONArray("hourly");
            for (int i = 0; i < 24; i++){
                hours[i].update(hourly.getJSONObject(i));
            }
            JSONArray daily = data.getJSONArray("daily");
            for (int i = 0; i < 7; i++){
                days[i].update(daily.getJSONObject(i + 1));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static String temp(double temp){
        return Math.round(temp) + "°";
    }
}
