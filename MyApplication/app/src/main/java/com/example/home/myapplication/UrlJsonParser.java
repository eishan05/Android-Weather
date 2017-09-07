package com.example.home.myapplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Home on 9/6/2017.
 */

public class UrlJsonParser {

    private final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?mode=json&q=";
    private final String APP_ID="&APPID=cdd933051b31ca5fc539ba785e0a9ba4";

    public String getTemp(String location) throws IOException, JSONException
    {
         String url = BASE_URL + location + APP_ID;
         String temp = JsonParser(getJSONObjectFromURL(url));
        return temp;
    }

    private JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */ );
        urlConnection.setConnectTimeout(15000 /* milliseconds */ );
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        System.out.println("JSON: " + jsonString);

        return new JSONObject(jsonString);
    }

    private String JsonParser(JSONObject jObj) throws JSONException
    {
        JSONObject mainObj = jObj.getJSONObject("main");
        double temp = mainObj.getDouble("temp");
        temp = 273.15-temp;
        String returnValue = Double.toString(temp);
        return returnValue;
    }
}
