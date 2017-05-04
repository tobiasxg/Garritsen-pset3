package com.example.tobias.garritsen_pset3;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Tobias on 20-4-2017.
 */

public class MovieAsyncTask extends AsyncTask<String,Integer,String> {
    Context context;
    MainActivity mainAct;

    public MovieAsyncTask(MainActivity main) {
        this.mainAct = main;
        this.context = this.mainAct.getApplicationContext();
    }

    @Override
    protected void onPreExecute(){
        Toast.makeText(context, "searching for movies...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params){
        return HttpRequestHelper.downloadFromServer("s", params);
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);

        ArrayList<String> movieVariables = new ArrayList<String>();
        String singleTitle = "";
        ArrayList<String> movieTitles = new ArrayList<String>();

        try{
            JSONObject streamObj = new JSONObject(result);
            JSONArray searchArray = streamObj.getJSONArray("Search");
            int l = searchArray.length();
            for(int i=0; i<searchArray.length(); i++){
                JSONObject readObj = searchArray.getJSONObject(i);
                String title = readObj.getString("Title");
//                String year = readObj.getString("Year");
                movieTitles.add(title);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        this.mainAct.movieIntent(movieTitles);
    }
}
