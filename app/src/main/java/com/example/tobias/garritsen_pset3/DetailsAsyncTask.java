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
//    OutputActivity resultAct;

    public DetailsAsyncTask(OutputActivity output) {
//public MovieAsyncTask(OutputActivity output) {
        this.outAct = output;
        this.context = this.outAct.getApplicationContext();
//        this.resultAct = output;
//        this.context = this.resultAct.getApplicationContext();
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

        ArrayList<String> movieVariables = new ArrayList<String>();
        movieVariables.add("Title");
        movieVariables.add("Year");
        movieVariables.add("Runtime");
        movieVariables.add("Genre");
        movieVariables.add("Director");
        movieVariables.add("Actors");
        movieVariables.add("Plot");

        String singleTitle = "";
//        String title = "";

        ArrayList<String> movieTitles = new ArrayList<String>();

        MovieData mdata = null;
        ArrayList<MovieData> mdataList = new ArrayList<MovieData>();
        String title = "";
        String posterUrl = "";
        String year = "";
        String runtime = "";
        String actors = "";
        String plot = "";

        try{
            JSONObject readObj = new JSONObject(result);
//            JSONObject currentObj;
//            for(int i=1; i<movieVariables.size(); i++){
//                currentObj = streamObj.getJSONObject(movieVariables.get(i));
//                movieVariables.set(i,currentObj.toString());
//                singleTitle = streamObj.getJSONObject("Title").toString();
//            }
//            JSONArray searchArray = streamObj.getJSONArray("Search");
//            int l = searchArray.length();
//            for(int i=0; i<searchArray.length(); i++){
//                JSONObject tempJSON = searchArray.getJSONObject(i);
//                JSONObject readObj = streamObj.getJSONObject(result);
//                String title = tempJSON.getString("Title");
//                String year = tempJSON.getString("Year");
//                String id = tempJSON.getString("imdbID");
//                JSONObject readObj = new JSONObject(result);
//                String plot = readObj.getString("Plot");
                title = readObj.getString("Title");
                posterUrl = readObj.getString("Poster");
                year = readObj.getString("Year");
                runtime = readObj.getString("Runtime");
                plot = readObj.getString("Plot");
                actors = readObj.getString("Actors");
//                String director = readObj.getString("Director");
//                String actors = readObj.getString("Actors");
//                String actors = tempJSON.getString("Actors");
//                String plot = tempJSON.getString("Plot");
                movieTitles.add(title);
                mdata = new MovieData(title, year, posterUrl);//, actors, plot);
                mdataList.add(mdata);
//            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        this.outAct.fullDetails(title, posterUrl, year, runtime, plot, actors);
//        this.resultAct.fillData(movieVariables);
    }
}
