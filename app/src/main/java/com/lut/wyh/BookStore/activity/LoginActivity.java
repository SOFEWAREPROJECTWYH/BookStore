package com.lut.wyh.BookStore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.entity.User;
import com.lut.wyh.BookStore.presenter.UserPresenter;

import org.greenrobot.eventbus.EventBus;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextAccount;
    private EditText editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextAccount=findViewById(R.id.account);
        editTextPassword=findViewById(R.id.password);
        EventBus.getDefault().register(this);
        login(editTextAccount.getText().toString(),editTextPassword.getText().toString());
    }
    public void login(String username,String password){
        User user=new User(Integer.parseInt(username),password);
        new UserPresenter().loadData(user);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}