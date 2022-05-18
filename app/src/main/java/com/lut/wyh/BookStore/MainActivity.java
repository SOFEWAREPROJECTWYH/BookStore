package com.lut.wyh.BookStore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.lut.wyh.BookStore.activity.StoreActivity;
import com.lut.wyh.BookStore.presenter.BookPresenter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.book);
        button.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, StoreActivity.class);
            startActivity(intent);
//            new BookPresenter().loadData();
        });

    }
}