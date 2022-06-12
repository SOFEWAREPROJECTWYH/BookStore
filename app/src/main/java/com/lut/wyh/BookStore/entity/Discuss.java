package com.lut.wyh.BookStore.entity;


public class Discuss {
    private Integer bookid;
    private String bookname;
    private Integer id;
    private String name;
    private String info;
    private String url;

    public Discuss(Integer bookid, String bookname, Integer id, String name, String info, String url) {
        this.bookid = bookid;
        this.bookname = bookname;
        this.id = id;
        this.name = name;
        this.info = info;
        this.url = url;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Discuss{" +
                "bookid=" + bookid +
                ", bookname='" + bookname + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
