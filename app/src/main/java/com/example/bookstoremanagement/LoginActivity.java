package com.example.bookstoremanagement;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText username ,password;
    TextView createAccount,forgetPassword;
    public SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.createAccountButton);
        username = findViewById(R.id.createAccountUserName);
        password = findViewById(R.id.createAccountPassword);
        createAccount = findViewById(R.id.create_account);
        forgetPassword = findViewById(R.id.forget_password_link);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateAccountUser.class);
                startActivity(i);
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ForgetPassword.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              try{
                  String un,pass;
                  un = String.valueOf(username.getText());
                  pass = String.valueOf(password.getText());
                  Dbhandler DB = new Dbhandler(LoginActivity.this,"BOOKSTOREDATABASE",null,1);
                  StringBuffer b;
                  String s;
                  try{
                     b = DB.read_username_password(db);
                     s = b.toString();
                      String[] s1 =  s.split("\n");


                      String dbusername = s1[0],dbpass = s1[1];
                      Log.e(TAG,s1[0]+s1[1],null);
                      if(un.equals(dbusername)){
                          if(pass.equals(dbpass)){
                              Intent i = new Intent(getApplicationContext(), MainActivity.class);
                              startActivity(i);
                          }
                          else{
                              new AlertDialog.Builder(LoginActivity.this).setTitle("Username or Password is incorrect")
                                      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                          @Override
                                          public void onClick(DialogInterface dialog, int which) {

                                          }
                                      }).show();
                          }
                      }else{
                          new AlertDialog.Builder(LoginActivity.this).setTitle("NO Account Found")
                                  .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                      @Override
                                      public void onClick(DialogInterface dialog, int which) {

                                      }
                                  }).show();
                      }
                  }catch (Exception e){
                      new AlertDialog.Builder(LoginActivity.this).setTitle("NO Account Found")
                              .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialog, int which) {

                                  }
                              }).show();
                  }

              }catch (Exception e){
                  new AlertDialog.Builder(getApplicationContext())
                          .setTitle("No Account Found")
                          .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {

                              }
                          });
              }
            }
        });

    }
}