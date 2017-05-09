package com.example.tobias.garritsen_pset3;

import android.content.Intent;
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

public class ListActivity extends AppCompatActivity {

    ListView viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Bundle extras = getIntent().getExtras();
        String mdata = extras.getString("mdata");
        ArrayList<String> titlesArray = extras.getStringArrayList("titleList");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, titlesArray);

        viewList = (ListView) findViewById(R.id.moviesList);
        assert viewList != null;
        viewList.setAdapter(arrayAdapter);

        AdapterView.OnItemClickListener moviesListener = new MoviesListener();

        viewList.setOnItemClickListener(moviesListener);

//        viewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            // get page of movie by clicking on one of the movie names
//            public void onItemClick(AdapterView parent, View view, int position, long id) {
//                TextView titleView = (TextView) view;
//                String title = titleView.getText().toString();
//                Intent i = new Intent(getApplicationContext(), OutputActivity.class);
//                i.putExtra("title", title);
//                startActivity(i);
//                finish();
//            }
//        });
    }

    private class MoviesListener implements AdapterView.OnItemClickListener {
        // get page of movie by clicking on one of the movie names
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            TextView titleView = (TextView) view;
            String title = titleView.getText().toString();
            Intent i = new Intent(getApplicationContext(), OutputActivity.class);
            i.putExtra("title", title);
            startActivity(i);
            finish();
        }
    }

}
