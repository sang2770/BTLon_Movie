package com.example.btlon_movie.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.btlon_movie.R;
import com.example.btlon_movie.adapter.MovieAdapter;
import com.example.btlon_movie.adapter.MyMovieAdapter;
import com.example.btlon_movie.models.Category;
import com.example.btlon_movie.models.Country;
import com.example.btlon_movie.models.Movie;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyMovie extends AppCompatActivity {
    MyMovieAdapter myMovieAdapter;
    ArrayList<Movie> data;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_movie);
        listView=findViewById(R.id.ListMovie);
        //Load dữ liệu
        data=new ArrayList<Movie>();
        getListdata();
       /*data.add(new Movie(1,"Joker",R.drawable.joker+"","phim moi",R.drawable.joker+"","",null,null,"","",1111));
        data.add(new Movie(2,"Joker",R.drawable.joker+"","phim moi",R.drawable.joker+"","",null,null,"","",1111));

*//*
        *//*data.add(new Movie(1,"Joker",1,"Phim mới", 1,"","",""));
        data.add(new Movie(1,"Joker",1,"Phim mới", 1,"", "", ""));
        data.add(new Movie(3,"Joker",1,"Phim mới", 1,"", "", ""));*//*
        myMovieAdapter=new MyMovieAdapter(this, data);
        listView.setAdapter(myMovieAdapter);*/
        //Menu bottom
        BottomNavigationView menu=findViewById(R.id.Navigation_MyMovie);
        menu.setSelectedItemId(R.id.myMovie);
        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mainActivity:
                        startActivity(new Intent(MyMovie.this, MainActivity.class));
                        overridePendingTransition(0,0);
                        finishAffinity();
                        return  true;
                    case R.id.UserInfo:
                        startActivity(new Intent(MyMovie.this, UserActivity.class));
                        overridePendingTransition(0,0);
                        finishAffinity();
                        return  true;
                    case R.id.myMovie:
                        return true;

                }
                return false;
            }
        });
    }
    private void getListdata(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myref= database.getReference();

        myref.child("Movie").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot sanp:snapshot.getChildren()){
                    Movie movie=sanp.getValue(Movie.class);
                    if(movie.getRating().equals("Like")){
                        data.add(movie);
                    }

                    }
                myMovieAdapter=new MyMovieAdapter(MyMovie.this, data);
                listView.setAdapter(myMovieAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}