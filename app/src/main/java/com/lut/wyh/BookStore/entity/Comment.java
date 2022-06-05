package com.lut.wyh.BookStore.entity;

public class Comment {
    private String id;
    private String name;
    private String url;
    private String title;
    private String content;
    private String bookurl;
    private String like;
    private String comment;
    private String share;

    public Comment(String title) {
        this.title = title;
    }

    public Comment(String id, String name, String url, String title, String content, String bookurl, String like, String comment, String share) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.title = title;
        this.content = content;
        this.bookurl = bookurl;
        this.like = like;
        this.comment = comment;
        this.share = share;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBookurl() {
        return bookurl;
    }

    public void setBookurl(String bookurl) {
        this.bookurl = bookurl;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", bookurl='" + bookurl + '\'' +
                ", like='" + like + '\'' +
                ", comment='" + comment + '\'' +
                ", share='" + share + '\'' +
                '}';
    }
}
