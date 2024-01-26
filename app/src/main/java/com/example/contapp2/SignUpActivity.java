package com.example.contapp2;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

import com.example.contapp2.databinding.ActivitySignUpBinding;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    DbManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbManager = new DbManager(this);


        binding.SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.regEmail.getText().toString();
                String name = binding.regName.getText().toString();
                String phone = binding.regTelephone.getText().toString();
                String password = binding.regPassword.getText().toString();
                String confirmPass = binding.regConfirmPass.getText().toString();
                System.out.println("hellooooo");

                if (email.equals("") || password.equals("") || confirmPass.equals("") || phone.equals("") || name.equals(""))
                    Toast.makeText(SignUpActivity.this, "All fields must be filled", Toast.LENGTH_SHORT);
                else{
                    if (password.equals(confirmPass)){
                        Boolean checkUserEmail = dbManager.checkEmailExist(email);

                        if(checkUserEmail == false) {
                            Boolean insert = dbManager.insertData(email, name, phone, password);

                            if (insert == true) {
                                Toast.makeText(SignUpActivity.this, "SignUp Succesful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(SignUpActivity.this, "User is alreasy exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignUpActivity.this, "Invalid pasword", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        binding.signInRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);

            }
        });










    }
}