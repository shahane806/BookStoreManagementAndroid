package com.example.bookstoremanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        runThread();
    }
    public void runThread(){
        Thread t = new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                    Thread.sleep(5000);
                    Intent i = new Intent(getApplicationContext(),ChooseUserOrOwner.class);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}