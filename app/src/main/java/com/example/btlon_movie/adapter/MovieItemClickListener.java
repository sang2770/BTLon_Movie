package com.example.btlon_movie.adapter;

import android.widget.ImageView;

import com.example.btlon_movie.models.Movie;
//tạo su kien khi nhan vào item phim
public interface MovieItemClickListener {

    void onMovieClick(Movie movie, ImageView movieImageView);//cần imageview để share animation giữa 2 activity


}
