package com.example.tobias.garritsen_pset3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
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
//        toAsync(input);
        movieArray = (ArrayList<String>) extras.getSerializable("movies");
    }

//    public void toAsync(String input){
//        MovieAsyncTask asyncTask = new MovieAsyncTask(this);
//        asyncTask.execute(input);
//    }

    public void fillData(ArrayList<String> movieVariables){
        TextView movieTitle = (TextView) findViewById(R.id.foundMovie);
        TextView movieActor = (TextView) findViewById(R.id.actor);
        movieTitle.setText(movieVariables.get(0));
        movieActor.setText(movieVariables.get(5));
    }

    public void makeTrackAdapter(){
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, movieArray);
//        viewList = (ListView) findViewById(R.id.);
        assert viewList != null;
        viewList.setAdapter(arrayAdapter);
    }

}
