package com.lut.wyh.BookStore.entity;


import java.util.List;

public class Inventory {
    private Integer bookid;
    private String bookname;
    private Integer price;
    private Integer quantity;
    private String type;
    private String url;

    public Inventory(Integer bookid) {
        this.bookid = bookid;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "bookid=" + bookid +
                ", bookname='" + bookname + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
