package com.lut.wyh.BookStore.entity;

public class ShoppingTrolley {
    private Integer id;
    private String username;
    private String shoptrobookid;
    private String shoptrobookname;
    private String shoptrobookurl;
    private String shoptrobookprice;

    public ShoppingTrolley(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public ShoppingTrolley(Integer id, String username, String shoptrobookid, String shoptrobookname, String shoptrobookurl, String shoptrobookprice) {
        this.id = id;
        this.username = username;
        this.shoptrobookid = shoptrobookid;
        this.shoptrobookname = shoptrobookname;
        this.shoptrobookurl = shoptrobookurl;
        this.shoptrobookprice = shoptrobookprice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShoptrobookid() {
        return shoptrobookid;
    }

    public void setShoptrobookid(String shoptrobookid) {
        this.shoptrobookid = shoptrobookid;
    }

    public String getShoptrobookname() {
        return shoptrobookname;
    }

    public void setShoptrobookname(String shoptrobookname) {
        this.shoptrobookname = shoptrobookname;
    }

    public String getShoptrobookurl() {
        return shoptrobookurl;
    }

    public void setShoptrobookurl(String shoptrobookurl) {
        this.shoptrobookurl = shoptrobookurl;
    }

    public String getShoptrobookprice() {
        return shoptrobookprice;
    }

    public void setShoptrobookprice(String shoptrobookprice) {
        this.shoptrobookprice = shoptrobookprice;
    }

    @Override
    public String toString() {
        return "ShoppingTrolley{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", shoptrobookid='" + shoptrobookid + '\'' +
                ", shoptrobookname='" + shoptrobookname + '\'' +
                ", shoptrobookurl='" + shoptrobookurl + '\'' +
                ", shoptrobookprice='" + shoptrobookprice + '\'' +
                '}';
    }
}
