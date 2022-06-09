package com.lut.wyh.BookStore.entity;

public class Collect {
    private Integer id;
    private String name;
    private Integer bookid;
    private String bookname;

    public Collect(Integer id, String name, Integer bookid, String bookname) {
        this.id = id;
        this.name = name;
        this.bookid = bookid;
        this.bookname = bookname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Collect{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bookid=" + bookid +
                ", bookname='" + bookname + '\'' +
                '}';
    }
}
