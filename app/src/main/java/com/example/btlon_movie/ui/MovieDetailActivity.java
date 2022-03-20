package com.example.btlon_movie.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.btlon_movie.R;
import com.example.btlon_movie.adapter.CastAdapter;
import com.example.btlon_movie.models.Cast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView MovieThumbnailImg,MovieCoverImg;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_fab;
    private RecyclerView RvCast;
    private CastAdapter castAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);



        //lấy dữ liệu
        iniView();

        //xây dựng list cast
        setuplistcast();

    }
    void iniView(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if (bundle!=null){
            String movieTitle=bundle.getString("title");
            int imageResoureceId=bundle.getInt("imgUrl");
            int imageCover=bundle.getInt("imgCover");

            play_fab=findViewById(R.id.play_fab);
            RvCast=findViewById(R.id.RvCast);

            MovieThumbnailImg=findViewById(R.id.detail_movie_img);
            Glide.with(this).load(imageResoureceId).into(MovieThumbnailImg);
            MovieCoverImg=findViewById(R.id.detail_movie_cover);
            Glide.with(this).load(imageCover).into(MovieCoverImg);
            tv_title=findViewById(R.id.detail_movie_title);
            tv_title.setText(movieTitle);
            getSupportActionBar().setTitle(movieTitle);
            tv_description=findViewById(R.id.detail_movie_desc);
            MovieThumbnailImg.setImageResource(imageResoureceId);

            //set animation
            MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animtion));
            play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animtion));

        }
    }
    void setuplistcast(){
        List<Cast> mdata=new ArrayList<>();
        mdata.add(new Cast("name",R.drawable.andrew_garfield));
        mdata.add(new Cast("name",R.drawable.tobey_maguire));
        mdata.add(new Cast("name",R.drawable.benedict_cumberbatch));
        mdata.add(new Cast("name",R.drawable.tom_holland));

        castAdapter =new CastAdapter(this,mdata);
        RvCast.setAdapter(castAdapter);
        RvCast.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }
}