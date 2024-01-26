package com.example.contapp2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.contapp2.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {
    ActivitySignInBinding binding;
    DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbManager = new DbManager(this);

        binding.SignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.edtEmail.getText().toString();
                String password = binding.edtPassword.getText().toString();

                if (email.equals("")|| password.equals("")){
                    Toast.makeText(SignInActivity.this, "All fields must be added", Toast.LENGTH_SHORT).show();

                }else{
                    Boolean checkUser = dbManager.checkPassword(email, password);

                    if (checkUser == true){
                        Toast.makeText(SignInActivity.this, "SignIn successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(SignInActivity.this, "Invalid User or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

}