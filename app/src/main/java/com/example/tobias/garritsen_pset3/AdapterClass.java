package com.example.tobias.garritsen_pset3;

/**
 * Created by Tobias on 2-5-2017.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Output;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class AdapterClass extends AppCompatActivity{

    private ListView moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapter_layout);
        moviesList = (ListView) findViewById(R.id.moviesList);
        // get movies in watchlist
        SharedPreferences prefs = this.getSharedPreferences("settings",this.MODE_PRIVATE);
        String savedMovie = prefs.getString("movies", "");
        // put all movies in array by splitting on the separator
        String[] savedMovies = savedMovie.split("\\*");
        // add movies to arraylist to display in listview
        ArrayList<String> movies = new ArrayList<>(Arrays.asList(savedMovies));
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, movies);
        moviesList.setAdapter(adapter);

        moviesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // get page of movie by clicking on one of the movie names
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                TextView movieView = (TextView) view;
                String movie = movieView.getText().toString();
                Intent resultPage = new Intent(getApplicationContext(), OutputActivity.class);
                resultPage.putExtra("moviename", movie);
                // to display the right button since a movie on watchlist shouldn't be added again
                resultPage.putExtra("whichClass", "watch");
                startActivity(resultPage);
                finish();
            }
        });
    }
}