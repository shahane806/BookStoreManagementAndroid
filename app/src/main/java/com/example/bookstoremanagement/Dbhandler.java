package com.example.bookstoremanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Dbhandler extends SQLiteOpenHelper {
    public Dbhandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
           createUserAccounts(db);
           createAdminAccount(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createCartTable(SQLiteDatabase db){
        db = getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS CART(ID INTEGER, BOOKNAME TEXT, BOOKPRICE TEXT, BOOKAUTHOR TEXT)");
        db.close();

    }

    public boolean insertIntoCart(SQLiteDatabase db , String id,String bookname,String bookprice,String bookauthor){

        try{
            db =getWritableDatabase();
            ContentValues v = new ContentValues();
            v.put("id",id);
            v.put("bookname",bookname);
            v.put("bookprice",bookprice);
            v.put("bookauthor",bookauthor);
            db.insert("CART",null,v);
            db.close();
        }catch (Exception e){
            return false;
        }
        return true;
    }
    BooksModel model;
    ArrayList<BooksModel> arrayList;
    public ArrayList readCart(SQLiteDatabase db){
        db = getReadableDatabase();
        String query = "SELECT * FROM CART";
        Cursor c = db.rawQuery(query,null);
        arrayList = new ArrayList();
        c.moveToPrevious();
        while(c.moveToNext()){
            model = new BooksModel();

            model.setId(c.getString(0));
            model.setBookName(c.getString(1));
            model.setBookPrice(Double.valueOf(c.getString(2)));
            model.setBookAuthor(c.getString(3));


            arrayList.add(model);

        }
        db.close();
        return arrayList;
    }
    public void createBookTable(SQLiteDatabase db){
        db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS BOOKS(ID INTEGER PRIMARY KEY AUTOINCREMENT,BOOKNAME TEXT,BOOKPRICE DOUBLE,BOOKAUTHOR TEXT)");
        db.close();
    }
    public boolean insertBook(SQLiteDatabase db,
         String bookname,Double bookprice,String bookauthor
    ){
        try{
            db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("bookname",bookname);
            values.put("bookprice",bookprice);
            values.put("bookauthor",bookauthor);
            db.insert("BOOKS",null,values);
            db.close();
        }
        catch (Exception e){
            e.printStackTrace();
            return  false;
        }
        return true;
    }
    public boolean deleteBook(SQLiteDatabase db,int id){
        try{
            db = getWritableDatabase();
            String[] s = new String[1];
            s[0] = String.valueOf(id);
            db.delete("BOOKS","ID = ?",s);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean updateBook(SQLiteDatabase db,int ID,String BookName,String BookPrice,String BookAuthor){
        db = getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            String[]s = new String[1];
            values.put("bookname",BookName);
            values.put("bookprice",BookPrice);
            values.put("bookauthor",BookAuthor);
            s[0] = String.valueOf(ID);
            db.update("BOOKS",values,"ID=?",s);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public StringBuffer searchBook(SQLiteDatabase db , int id){
        StringBuffer b = new StringBuffer();
        db = getReadableDatabase();
        String query="SELECT * FROM BOOKS WHERE ID=?";
        String[]args = new String[1];
        args[0] = String.valueOf(id);

        Cursor c = db.rawQuery(query,args);
        while(c.moveToNext()){
            b.append(c.getString(0)+"\n"+c.getString(1)+"\n"+c.getString(2)+"\n"+c.getString(3));
        }
        return b;
    }

    public ArrayList showAllBooks(SQLiteDatabase db){
        db = getReadableDatabase();
        String query = "SELECT * FROM BOOKS";
        Cursor c = db.rawQuery(query,null);
        arrayList = new ArrayList();
        c.moveToPrevious();
        while(c.moveToNext()){
            model = new BooksModel();

            model.setId(c.getString(0));
            model.setBookName(c.getString(1));
            model.setBookPrice(Double.valueOf(c.getString(2)));
            model.setBookAuthor(c.getString(3));


            arrayList.add(model);

        }
        db.close();
        return arrayList;
    }

    public void createUserAccounts(SQLiteDatabase db){
        db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS USER(ID INT PRIMARY KEY ,USERNAME TEXT,PASSWORD TEXT)");
        db.close();
    }
    public void createAdminAccount(SQLiteDatabase db){
        db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS ADMIN(ID INT PRIMARY KEY, USERNAME TEXT,PASSWORD TEXT)");
        db.close();
    }
    public void deleteAllRowsFromAdminTable(SQLiteDatabase db){
        db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS 'ADMIN'");
        createAdminAccount(db);
        db.close();
    }

    public void deleteAllRowsFromUserTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS 'USER'");
        createUserAccounts(db);
        db.close();
    }
    public boolean insert_username_password(String username,String password){
      try{
          SQLiteDatabase db = this.getWritableDatabase();
          ContentValues values = new ContentValues();
          values.put("username",username);
          values.put("password",password);

          db.insert("USER",null,values);
          db.close();
      }catch (Exception e){
          return false;
      }
        return true;
    }
    public boolean insert_admin_username_password(String adminUserName,String adminPassword){
      try{
          SQLiteDatabase db = this.getWritableDatabase();
          ContentValues v = new ContentValues();
          v.put("username",adminUserName);
          v.put("password",adminPassword);
          db.insert("ADMIN",null,v);
          db.close();

      }catch (Exception e){
         return false;
      }
      return true;
    }
    public StringBuffer read_admin_username_password(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM ADMIN";

        Cursor c = db.rawQuery(sql,null);
        StringBuffer b = new StringBuffer();

        while(c.moveToNext()){
            b.append(c.getString(1)+"\n"+c.getString(2)+"\n");

        }
        db.close();
        return b;
    }
    public StringBuffer read_username_password(SQLiteDatabase db){
        db = this.getReadableDatabase();
        String sql = "SELECT * FROM USER";

        Cursor c = db.rawQuery(sql,null);
       StringBuffer b = new StringBuffer();

        while(c.moveToNext()){
            b.append(c.getString(1)+"\n"+c.getString(2)+"\n");

        }
        db.close();
        return b;
    }
    public boolean forgetPassword(SQLiteDatabase db,String username,String new_password){
        try{
            db = this.getWritableDatabase();
            deleteAllRowsFromUserTable();
            insert_username_password(username,new_password);
            db.close();
        }catch (Exception e){
            e.getMessage();
            return false;
        }
        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS 'USER'");

        onCreate(db);
        db.close();
    }
}
