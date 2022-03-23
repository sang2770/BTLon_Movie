package com.example.btlon_movie.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btlon_movie.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity {
    Button BtnLogout;
    TextView TxtEmail, TxtType;
    BottomNavigationView menu;
    ImageView Avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        InitUi();
        InitEnvent();
    }

    private void InitUi() {
        BtnLogout=findViewById(R.id.btnLogout);
        TxtEmail=findViewById(R.id.UserEmail);
        TxtType=findViewById(R.id.UserType);
        Avatar=findViewById(R.id.ImageAvatar);
        Avatar.setImageResource(R.drawable.joker);
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            String email = user.getEmail();
            TxtEmail.setText(email);
        }
        //Menu bottom
        menu=findViewById(R.id.Navigation_User);
        menu.setSelectedItemId(R.id.UserInfo);

    }
    private void InitEnvent() {
        BtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(UserActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mainActivity:
                        startActivity(new Intent(UserActivity.this, MainActivity.class));
                        overridePendingTransition(0,0);
                        finishAffinity();
                        return  true;
                    case R.id.UserInfo:
                        return  true;
                    case R.id.myMovie:
                        startActivity(new Intent(UserActivity.this, MyMovie.class));
                        overridePendingTransition(0,0);
                        finishAffinity();
                        return true;

                }
                return false;
            }
        });
    }
}