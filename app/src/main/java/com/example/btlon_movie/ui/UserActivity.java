package com.example.btlon_movie.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btlon_movie.R;
import com.example.btlon_movie.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserActivity extends AppCompatActivity {
    Button BtnLogout;
    TextView TxtEmail, TxtType;
    BottomNavigationView menu;
    ImageView Avatar;
    ProgressDialog progressDialog;
    private DatabaseReference mDatabase;
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
        Avatar.setImageResource(R.drawable.avatar);
        progressDialog=new ProgressDialog(this);
        progressDialog.show();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            String id = user.getUid();
            FirebaseDatabase database=FirebaseDatabase.getInstance();
            DatabaseReference myref= database.getReference();

            myref.child("user").child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    progressDialog.dismiss();
                    String email=dataSnapshot.child("account").getValue(String.class);
                    String type=dataSnapshot.child("type").getValue(String.class);
//                   User user1=dataSnapshot.getValue(User.class);
                    TxtEmail.setText(email);
                    TxtType.setText(type);

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    progressDialog.dismiss();
                    // Getting Post failed, log a message
                    Toast.makeText(UserActivity.this, "Lấy dữ liệu không thành công", Toast.LENGTH_SHORT).show();
                }
            }
            );
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