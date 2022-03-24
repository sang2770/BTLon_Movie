package com.example.btlon_movie.ui;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btlon_movie.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    EditText TxtEmail, TxtPassword;
    Button btnLogin;
    TextView Change;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initUi();
        initEvent();
    }
    private void initUi() {
        TxtEmail=findViewById(R.id.RegisterEmail);
        TxtPassword=findViewById(R.id.RegisterPassword);
        btnLogin=findViewById(R.id.BtnRegister);
        Change=findViewById(R.id.ChangeLogin);
        progressDialog=new ProgressDialog(this);

    }
    private void initEvent()
    {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();

            }
        });
    }
    private void Register() {
        String email, password;
        email=TxtEmail.getText().toString().trim();
        password=TxtPassword.getText().toString().trim();
        if(email.length()==0 || password.length()==0)
        {
            Toast.makeText(this, "Dữ liệu không đúng định dạng", Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        progressDialog.show();
        Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success
                            Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            // fails
                            Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}