package com.ubc.ece.cpen2912017w.group6a.android_weather;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //date when the app is opened
    public Date date = new Date();

    //openweathermap.org weather api to get current weather data at Houston
    final static String weatherHouston = "http://api.openweathermap.org/data/2.5/weather?id=4699066&appid=c68b9e7c160eb22994944055838d20e0";
    static String weatherJSON = "";
    static JSONObject jsonObject = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //reterive json weather data from openweathermap.org and save as global variable
        new GetWeatherJSON(this).execute();

        //initialize button
        Button button = findViewById(R.id.weatherUpdate);

        //display the weather data in textview when the button is clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //format date and save as string
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String stringDate = dateFormat.format(date);
                TextView tv1 = findViewById(R.id.textView);
                try
                {
                    //format the display string in textView
                    JSONObject weather  = jsonObject.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = jsonObject.getJSONObject("main");
                    JSONObject wind = jsonObject.getJSONObject("wind");
                    JSONObject clouds = jsonObject.getJSONObject("clouds");

                    tv1.setText("Date:    " + stringDate + "\n" +
                            "City:    " + jsonObject.getString("name") + "\n" +
                            "Main Weather:    " + weather.getString("main") + "\n" +
                            "Weather Description:    " + weather.getString("description") + "\n" +
                            "Current Temperature(Kelvin):    " + main.getString("temp") + "\n" +
                            "Pressure(hPa):    " + main.getString("pressure") + "\n" +
                            "Humidity(%):    " + main.getString("humidity") + "\n" +
                            "Wind Speed(meter/sec):    " + wind.getString("speed") + "\n" +
                            "Wind Degree(degrees):    " + wind.getString("deg") + "\n" +
                            "Cloudiness(%):    " + clouds.getString("all") + "\n"
                            );
                }
                catch (JSONException e) {
                    Log.e("My debug", "JSONException", e);
                }
            }
        });

    }

    private static class GetWeatherJSON extends AsyncTask<Void, Void, String> {

        private WeakReference<MainActivity> activityReference;

        // only retain a weak reference to the activity
        GetWeatherJSON(MainActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {

                //initialize the url address from the string url address
                URL urlWeatherHouston = new URL(weatherHouston);
                //establish the connection
                HttpURLConnection con = (HttpURLConnection) urlWeatherHouston.openConnection();

                //get the json stream from php
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String json;
                while ((json = bufferedReader.readLine()) != null) {
                    Log.d("My debug", json);
                    sb.append(json + "\n");
                }
                weatherJSON = sb.toString().trim();
                try {
                    jsonObject = new JSONObject(weatherJSON);
                } catch (JSONException e) {
                    Log.e("My debug", "JSONException", e);
                }
                //Log.d("My debug", jsonCommands);
                return sb.toString().trim();
            } catch (Exception e) {
                Log.d("My debug", "Failed building json string");
                Log.e("My debug", "exception", e);
                return null;
            }
        }
    }
}
