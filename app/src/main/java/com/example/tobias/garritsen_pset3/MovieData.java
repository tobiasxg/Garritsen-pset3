package com.example.tobias.garritsen_pset3;

import android.graphics.Bitmap;

/**
 * Created by Tobias on 2-5-2017.
 */

public class MovieData {
    String title;
    String year;
    String posterUrl;
//    String actors;
//    String plot;
//    Bitmap poster;

    public MovieData(String title, String year, String posterUrl){//, Bitmap poster) {
        this.title = title;
        this.year = year;
        this.posterUrl = posterUrl;
//        this.actors = actors;
//        this.plot = plot;
//        this.poster = poster;
    }

}