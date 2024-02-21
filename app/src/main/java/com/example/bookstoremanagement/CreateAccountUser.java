package com.example.bookstoremanagement;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccountUser extends AppCompatActivity {
    EditText createAccountUser,createAccontPassword;
    public SQLiteDatabase db;
    Button createAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_user);
        createAccountUser = findViewById(R.id.createAccountUserName);
        createAccontPassword = findViewById(R.id.createAccountPassword);
        createAccount = findViewById(R.id.createAccountButton);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username,password;
                StringBuffer b;
                Dbhandler dbhandler;
                boolean f =false;
                username = String.valueOf(createAccountUser.getText());
                password = String.valueOf(createAccontPassword.getText());
                dbhandler= new Dbhandler(getApplicationContext(),"BOOKSTOREDATABASE",null,1);
                dbhandler.onCreate(db);

                try{

                    b = dbhandler.read_username_password(db);
                    String s = b.toString();

                    Log.e(TAG, String.valueOf(b.toString().length()));
                    if(s.length()==0){

                        try{
                            if(username!=null || password !=null){
                                f = dbhandler.insert_username_password(username,password);
                            }
                            else{
                                new AlertDialog.Builder(CreateAccountUser.this)
                                        .setTitle("Already account created try to login")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        }).show();
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        if(f){
                            new AlertDialog.Builder(CreateAccountUser.this)
                                    .setTitle("Account Created Successfully")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
                        }else{
                            new AlertDialog.Builder(CreateAccountUser.this)
                                    .setTitle("Account Not Create")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
                        }
                    }
                    else{
                        new AlertDialog.Builder(CreateAccountUser.this)
                                .setTitle("Already account created try to login")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
                    }
                }catch (Exception e){

                    new AlertDialog.Builder(CreateAccountUser.this)
                            .setTitle("Already account created try to login")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }


            }
        });
    }
}