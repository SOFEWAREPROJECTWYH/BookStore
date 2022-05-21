package com.lut.wyh.BookStore.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.entity.User;
import com.lut.wyh.BookStore.presenter.UserPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {
    private final String TAG="LoginActivity";
    private EditText editTextAccount;
    private EditText editTextPassword;
    private Button button;
    private Button buttonregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EventBus.getDefault().register(this);
        editTextAccount=findViewById(R.id.account);
        editTextPassword=findViewById(R.id.password);
        button=findViewById(R.id.login);
        buttonregister=findViewById(R.id.register_login);
        setButtonOnClickListener(button);
        setRegisterOnClickListener();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Boolean userResult){
        if (userResult){
            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        }else{
            editTextAccount.setText("用户名或密码错误");
            editTextPassword.setText("用户名或密码错误");
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventRegisterInfo(User user){
        Intent intent=new Intent(LoginActivity.this,StoreActivity.class);
        intent.putExtra("username",user.getName());
        startActivity(intent);
    }
    public void login(String username,String password){
        User user=new User(Integer.valueOf(username),password);
        new UserPresenter().login(user);
    }

    public void setButtonOnClickListener(Button buttonlogin){
        buttonlogin.setOnClickListener(v->{
            int flag=0;
            for (int i=0;i<editTextAccount.getText().toString().length();i++){
                if (editTextAccount.getText().toString().charAt(i)<'0'||
                        editTextAccount.getText().toString().charAt( i)>'9'){
                    flag=1;
                    editTextAccount.setText("账号必须为数字");
                }
            }
            if (flag==0&&editTextAccount.getText().toString().equals("")){
                editTextAccount.setText("此处为空");
            }else if (flag==0&&editTextPassword.getText().toString().equals("")){
                editTextPassword.setText("此处为空");
            }else if (flag==0){
                login(editTextAccount.getText().toString(),editTextPassword.getText().toString());
            }
        });
    }

    public void setRegisterOnClickListener(){
        buttonregister.setOnClickListener(v->{
            Intent intent=new Intent(this,RegisterActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}