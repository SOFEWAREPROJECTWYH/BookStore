package com.lut.wyh.BookStore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.entity.Discuss;
import com.lut.wyh.BookStore.entity.User;
import com.lut.wyh.BookStore.presenter.DiscussPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class DiscussActivity extends AppCompatActivity {
    private User userInfo;
    private TextView bookname;
    private TextView bookprice;
    private TextView booktype;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }
    public void initView(){
        bookname=findViewById(R.id.bookname);
        bookprice=findViewById(R.id.bookprice);
        booktype=findViewById(R.id.booktype);
        bookImage=findViewById(R.id.bookimage);
    }
    @SuppressLint("SetTextI18n")
    public void initData(){
        Intent intent=this.getIntent();
        bookname.setText("书名"+intent.getStringExtra("name"));
        bookprice.setText("价格："+"￥"+intent.getStringExtra("price"));
        booktype.setText("类型"+intent.getStringExtra("type"));
        Glide.with(getApplicationContext()).load(intent.getStringExtra("url")).into(bookImage);
        new DiscussPresenter().loadData();

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(List<Discuss> discussList){

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}