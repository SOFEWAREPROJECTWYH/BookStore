package com.lut.wyh.BookStore.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.lut.wyh.BookStore.activity.StoreActivity;
import com.lut.wyh.BookStore.activity.ui.shoppingtrolley.ShoppingTrolleyFragment;
import com.lut.wyh.BookStore.entity.ShoppingTrolley;
import com.lut.wyh.BookStore.entity.User;
import com.lut.wyh.BookStore.event.InsertShopTroEvent;
import com.lut.wyh.BookStore.service.ShoppingTrolleyService;
import com.lut.wyh.BookStore.service.UserService;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShoppingTrolleyPresenter {
    private final String TAG="ShoppingTrolley";
    public void initShop(User user) {
        String URL = "http://192.168.43.27:8080/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        ShoppingTrolleyService shopCall = retrofit.create(ShoppingTrolleyService.class);
        shopCall.initShoppingTrolley(user).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "onSubscribe: " + "开始递送");
                    }

                    @Override
                    public void onNext(@NonNull Integer change) {
                        Log.i(TAG, "onNext: " + "请求成功");
                    }


                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getShopTro(User user){
        String URL="http://192.168.43.27:8080/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        ShoppingTrolleyService shopCall=retrofit.create(ShoppingTrolleyService.class);
        shopCall.getShoppingTrolley(user).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread())
                .subscribe(new Observer<ShoppingTrolley>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "onSubscribe: "+"开始递送");
                    }

                    @Override
                    public void onNext(@NonNull ShoppingTrolley shoppingTrolley) {
                        Log.i(TAG, "onNext: "+"请求成功"+" "+shoppingTrolley);
                        EventBus.getDefault().post(shoppingTrolley);
                    }


                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void insertShopTro(ShoppingTrolley shoppingTrolley){
        String URL="http://192.168.43.27:8080/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        ShoppingTrolleyService shopCall=retrofit.create(ShoppingTrolleyService.class);
        shopCall.insertShoppingTrolley(shoppingTrolley).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "onSubscribe: "+"开始递送");
                    }

                    @Override
                    public void onNext(@NonNull Integer change) {
                        Log.i(TAG, "onNext: "+"请求成功");
                        InsertShopTroEvent insertShopTroEvent=new InsertShopTroEvent(change);
                        EventBus.getDefault().post(insertShopTroEvent);
                    }


                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
