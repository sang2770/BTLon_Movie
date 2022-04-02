package com.example.btlon_movie.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.btlon_movie.DownloadImageTask;
import com.example.btlon_movie.R;
import com.example.btlon_movie.adapter.MovieAdapter;
import com.example.btlon_movie.models.Movie;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView MovieThumbnailImg, MovieCoverImg;
    private TextView tv_title, tv_description, TxtYear, TxtRate, TxtLanguage;
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

    void iniView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String movieTitle = bundle.getString("title");
            String imageResoureceId = bundle.getString("imgUrl");
            String imageCover = bundle.getString("imgCover");
            String Rate = bundle.getString("rating");
            String Language = bundle.getString("Language");
            int Year = bundle.getInt("Year");
            boolean like = bundle.getString("rating").equals("Like") ? true : false;
            int ID = bundle.getInt("ID");


            //Play Video
            play_fab = findViewById(R.id.play_fab);
            play_fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentPlay = new Intent(MovieDetailActivity.this, VideoActivity.class);
                    intentPlay.putExtra("IdVideo", bundle.getString("Link"));
                    startActivity(intentPlay);
                }
            });
            MovieThumbnailImg = findViewById(R.id.detail_movie_img);
            new DownloadImageTask(MovieThumbnailImg)
                    .execute(imageResoureceId);


            MovieCoverImg = findViewById(R.id.detail_movie_cover);
            new DownloadImageTask(MovieCoverImg)
                    .execute(imageCover);

            tv_title = findViewById(R.id.detail_movie_title);
            tv_title.setText(movieTitle);
            getSupportActionBar().setTitle(movieTitle);
            tv_description = findViewById(R.id.detail_movie_desc);
            TxtYear = findViewById(R.id.TxtNam);
            TxtYear.setText("Năm: " + Year);
            TxtLanguage = findViewById(R.id.TxtNgonNgu);
            TxtLanguage.setText("Ngôn ngữ: " + Language);
            TxtRate = findViewById(R.id.TxtDanhGia);
            TxtRate.setText("Đánh giá: " + Rate);

            Cklike = findViewById(R.id.CkLike);
            //lay du lieu trong firebasedata
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myref = database.getReference();
            if (user != null) {
                String IDuser = user.getUid();
                myref.child("User/" + IDuser + "/MyList").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(""+ID)){
                            Cklike.setChecked(true);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }



            Cklike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if (user != null) {
                        String IDuer = user.getUid();


                        if(b){
                            myref.child("User/" + IDuer + "/MyList/" + ID + "/ID").setValue(ID);
                        }
                        else{
                            myref.child("User/" + IDuer + "/MyList/" + ID).removeValue();
                        }


                    }
                }
            });
            BtnHome = findViewById(R.id.btn_home);
            BtnHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(MovieDetailActivity.this, MainActivity.class);
                    startActivity(intent1);
                }
            });


            //set animation
            MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animtion));
            play_fab.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animtion));

        }
    }


}