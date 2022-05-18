package com.lut.wyh.BookStore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.lut.wyh.BookStore.R;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextAccount;
    private EditText editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextAccount=findViewById(R.id.account);
        editTextPassword=findViewById(R.id.password);
        login(editTextAccount.getText().toString(),editTextPassword.getText().toString());
    }
    public void login(String username,String password){

    }
}