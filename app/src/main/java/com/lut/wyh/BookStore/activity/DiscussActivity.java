package com.lut.wyh.BookStore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.entity.User;

public class DiscussActivity extends AppCompatActivity {
    private User userInfo;
    private TextView bookname;
    private TextView bookprice;
    private TextView booksource;
    private TextView booktype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);
        initView();
    }
    public void initView(){
        bookname=findViewById(R.id.bookname);
        bookprice=findViewById(R.id.bookprice);
        booksource=findViewById(R.id.booksource);
        booktype=findViewById(R.id.booktype);
    }
}