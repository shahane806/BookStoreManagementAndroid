package com.example.bookstoremanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeleteBook extends AppCompatActivity {
    EditText bookid;
    Button deleteBookId;
    String d;
    Dbhandler dbhandler ;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_book);
        deleteBookId = findViewById(R.id.deleteSubmit);
        bookid = findViewById(R.id.bookIdForDelete);
        deleteBookId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = String.valueOf(bookid.getText());
                int mainId= Integer.parseInt(d);
                dbhandler = new Dbhandler(DeleteBook.this,"BOOKSTOREDATABASE",null,1);
                if(mainId!=0){
                   boolean f =  dbhandler.deleteBook(db,mainId);
                   if(f){
                       new AlertDialog.Builder(DeleteBook.this)
                               .setTitle("Book Data Deleted Successfully")
                               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {

                                   }
                               }).show();
                   }
                }
                else{
                    new AlertDialog.Builder(DeleteBook.this)
                            .setTitle("Book id not be 0")
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