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

public class ForgetPassword extends AppCompatActivity {
    EditText username,new_password,confirm_password;
    TextView gotoLogin,forgetUsername;
    Button changePassword;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        username = findViewById(R.id.forgetPasswordUserName);
        gotoLogin = findViewById(R.id.login_page);
        new_password = findViewById(R.id.forgetPasswordNewPassword);
        confirm_password = findViewById(R.id.forgetPasswordConfirmPassword);
        changePassword = findViewById(R.id.forgetPasswordButton);
        Dbhandler dbhandler = new Dbhandler(ForgetPassword.this,"BOOKSTOREDATABASE",null,1);
        forgetUsername = findViewById(R.id.forgetUserName);

        StringBuffer sb = dbhandler.read_username_password(db);
        String string= sb.toString();
        String[] s1 =  string.split("\n");
        String dbusername = s1[0];
        Log.e(TAG,s1[0],null);
        forgetUsername.setText("Username : "+dbusername);

        changePassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String un,np,cp;

                un = String.valueOf(username.getText());
                np = String.valueOf(new_password.getText());
                cp = String.valueOf(confirm_password.getText());
                try{

                    Log.e(TAG,un+np+cp);
                    if(np.equals(cp)){
                        StringBuffer b;
                        String s;
                        try{
                            b = dbhandler.read_username_password(db);
                            s = b.toString();
                            String[] s1 =  s.split("\n");
                            String dbusername = s1[0];
                            Log.e(TAG,s1[0],null);
                            forgetUsername.setText("Username : "+dbusername);
                            if(un.equals(dbusername)){
                                boolean f = dbhandler.forgetPassword(db,un,np);
                                if(f){
                                    new AlertDialog.Builder(ForgetPassword.this)
                                            .setTitle("PASSWORD CHANGED Successfully")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            }).show();
                                }
                                else{
                                    new AlertDialog.Builder(ForgetPassword.this)
                                            .setTitle("PASSWORD NOT CHANGED ")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            }).show();
                                }
                            }
                        }catch (
                                Exception e
                        ){
                            e.printStackTrace();
                        }
                    }
                    else{
                        new AlertDialog.Builder(ForgetPassword.this)
                                .setTitle("NEW PASSWORD AND CONFIRM PASSWORD IS NOT SAME")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForgetPassword.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}