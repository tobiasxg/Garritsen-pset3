package com.example.tobias.garritsen_pset3;

import android.content.Intent;
import android.icu.util.Output;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

//http://img.omdbapi.com/?i=tt2294629&apikey=95a79eac
public class MainActivity extends AppCompatActivity {

    EditText searchMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchMovie = (EditText) findViewById(R.id.searchMovie);
        assert searchMovie != null;
    }

    public void searchMovie(View v){
        String input = searchMovie.getText().toString();
        MovieAsyncTask asyncTask = new MovieAsyncTask(this);
        asyncTask.execute(input);
//        searchMovie.getText().clear();
//        http://img.omdbapi.com/?i=tt2294629&apikey=95a79eac

//        Intent searchIntent = new Intent(this, OutputActivity.class);
//        searchIntent.putExtra("movie",input);
//        startActivity(searchIntent);

        EditText show = (EditText) findViewById(R.id.searchMovie);
    }

    public void movieIntent(ArrayList<String> data){
//        Intent i = new Intent(this, OutputActivity.class);
        Intent i = new Intent(this, ListActivity.class);
        i.putExtra("movies", data);
        i.putStringArrayListExtra("titleList", data);
        this.startActivity(i);

    }

    public void singleTitle(String title){
        EditText show = (EditText) findViewById(R.id.searchMovie);
        show.setText(title);
    }
}
