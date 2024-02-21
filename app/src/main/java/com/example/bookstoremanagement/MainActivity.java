package com.example.bookstoremanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Dbhandler db;
    RecyclerView showAllBooks;
    SQLiteDatabase database;
    BooksModel booksModel = new BooksModel();

    ArrayList h;

    BooksAdapter adapter;
    FloatingActionButton floatingActionButton,search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showAllBooks = findViewById(R.id.recyclerViewShowAllBooks);
        floatingActionButton = findViewById(R.id.cartButton);
        search = findViewById(R.id.search);

        try{
            db = new Dbhandler(MainActivity.this,"BOOKSTOREDATABASE",null,1);
            h =   db.showAllBooks(database);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Cart.class);
                startActivity(i);
            }
        });search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SearchBook.class);
                startActivity(i);
            }
        });
        if(h == null){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("No Books Found")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }
        else{


            try {
                adapter= new BooksAdapter(MainActivity.this,h);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
            showAllBooks.setLayoutManager(linearLayoutManager);
            showAllBooks.setAdapter(adapter);


        }
    }
}