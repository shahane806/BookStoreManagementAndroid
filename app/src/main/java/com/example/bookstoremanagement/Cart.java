package com.example.bookstoremanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    RecyclerView cartRecyclerView;
    CartAdapter adapter;
    ArrayList h;
    Dbhandler dbhandler;
    FloatingActionButton fab;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        fab  = findViewById(R.id.search);
        try {
            dbhandler = new Dbhandler(Cart.this,"BOOKSTOREDATABASE",null,1);
            h = dbhandler.readCart(db);
            adapter= new CartAdapter(Cart.this,h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Cart.this,SearchBook.class);
                startActivity(i);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Cart.this,LinearLayoutManager.VERTICAL,false);
        cartRecyclerView.setLayoutManager(linearLayoutManager);
        cartRecyclerView.setAdapter(adapter);

    }
}