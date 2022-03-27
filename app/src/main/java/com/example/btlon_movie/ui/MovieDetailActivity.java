package com.example.btlon_movie.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.btlon_movie.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView MovieThumbnailImg,MovieCoverImg;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_fab;
    private RecyclerView RvCast;
    private CheckBox Cklike;
    private Button BtnHome;


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
            String imageResoureceId=bundle.getString("imgUrl");
            String imageCover=bundle.getString("imgCover");
            boolean like=bundle.getString("rating").equals("Like")?true:false;
            int ID=bundle.getInt("ID");


            play_fab=findViewById(R.id.play_fab);


            MovieThumbnailImg=findViewById(R.id.detail_movie_img);
            Context context = MovieThumbnailImg.getContext();
            int id = context.getResources().getIdentifier(imageResoureceId, "drawable", context.getPackageName());
            Glide.with(this).load(id).into(MovieThumbnailImg);

            MovieCoverImg=findViewById(R.id.detail_movie_cover);
            Context context1 = MovieCoverImg.getContext();
            int id1 = context.getResources().getIdentifier(imageCover, "drawable", context.getPackageName());
            Glide.with(this).load(id1).into(MovieCoverImg);

            tv_title=findViewById(R.id.detail_movie_title);
            tv_title.setText(movieTitle);
            getSupportActionBar().setTitle(movieTitle);
            tv_description=findViewById(R.id.detail_movie_desc);
            MovieThumbnailImg.setImageResource(id);

            Cklike=findViewById(R.id.CkLike);
            Cklike.setChecked(like);
            Cklike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    FirebaseDatabase database=FirebaseDatabase.getInstance();
                    DatabaseReference myref= database.getReference("Movie/"+ID+"/rating");
                    myref.setValue(b?"Like":"");
                }
            });
            BtnHome=findViewById(R.id.btn_home);
            BtnHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 =new Intent(MovieDetailActivity.this,MainActivity.class);
                    startActivity(intent1);
                }
            });




            //set animation
            MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animtion));
            play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animtion));

        }
    }

}