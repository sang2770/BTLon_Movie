package com.example.btlon_movie.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.btlon_movie.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MyMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_movie);
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
                        return  true;
                    case R.id.myMovie:
                        return true;

                }
                return false;
            }
        });
    }
}