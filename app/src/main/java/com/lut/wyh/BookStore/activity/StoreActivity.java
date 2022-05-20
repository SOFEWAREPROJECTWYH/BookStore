package com.lut.wyh.BookStore.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.adapter.ListPagerAdapter;
import com.lut.wyh.BookStore.entity.User;
import com.lut.wyh.BookStore.entity.UserNick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {
    private final String TAG="StoreActivity";
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_store, R.id.navigation_notifications,R.id.navigation_shoppingtrolley,R.id.navigation_home)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        convey();

    }
    public void convey(){
        Intent intent=this.getIntent();
        username=intent.getStringExtra("username");
        UserNick userNick=new UserNick(username,null);
        EventBus.getDefault().post(userNick);
    }
}