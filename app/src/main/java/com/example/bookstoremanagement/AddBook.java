package com.example.bookstoremanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBook extends AppCompatActivity {
    EditText bookname , bookprice,bookauthor;
    Button submit;

    public  String bn,bp,ba;
    Dbhandler dbhandler;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        submit = findViewById(R.id.submit);
        bookname = findViewById(R.id.bookname);
        bookprice = findViewById(R.id.bookprice);
        bookauthor = findViewById(R.id.bookauthor);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bn = String.valueOf(bookname.getText());
                bp = String.valueOf(bookprice.getText());
                ba = String.valueOf(bookauthor.getText());
                dbhandler = new Dbhandler(AddBook.this,"BOOKSTOREDATABASE",null,1);
                if(bn.equals("")||bp.equals(null)||bp.equals(0)||ba.equals("")){
                    new AlertDialog.Builder(AddBook.this)
                            .setTitle("Book Name,Price,Author not be empty")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }
                else{
                    boolean f = dbhandler.insertBook(db,bn,Double.valueOf(bp),ba);
                    if(f){
                        new AlertDialog.Builder(AddBook.this)
                                .setTitle("Book Name,Price,Author Values Inserted")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
                    }else{
                        new AlertDialog.Builder(AddBook.this)
                                .setTitle("Book Name,Price,Author Values Not Inserted")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
                    }
                }
            }
        });
    }
}