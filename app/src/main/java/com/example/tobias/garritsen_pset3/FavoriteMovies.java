package com.example.tobias.garritsen_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class FavoriteMovies extends AppCompatActivity {

    ListView viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);

        SharedPreferences prefs = this.getSharedPreferences("settings",this.MODE_PRIVATE);
        String savedMovie = prefs.getString("movies", "");
        // put all movies in array by splitting on the separator
        String[] savedMovies = savedMovie.split("\\*");
        // add movies to arraylist to display in listview
        String firstWouldBe = savedMovies[0];
        ArrayList<String> movies = new ArrayList<>(Arrays.asList(savedMovies));
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, movies);
//        watchList.setAdapter(adapter);
        viewList = (ListView) findViewById(R.id.favoritesList);
        assert viewList != null;
        viewList.setAdapter(adapter);

        viewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // get page of movie by clicking on one of the movie names
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                TextView titleView = (TextView) view;
                String title = titleView.getText().toString();
                Intent i = new Intent(getApplicationContext(), OutputActivity.class);
                i.putExtra("title", title);
                // to display the right button since a movie on watchlist shouldn't be added again
                startActivity(i);
                finish();
            }
        });

    }

}
