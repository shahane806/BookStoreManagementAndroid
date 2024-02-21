package com.example.bookstoremanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    public Dbhandler dbhandler;
    public SQLiteDatabase db;
    Button add,delete,update,search,showAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dbhandler = new Dbhandler(MainActivity2.this,"BOOKSTOREDATABASE",null,1);
        dbhandler.createBookTable(db);
        add = findViewById(R.id.InsertBook);
        delete = findViewById(R.id.DeleteBook);
        update = findViewById(R.id.UpdateBook);
        search = findViewById(R.id.SearchBook);
        showAll = findViewById(R.id.ShowAllBooks);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent i = new Intent(MainActivity2.this,AddBook.class);
              startActivity(i);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this,DeleteBook.class);
                startActivity(i);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this,UpdateBook.class);
                startActivity(i);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this,SearchBook.class);
                startActivity(i);
            }
        });
        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this,ShowAllBooks.class);
                startActivity(i);
            }
        });

    }
}