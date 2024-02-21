package com.example.bookstoremanagement;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowAllBooks extends AppCompatActivity {
    Dbhandler db;
    RecyclerView showAllBooks;
    SQLiteDatabase database;
    BooksModel booksModel = new BooksModel();

    ArrayList<BooksModel> h;

    BooksAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_books);
        showAllBooks = findViewById(R.id.recyclerViewShowAllBooks);
        db = new Dbhandler(ShowAllBooks.this,"BOOKSTOREDATABASE",null,1);
        h =   db.showAllBooks(database);

        if(h == null){
            new AlertDialog.Builder(ShowAllBooks.this)
                    .setTitle("No Books Found")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }
        else{


            try {
                adapter= new BooksAdapter(ShowAllBooks.this,h);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowAllBooks.this,LinearLayoutManager.VERTICAL,false);
                showAllBooks.setLayoutManager(linearLayoutManager);
                showAllBooks.setAdapter(adapter);


        }

    }

}