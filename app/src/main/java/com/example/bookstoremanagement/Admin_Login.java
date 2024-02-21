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

public class Admin_Login extends AppCompatActivity {
    EditText adminUsername_editText,adminPassword_editText;
    String adminUsername = "",adminPassword = "";
    Button adminLoginButton;
    public Dbhandler dbhandler;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
//       adminUsername = "Admin@123";
//        adminPassword = "Admin@123";

        adminUsername_editText = findViewById(R.id.adminUsername);
        adminPassword_editText = findViewById(R.id.adminAccountPassword);
        adminLoginButton = findViewById(R.id.adminLoginButton);
        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminUsername = String.valueOf(adminUsername_editText.getText());
                adminPassword = String.valueOf(adminPassword_editText.getText());
                if(adminUsername.equals("")||adminPassword.equals("")){
                    new AlertDialog.Builder(Admin_Login.this).setTitle("AdminUsername or AdminPassword not be empty")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }

                try{
                    dbhandler = new Dbhandler(Admin_Login.this,"BOOKSTOREDATABASE",null,1);
                    dbhandler.createAdminAccount(db);
                    StringBuffer buffer =  dbhandler.read_admin_username_password();
                    String s = buffer.toString();
                    Log.e(TAG,s);
                    if(s.equals("")){
                        dbhandler.insert_admin_username_password("Admin@123","Admin@123");

                    }
                    else{
                       String []sp = s.split("\n");
                       Log.e(TAG, String.valueOf(adminUsername.equals(sp[0])));
                       Log.e(TAG, String.valueOf(adminPassword.equals(sp[1])));
                       String a,p;
                       a = sp[0].toString();
                       p = sp[1].toString();
                       if(adminUsername.equals(a)){
                           if(adminPassword.equals(p)){
                               Intent i = new Intent(Admin_Login.this,MainActivity2.class);
                               startActivity(i);
                           }
                       }else{
                           new AlertDialog.Builder(Admin_Login.this).setTitle("Username or Password is incorrect")
                                   .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                       @Override
                                       public void onClick(DialogInterface dialog, int which) {

                                       }
                                   }).show();
                       }
                    }
                }catch (Exception e){
                    e.printStackTrace();

                }
            }
        });

    }
}