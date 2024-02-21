package com.example.bookstoremanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseUserOrOwner extends AppCompatActivity {
Button userLogin,AdminLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user_or_owner);
        userLogin = findViewById(R.id.User_Login);
        AdminLogin = findViewById(R.id.Admin_Login);
        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i);
            }
        });
        AdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Admin_Login.class);
                startActivity(i);
            }
        });
    }
}