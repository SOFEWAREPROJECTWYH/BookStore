package com.lut.wyh.BookStore.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableResource;
import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.entity.Collect;
import com.lut.wyh.BookStore.entity.ShoppingTrolley;
import com.lut.wyh.BookStore.entity.User;
import com.lut.wyh.BookStore.event.CollectDeleteEvent;
import com.lut.wyh.BookStore.event.CollectInsertEvent;
import com.lut.wyh.BookStore.event.InsertShopTroEvent;
import com.lut.wyh.BookStore.presenter.CollectPresenter;
import com.lut.wyh.BookStore.presenter.ShoppingTrolleyPresenter;
import com.lut.wyh.BookStore.util.ShardPrefUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textViewName;
    private TextView textViewPrice;
    private ImageView imageViewComment;
    private ImageView imageViewCollect;
    private TextView commentText;
    private TextView collectText;
    private Button buttonShop;
    private Button buttonBuy;
    //用户信息
    private User userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        EventBus.getDefault().register(this);
        initView();
        initUserData();
        Intent intent=this.getIntent();
        textViewName.setText(intent.getStringExtra("textname"));
        textViewPrice.setText("￥"+intent.getStringExtra("textprice"));
        Glide.with(this).load(intent.getStringExtra("image")).into(imageView);
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    public void initView(){
        int flag=0;
        imageView=findViewById(R.id.image_book);
        textViewName=findViewById(R.id.text_name);
        textViewPrice=findViewById(R.id.text_price);
        imageViewComment=findViewById(R.id.comment);
        imageViewCollect=findViewById(R.id.collect);
        commentText=findViewById(R.id.comment_text);
        collectText=findViewById(R.id.collect_text);
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
                        intent.getStringExtra("image"),intent.getStringExtra("textprice"));
                new ShoppingTrolleyPresenter().insertShopTro(shoppingTrolley);
            }

        });
        imageViewCollect.setOnClickListener(v->{
            if (collectText.getText().toString().equals("收藏")){
                collectText.setText("已收藏");
                imageViewCollect.setImageResource(R.drawable.collect_pressed);
                Intent intent=this.getIntent();
                Collect collect=new Collect(userInfo.getId(),userInfo.getName(),Integer.valueOf(intent.getStringExtra("bookid")),intent.getStringExtra("textname"));
                new CollectPresenter().insertData(collect);
            }else{
                collectText.setText("收藏");
                imageViewCollect.setImageResource(R.drawable.collect);
                Intent intent=this.getIntent();
                Collect collect=new Collect(userInfo.getId(),userInfo.getName(),Integer.valueOf(intent.getStringExtra("bookid")),intent.getStringExtra("textname"));
                new CollectPresenter().deleteData(collect);
            }
        });
    }
    public void initUserData(){
        userInfo=(User) ShardPrefUtils.getSerializableEntity(getApplication(),"userInfo");
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(InsertShopTroEvent insertShopTroEvent){
        Toast.makeText(this,"已添加到购物车！！！",Toast.LENGTH_SHORT).show();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectInsertEvent(CollectInsertEvent collectInsertEvent){
        if (collectInsertEvent!=null&&collectInsertEvent.getInsertCondition().equals(1)){
            Toast.makeText(getApplication(), "图书已收藏！", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplication(), "图书收藏失败！", Toast.LENGTH_SHORT).show();
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectDeleteEvent(CollectDeleteEvent collectDeleteEvent){
        if (collectDeleteEvent.getDeleteCondition().equals(1)){
            Toast.makeText(getApplication(), "图书已取消收藏！", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplication(), "图书取消收藏失败！", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}