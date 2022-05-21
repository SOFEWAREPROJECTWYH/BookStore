package com.lut.wyh.BookStore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.entity.ShoppingTrolley;
import com.lut.wyh.BookStore.entity.User;
import com.lut.wyh.BookStore.event.ShoppingTrolleyEvent;
import com.lut.wyh.BookStore.presenter.ShoppingTrolleyPresenter;
import com.lut.wyh.BookStore.presenter.UserPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class RegisterActivity extends AppCompatActivity {
    private EditText account;
    private EditText password;
    private EditText username;
    private EditText phonenumber;
    private EditText email;
    private EditText major;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EventBus.getDefault().register(this);
        initView();
        setRegisterClick();
    }
    public void initView(){
        account=findViewById(R.id.account);
        password=findViewById(R.id.password);
        username=findViewById(R.id.username);
        phonenumber=findViewById(R.id.phonenumber);
        email=findViewById(R.id.email);
        major=findViewById(R.id.major);
        register=findViewById(R.id.register);
    }
    public void setRegisterClick(){
        register.setOnClickListener(v->{
            int flag=0;
            for (int i=0;i<account.getText().toString().length();i++){
                if (account.getText().toString().charAt(i)<'0'||
                        account.getText().toString().charAt(i)>'9'){
                    flag=1;
                    account.setText("账号必须为数字");
                }
            }
            if (flag==0&&(account.getText().toString().equals("")||
                    password.getText().toString().equals("")||
                    username.getText().toString().equals("")||
                    phonenumber.getText().toString().equals("")||
                    email.getText().toString().equals("")||
                    major.getText().toString().equals(""))){
                Toast.makeText(this,"信息不全！！！",Toast.LENGTH_LONG).show();
            }else if (flag==1){
                Toast.makeText(this,"账号必须为数字",Toast.LENGTH_LONG).show();
            }else{
                User user=new User(Integer.parseInt(account.getText().toString()),username.getText().toString()
                ,password.getText().toString(),phonenumber.getText().toString()
                ,email.getText().toString(),major.getText().toString());
                new UserPresenter().register(user);
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Integer result){
        if (result==1){
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"注册失败",Toast.LENGTH_SHORT).show();
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onInitShopEvent(ShoppingTrolleyEvent shoppingTrolleyEvent){
        User usershop=new User(shoppingTrolleyEvent.getId(),shoppingTrolleyEvent.getUsername());
        new ShoppingTrolleyPresenter().initShop(usershop);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}