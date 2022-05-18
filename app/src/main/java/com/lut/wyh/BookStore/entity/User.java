package com.lut.wyh.BookStore.entity;

public class User {
    private Integer id;
    private String name;
    private String password;
    private String phonenumber;
    private String email;
    private Integer authority;
    private String shoppingtrolley;
    private String url;

    public User(Integer id, String password) {
        this.id = id;
        this.password = password;
    }

    public User(Integer id, String name, String password, String phonenumber, String email, Integer authority, String shoppingtrolley, String url) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phonenumber = phonenumber;
        this.email = email;
        this.authority = authority;
        this.shoppingtrolley = shoppingtrolley;
        this.url = url;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    public String getShoppingtrolley() {
        return shoppingtrolley;
    }

    public void setShoppingtrolley(String shoppingtrolley) {
        this.shoppingtrolley = shoppingtrolley;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", authority=" + authority +
                ", shoppingtrolley='" + shoppingtrolley + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
