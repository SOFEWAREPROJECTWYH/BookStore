package com.lut.wyh.BookStore.presenter;

import android.util.Log;

import com.lut.wyh.BookStore.entity.Inventories;
import com.lut.wyh.BookStore.entity.ShoppingTrolley;
import com.lut.wyh.BookStore.entity.User;
import com.lut.wyh.BookStore.event.ShoppingTrolleyEvent;
import com.lut.wyh.BookStore.service.BookService;
import com.lut.wyh.BookStore.service.UserService;
import com.lut.wyh.BookStore.util.ShardPrefUtils;

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
    public void login(User user){
        String URL="http://192.168.43.27:8080/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        UserService userCall=retrofit.create(UserService.class);
        userCall.login(user).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread())
        .subscribe(new Observer<User>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "onSubscribe: "+"开始递送");
            }

            @Override
            public void onNext(@NonNull User userRet) {
                Log.i(TAG, "onNext: "+"请求成功");
                if (user.getPassword().equals(userRet.getPassword())){
                    EventBus.getDefault().post(userRet);
                    ShardPrefUtils.saveSerializableEntity(null,"userInfo",userRet);
                }else{
                    EventBus.getDefault().post(false);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void register(User user){
        String URL="http://192.168.43.27:8080/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        UserService userCall=retrofit.create(UserService.class);
        userCall.register(user).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "onSubscribe: "+"开始递送");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.i(TAG, "onNext: "+"请求成功");
                        EventBus.getDefault().post(integer);
                        ShoppingTrolleyEvent shoppingTrolleyEvent=new ShoppingTrolleyEvent(user.getId(),user.getName());
                        EventBus.getDefault().post(shoppingTrolleyEvent);
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
