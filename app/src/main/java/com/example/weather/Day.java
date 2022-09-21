package com.example.weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Day {
    long time;
    long sunrise;
    long sunset;
    double maxTemp;
    double minTemp;
    int humidity;
    int uvIndex;
    int windDeg;
    int icon;
    double windSpeed;
    String condition;

    SimpleDateFormat format = new SimpleDateFormat("EEEE");

    public Day(){

    }

    void update(JSONObject data){
        try {
            time = data.getLong("dt");
            sunrise = data.getLong("sunrise");
            sunset = data.getLong("sunset");
            JSONObject temp = data.getJSONObject("temp");
            maxTemp = temp.getDouble("max");
            minTemp = temp.getDouble("min");
            humidity = data.getInt("humidity");
            uvIndex = data.getInt("uvi");
            windDeg = data.getInt("wind_deg");
            windSpeed = data.getDouble("wind_speed");
            JSONArray weatherArray = data.getJSONArray("weather");
            JSONObject weather = weatherArray.getJSONObject(0);
            icon = WeatherData.icons.get(weather.getString("icon"));
            condition = weather.getString("description");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    String getFormattedDate(){
        return format.format(Date.from(Instant.ofEpochSecond(time)));
    }
}
