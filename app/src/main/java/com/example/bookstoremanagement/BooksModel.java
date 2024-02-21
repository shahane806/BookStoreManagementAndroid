package com.example.bookstoremanagement;


public class BooksModel {
     public  String BookName;
     public  Double BookPrice;
     public  String BookAuthor;
     public  String Id;
     BooksModel(){}
    BooksModel(String bn,Double bp,String ba,String id){
        this.BookName = bn;
        this.BookPrice = bp;
        this.BookAuthor = ba;
        this.Id = id;
    }

    public  void setId(String id) {
        Id = id;
    }

    public  String getId() {
        return Id;
    }

    public Double getBookPrice() {
        return BookPrice;
    }

    public String getBookAuthor() {
        return BookAuthor;
    }

    public String getBookName() {
        return BookName;
    }

    public  void setBookAuthor(String bookAuthor) {
        BookAuthor = bookAuthor;
    }

    public  void setBookName(String bookName) {
        BookName = bookName;
    }

    public  void setBookPrice(Double bookPrice) {
        BookPrice = bookPrice;
    }
}
