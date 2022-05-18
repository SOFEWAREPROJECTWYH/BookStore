package com.lut.wyh.BookStore.presenter;

import android.util.Log;

import com.lut.wyh.BookStore.entity.Inventories;
import com.lut.wyh.BookStore.entity.User;
import com.lut.wyh.BookStore.service.BookService;
import com.lut.wyh.BookStore.service.UserService;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserPresenter {
    private final String TAG="UserPresenter";
    public void loadData(User user){
        String URL="http://192.168.43.27:8080/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        UserService userCall=retrofit.create(UserService.class);
        userCall.login(user).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread())
        .subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "onSubscribe: "+"开始递送");
            }

            @Override
            public void onNext(@NonNull Boolean aBoolean) {
                Log.i(TAG, "onNext: "+"请求成功");
                EventBus.getDefault().post(aBoolean);
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
