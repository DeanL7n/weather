package com.example.weather;

import android.location.Location;
import android.os.Handler;

import androidx.lifecycle.ViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class WeatherViewModel extends ViewModel {

    static final String endpoint = "https://api.openweathermap.org/data/3.0/onecall?";
    static final String params = "&exclude=minutely&units=metric";
    static final String key = "&appid=";
    Handler handler = new Handler();

    WeatherData weatherData = new WeatherData();

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    LocationRequestedCallback locationRequestedCallback;

    void update(Runnable onFinished){
        executorService.submit(() -> {
            try {
                URL url = new URL(endpoint + getLocation(54.35, -7.63) + params + key);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                InputStream stream = connection.getInputStream();
                String response = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining());
                weatherData.update(new JSONObject(response));
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            handler.post(onFinished);
        });
    }

    String getLocation(double lat, double lng){
        String latitude = Double.toString(lat);
        String longitude = Double.toString(lng);
        return "lat=" + latitude + "&lon=" + longitude;
    }

    interface LocationRequestedCallback{
        Location onLocationRequested();
    }

}
