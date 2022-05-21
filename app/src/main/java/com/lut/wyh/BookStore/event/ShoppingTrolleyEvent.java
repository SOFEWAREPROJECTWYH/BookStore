package com.lut.wyh.BookStore.event;

public class ShoppingTrolleyEvent {
    private Integer id;
    private String username;
    private String shoptrobookid;
    private String shoptrobookname;
    private String shoptrobookurl;

    public ShoppingTrolleyEvent(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public ShoppingTrolleyEvent(Integer id, String username, String shoptrobookid, String shoptrobookname, String shoptrobookurl) {
        this.id = id;
        this.username = username;
        this.shoptrobookid = shoptrobookid;
        this.shoptrobookname = shoptrobookname;
        this.shoptrobookurl = shoptrobookurl;
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

    @Override
    public String toString() {
        return "ShoppingTrolleyEvent{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", shoptrobookid='" + shoptrobookid + '\'' +
                ", shoptrobookname='" + shoptrobookname + '\'' +
                ", shoptrobookurl='" + shoptrobookurl + '\'' +
                '}';
    }
}
