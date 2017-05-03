package com.example.tobias.garritsen_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OutputActivity extends AppCompatActivity {
    TextView viewResults;
    ListView viewList;
    ArrayList<String> movieArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        viewResults = (TextView) findViewById(R.id.foundMovie);
//        viewList = (ListView) findViewById(R.id.);

        Bundle extras = getIntent().getExtras();
        String input = extras.getString("title");
        viewResults.setText(input);

        DetailsAsyncTask dAsyncTask = new DetailsAsyncTask(this);
        dAsyncTask.execute(input);

//        toAsync(input);

//        MovieAsyncTask asyncTask = new MovieAsyncTask(this);
//        asyncTask.execute(input);


        movieArray = (ArrayList<String>) extras.getSerializable("movies");
    }

//    public void toAsync(String input){
//        MovieAsyncTask asyncTask = new MovieAsyncTask(this);
//        asyncTask.execute(input);
//    }

    public void fillData(ArrayList<String> movieVariables){
        TextView movieTitle = (TextView) findViewById(R.id.foundMovie);
        movieTitle.setText(movieVariables.get(0));
    }

    public void makeTrackAdapter(){
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, movieArray);
//        viewList = (ListView) findViewById(R.id.);
        assert viewList != null;
        viewList.setAdapter(arrayAdapter);
    }

    public void fullDetails(String title, String posterUrl, String year,
                            String runtime, String plot, String actors){
//        Intent i = new Intent(this, OutputActivity.class);
        TextView titleV = (TextView) findViewById(R.id.foundMovie);
        TextView yearV = (TextView) findViewById(R.id.year);
        TextView posterV = (TextView) findViewById(R.id.poster);
        TextView runtimeV = (TextView) findViewById(R.id.runtime);
        TextView actorsV = (TextView) findViewById(R.id.actors);
        TextView plotV = (TextView) findViewById(R.id.plot);
        titleV.setText(title);
        yearV.setText(year);
        posterV.setText(posterUrl);
        runtimeV.setText(runtime);
        actorsV.setText(actors);
        plotV.setText(plot);
//        Bitmap posterImage = null;
//        try {
//            URL url = new URL(posterUrl);
//            InputStream inputStream = url.openStream();
//            posterImage = BitmapFactory.decodeStream(inputStream);
//            Toast.makeText(this.getApplicationContext(), "searching for poster...", Toast.LENGTH_SHORT).show();
//        } catch (Exception e){
//            Toast.makeText(this.getApplicationContext(), "failed poster...", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//
//        ImageView posterView = (ImageView) findViewById(R.id.posterView);
//        posterView.setImageBitmap(posterImage);

//        String url = posterUrl;
//        Drawable d = null;
//        try {
//            InputStream is = (InputStream) new URL(url).getContent();
//            d = Drawable.createFromStream(is, "src name");
//        } catch (Exception e) {
//            d = null;
//        }
//        posterImage = d

        ImageView posterView = (ImageView) findViewById(R.id.posterView);

        // APPARENTLY THIS IS IMPOSSIBLE
        try {
            URL url = new URL("https://images-na.ssl-images-amazon.com/images/M/MV5BMTY5MzYzNjc5NV5BMl5BanBnXkFtZTYwNTUyNTc2._V1_SX300.jpg");
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            posterView.setImageBitmap(bmp);
        } catch (Exception e){
        }

//        URL url;
//        Bitmap bmp = null;
//        try {
//            url = new URL("https://images-na.ssl-images-amazon.com/images/M/MV5BMTY5MzYzNjc5NV5BMl5BanBnXkFtZTYwNTUyNTc2._V1_SX300.jpg");
//            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            posterView.setImageBitmap(bmp);
//        } catch (Exception e) {
//            Drawable myDrawable = getResources().getDrawable(R.drawable.error);
//            posterView.setImageDrawable(myDrawable);
//        }
//        posterView.setImageBitmap(posterImage);

//        Picasso.with(getApplicationContext()).load("http://www.farsnews.com/shares/img/PLogo.jpg").into(imageView);

    }

    public void addFavorites(View v){
        TextView titleV = (TextView) findViewById(R.id.foundMovie);
        String name = titleV.getText().toString();
        // gets saved movies
        SharedPreferences prefs = this.getSharedPreferences("settings", this.MODE_APPEND);
        SharedPreferences.Editor editor = prefs.edit();
        String savedMovies = prefs.getString("movies", "");
        // add movie that has to be saved to saved movies with seperator
        String movieNames = savedMovies + name + '*';
        editor.putString("movies", movieNames);
        editor.commit();
        Toast.makeText(this, "Movie added to favorites", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, FavoriteMovies.class);
        this.startActivity(i);
        finish();
    }

}
