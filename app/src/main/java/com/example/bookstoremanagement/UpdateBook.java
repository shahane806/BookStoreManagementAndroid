package com.example.bookstoremanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateBook extends AppCompatActivity {
    EditText fetchBookId,updatebookId,updatebookName,updateBookPrice,updateBookAuthor;
    TextView data;
    Button update,fetchButton;
    Dbhandler db;
    SQLiteDatabase database;
    String ui,un,up,ua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);
        fetchBookId = findViewById(R.id.updateBookId);
        updatebookId = findViewById(R.id.updatedBookId);
        updatebookName = findViewById(R.id.updatedBookName);
        updateBookPrice = findViewById(R.id.updatedBookPrice);
        updateBookAuthor = findViewById(R.id.updatedBookAuthor);
        update = findViewById(R.id.update);
        fetchButton = findViewById(R.id.fetchButton);
        data = findViewById(R.id.data);
        updatebookId.setVisibility(View.INVISIBLE);
        updatebookName.setVisibility(View.INVISIBLE);
        updateBookAuthor.setVisibility(View.INVISIBLE);
        updateBookPrice.setVisibility(View.INVISIBLE);
        update.setVisibility(View.INVISIBLE);
        db = new Dbhandler(UpdateBook.this,"BOOKSTOREDATABASE",null,1);
        fetchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ui = String.valueOf(fetchBookId.getText());
                StringBuffer s = db.searchBook(database,Integer.parseInt(ui));
                if(s.length()==0){
                    new AlertDialog.Builder(UpdateBook.this)
                            .setTitle("NO BOOK FOUND")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }
                else{
                    String s1 = s.toString();
                    data.setText(s1);
                    updatebookId.setVisibility(View.VISIBLE);
                    updatebookName.setVisibility(View.VISIBLE);
                    updateBookAuthor.setVisibility(View.VISIBLE);
                    updateBookPrice.setVisibility(View.VISIBLE);
                    update.setVisibility(View.VISIBLE);
                }
            }
        });



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ui = String.valueOf(updatebookId.getText());
                un = String.valueOf(updatebookName.getText());
                up = String.valueOf(updateBookPrice.getText());
                ua = String.valueOf(updateBookAuthor.getText());
               boolean f =  db.updateBook(database,Integer.parseInt(ui),un,up,ua);
               if (f){
                   new AlertDialog.Builder(UpdateBook.this)
                           .setTitle("Data Updated")
                           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {

                               }
                           }).show();
               }else{
                   new AlertDialog.Builder(UpdateBook.this)
                           .setTitle("Data not Updated")
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