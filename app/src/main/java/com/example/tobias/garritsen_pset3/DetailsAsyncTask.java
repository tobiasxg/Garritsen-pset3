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

public class DetailsAsyncTask extends AsyncTask<String,Integer,String> {
    Context context;
    OutputActivity outAct;

    public DetailsAsyncTask(OutputActivity output) {
        this.outAct = output;
        this.context = this.outAct.getApplicationContext();
    }

    @Override
    protected void onPreExecute(){
        Toast.makeText(context, "searching for movies...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params){
        return HttpRequestHelper.downloadFromServer("t",params);
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);

        String title = "";
        String posterUrl = "";
        String year = "";
        String runtime = "";
        String actors = "";
        String plot = "";

        try{
            JSONObject readObj = new JSONObject(result);
                title = readObj.getString("Title");
                posterUrl = readObj.getString("Poster");
                year = readObj.getString("Year");
                runtime = readObj.getString("Runtime");
                plot = readObj.getString("Plot");
                actors = readObj.getString("Actors");
        }catch(JSONException e){
            e.printStackTrace();
        }
        this.outAct.fullDetails(title, posterUrl, year, runtime, plot, actors);
    }
}
