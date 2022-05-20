package com.lut.wyh.BookStore.entity;

public class UserNick {
    private String username;
    private String url;

    public UserNick(String username, String url) {
        this.username = username;
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "UserNick{" +
                "username='" + username + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
