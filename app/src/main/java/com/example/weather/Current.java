package com.example.weather;

import org.json.JSONException;
import org.json.JSONObject;

public class Current {
    long sunrise;
    long sunset;
    double temp;
    double feelsLike;
    double windSpeed;
    int pressure;
    int humidity;
    int uvIndex;
    int visibility;
    int windDeg;
    String description;
    String icon;

    public Current(){

    }

    void update(JSONObject data){
        try {
            sunrise = data.getLong("sunrise");
            sunset = data.getLong("sunset");
            temp = data.getDouble("temp");
            feelsLike = data.getDouble("feels_like");
            windSpeed = data.getDouble("wind_speed");
            pressure = data.getInt("pressure");
            humidity = data.getInt("humidity");
            uvIndex = data.getInt("uvi");
            visibility = data.getInt("visibility");
            windDeg = data.getInt("wind_deg");
            JSONObject weather = data.getJSONArray("weather").getJSONObject(0);
            description = weather.getString("description");
            icon = weather.getString("icon");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
