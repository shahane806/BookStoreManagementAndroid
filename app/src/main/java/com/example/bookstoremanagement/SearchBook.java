package com.example.bookstoremanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchBook extends AppCompatActivity {
    EditText searchBookId;
    TextView data;
    Button search;
    Dbhandler db;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);
        search = findViewById(R.id.searchButton);
        searchBookId = findViewById(R.id.searchBookId);
        data = findViewById(R.id.searchData);
        db = new Dbhandler(SearchBook.this,"BOOKSTOREDATABASE",null,1);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = searchBookId.getText().toString();
                StringBuffer s = db.searchBook(database,Integer.parseInt(id));
                String data2 = s.toString();
                data.setText(data2);
            }
        });
    }
}