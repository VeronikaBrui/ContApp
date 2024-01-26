package com.example.contapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    Button Login, Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void Login (View view) {
        Intent intent = new Intent(WelcomeActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    public void SignUp(View view) {
        Intent intent = new Intent(WelcomeActivity.this, SignUpActivity.class);
        startActivity(intent);
    }



}
