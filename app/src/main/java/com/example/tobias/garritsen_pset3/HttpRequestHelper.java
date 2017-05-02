package com.example.tobias.garritsen_pset3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Tobias on 20-4-2017.
 */

public class HttpRequestHelper {

    protected static synchronized String downloadFromServer(String... params) {
        String result = "";
        String chosenTag = params[0];
        chosenTag = chosenTag.replaceAll("\\s", "+");

        // maak url met api en zoekterm gebruiker
        //'http://img.omdbapi.com/?i=tt2294629&apikey=95a79eac'
        //String restUrl"&y=&plot=short&r=json";
        String siteUrl = "http://www.omdbapi.com/?s=";
        String urlComplete = siteUrl + chosenTag;

        URL url = null;


        try {
            url = new URL(urlComplete);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        HttpURLConnection connect;

        if (url != null){
            try{
                connect = (HttpURLConnection) url.openConnection();
                connect.setRequestMethod("GET");

                Integer responseCode = connect.getResponseCode();
                if (responseCode >= 200 && responseCode < 300){
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                    String line;
                    while((line=bufferedReader.readLine()) != null){
                        result+=line;
                    }
                }
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        return result;
    }
}
