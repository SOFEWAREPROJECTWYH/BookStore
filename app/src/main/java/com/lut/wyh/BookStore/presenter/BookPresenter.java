package com.lut.wyh.BookStore.presenter;


import android.util.Log;

import com.lut.wyh.BookStore.entity.Inventories;
import com.lut.wyh.BookStore.entity.Inventory;
import com.lut.wyh.BookStore.event.InventoryEvent;
import com.lut.wyh.BookStore.service.BookService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;

public class BookPresenter {
    private final String TAG="BookPresenter";
    private Inventories bookinfo;

    public void loadData(){
        String URL="http://192.168.43.27:8080/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        BookService bookCall=retrofit.create(BookService.class);
        bookCall.getBookInformation().subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread())
                .subscribe(new Observer<Inventories>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "onSubscribe: 计划递送");
                    }

                    @Override
                    public void onNext(@NonNull Inventories inventories) {
                        bookinfo=inventories;
                        EventBus.getDefault().post(inventories);
                        Log.i(TAG, "onNext: 请求成功/n"+inventories);
                    }


                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG, "onError: 请求失败"+e);
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: 请求成功");
                    }
                });
    }
    public Inventories getBookinfo() {
//        if (bookinfo==null){
//            try {
//                Thread.sleep(100);
//            }catch (Exception ignored){
//            }
//        }
        return bookinfo;
    }

}
