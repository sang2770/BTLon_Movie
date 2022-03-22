package com.example.btlon_movie.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.btlon_movie.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView MovieThumbnailImg,MovieCoverImg;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_fab;
    private RecyclerView RvCast;
    private CheckBox Cklike;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        //lấy dữ liệu
        iniView();


    }
    void iniView(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if (bundle!=null){
            String movieTitle=bundle.getString("title");
            int imageResoureceId=bundle.getInt("imgUrl");
            int imageCover=bundle.getInt("imgCover");
            boolean like=bundle.getString("rating")=="like"?true:false;

            play_fab=findViewById(R.id.play_fab);


            MovieThumbnailImg=findViewById(R.id.detail_movie_img);
            Glide.with(this).load(imageResoureceId).into(MovieThumbnailImg);
            MovieCoverImg=findViewById(R.id.detail_movie_cover);
            Glide.with(this).load(imageCover).into(MovieCoverImg);
            tv_title=findViewById(R.id.detail_movie_title);
            tv_title.setText(movieTitle);
            getSupportActionBar().setTitle(movieTitle);
            tv_description=findViewById(R.id.detail_movie_desc);
            MovieThumbnailImg.setImageResource(imageResoureceId);
            Cklike=findViewById(R.id.CkLike);
            Cklike.setChecked(like);




            //set animation
            MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animtion));
            play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animtion));

        }
    }

}