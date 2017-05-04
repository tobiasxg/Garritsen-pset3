package com.example.tobias.garritsen_pset3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Tobias on 3-5-2017.
 */

public class PosterLoad extends AsyncTask<String, Bitmap, Bitmap>{

    String posterURL;

    public PosterLoad(String posterURL){
        this.posterURL = posterURL;
    }

    protected Bitmap doInBackground(String... params){
        Bitmap poster = null;
        try {
            URL url = new URL(posterURL);
            InputStream inputStream = url.openStream();
            poster = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e){
            e.printStackTrace();
        }
        return poster;
    }

}
