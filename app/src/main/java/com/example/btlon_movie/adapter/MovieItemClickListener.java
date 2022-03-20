package com.example.btlon_movie.adapter;

import android.widget.ImageView;

import com.example.btlon_movie.models.Movie;

public interface MovieItemClickListener {

    void onMovieClick(Movie movie, ImageView movieImageView);//cần imageview để share animation giữa 2 activity


}
