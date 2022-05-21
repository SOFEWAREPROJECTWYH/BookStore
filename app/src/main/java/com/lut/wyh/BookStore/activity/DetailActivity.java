package com.lut.wyh.BookStore.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.entity.ShoppingTrolley;
import com.lut.wyh.BookStore.entity.User;
import com.lut.wyh.BookStore.presenter.ShoppingTrolleyPresenter;
import com.lut.wyh.BookStore.util.ShardPrefUtils;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textViewName;
    private TextView textViewPrice;
    private ImageView imageViewComment;
    private ImageView imageViewCollect;
    private Button buttonShop;
    private Button buttonBuy;
    //用户信息
    private User userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        initUserData();
        Intent intent=this.getIntent();
        textViewName.setText(intent.getStringExtra("textname"));
        textViewPrice.setText("￥"+intent.getIntExtra("textprice",0));
        Glide.with(this).load(intent.getStringExtra("image")).into(imageView);
    }
    public void initView(){
        imageView=findViewById(R.id.image_book);
        textViewName=findViewById(R.id.text_name);
        textViewPrice=findViewById(R.id.text_price);
        imageViewComment=findViewById(R.id.comment);
        imageViewCollect=findViewById(R.id.collect);
        buttonShop=findViewById(R.id.shopping_trolley);
        buttonBuy=findViewById(R.id.buy);
        buttonShop.setBackgroundResource(R.drawable.round_radius);
        buttonBuy.setBackgroundResource(R.drawable.round_radius);
        buttonShop.setOnClickListener(v->{
            if (userInfo==null) {
                Toast.makeText(getApplication(),"用户未登录",Toast.LENGTH_SHORT).show();
            }else{
                Intent intent=this.getIntent();
                ShoppingTrolley shoppingTrolley=new ShoppingTrolley(userInfo.getId(),userInfo.getName(),
                intent.getStringExtra("bookid"),intent.getStringExtra("textname"),
                        intent.getStringExtra("image"),intent.getStringExtra("price"));
                new ShoppingTrolleyPresenter().insertShopTro(shoppingTrolley);
            }
        });
    }
    public void initUserData(){
        userInfo=(User) ShardPrefUtils.getSerializableEntity(getApplication(),"userInfo");
    }
}