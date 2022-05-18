package com.lut.wyh.BookStore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.entity.User;
import com.lut.wyh.BookStore.presenter.UserPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextAccount;
    private EditText editTextPassword;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EventBus.getDefault().register(this);
        editTextAccount=findViewById(R.id.account);
        editTextPassword=findViewById(R.id.password);
        button=findViewById(R.id.login);
        button.setOnClickListener(v->{
            if (editTextAccount.getText().toString().equals("")){
                editTextAccount.setText("此处为空");
            }else if (editTextPassword.getText().toString().equals("")){
                editTextPassword.setText("此处为空");
            }else{
                login(editTextAccount.getText().toString(),editTextPassword.getText().toString());
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Boolean userResult){
        if (userResult){
            Intent intent=new Intent(LoginActivity.this,StoreActivity.class);
            startActivity(intent);
        }else{
            editTextAccount.setText("用户名或密码错误");
            editTextPassword.setText("用户名或密码错误");
        }
    }
    public void login(String username,String password){
        User user=new User(Integer.valueOf(username),password);
        new UserPresenter().loadData(user);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}