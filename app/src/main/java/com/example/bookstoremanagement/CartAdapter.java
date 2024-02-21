package com.example.bookstoremanagement;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;



public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewHolder>  {
    Context context;
    SQLiteDatabase db;
    ArrayList<BooksModel> l;
    public CartAdapter(Context c,ArrayList<BooksModel> l) throws JSONException {
        this.l = l;
        this.context = c;
    }
    CartAdapter(){}




    @NonNull
    @Override

    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_fragment,parent,false);

        return new viewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.e("Position", String.valueOf(position));

        holder.ID.setText("ID : "+l.get(position).getId());
        holder.BookName.setText("Book Name : "+l.get(position).getBookName());
        holder.BookPrice.setText(String.valueOf(l.get(position).getBookPrice()));
        holder.BookAuthor.setText("Book Author : "+l.get(position).getBookAuthor());

    }


    @Override
    public int getItemCount() {
        return l.size();
    }


    public void setContext(Context context) {
        this.context = context;
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView BookName,BookPrice,BookAuthor,ID;

        public viewHolder(@NonNull View itemView) {

            super(itemView);


            context  = itemView.getContext();
            ID = itemView.findViewById(R.id.ID);
            BookName = itemView.findViewById(R.id.BookName);
            BookAuthor = itemView.findViewById(R.id.BookAuthor);
            BookPrice = itemView.findViewById(R.id.BookPrice);

        }






    }

}
