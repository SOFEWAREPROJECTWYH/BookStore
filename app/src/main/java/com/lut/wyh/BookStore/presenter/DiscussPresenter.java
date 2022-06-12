package com.lut.wyh.BookStore.presenter;

import android.util.Log;

import com.lut.wyh.BookStore.entity.Comments;
import com.lut.wyh.BookStore.entity.Discuss;
import com.lut.wyh.BookStore.service.CommentService;
import com.lut.wyh.BookStore.service.DiscussService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiscussPresenter {
    private final String TAG="DiscussPresenter";
    public void loadData(){
        String URL="http://192.168.43.27:8080/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        DiscussService discussCall=retrofit.create(DiscussService.class);
        discussCall.getDiscussInfo().subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread())
                .subscribe(new Observer<List<Discuss>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "onSubscribe: 计划递送");
                    }

                    @Override
                    public void onNext(@NonNull List<Discuss> discusses) {
                        Log.i(TAG, "onNext: 请求成功");
                        EventBus.getDefault().post(discusses);
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
}
