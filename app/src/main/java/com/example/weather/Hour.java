package com.example.weather;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Hour {
    long time;
    double temp;
    String description;
    //String icon;
    int icon;

    SimpleDateFormat format = new SimpleDateFormat("HH:mm");

    public Hour(){

    }

    void update(JSONObject data){
        try {
            time = data.getLong("dt");
            temp = data.getDouble("temp");
            JSONObject weather = data.getJSONArray("weather").optJSONObject(0);
            description = weather.getString("main");
            icon = WeatherData.icons.get(weather.getString("icon"));
            //icon = weather.getString("icon");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    String getFormattedTime(){
        return format.format(Date.from(Instant.ofEpochSecond(time)));
    }
}
