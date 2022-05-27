package com.lut.wyh.BookStore.presenter;

import android.util.Log;

import com.lut.wyh.BookStore.entity.Comment;
import com.lut.wyh.BookStore.entity.Comments;
import com.lut.wyh.BookStore.entity.Inventories;
import com.lut.wyh.BookStore.service.BookService;
import com.lut.wyh.BookStore.service.CommentService;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentPresenter {
    private final String TAG="CommentPresenter";
    public void loadData(){
        String URL="http://192.168.43.27:8080/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        CommentService commentCall=retrofit.create(CommentService.class);
        commentCall.getCommentInformation().subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread())
                .subscribe(new Observer<Comments>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "onSubscribe: 计划递送");
                    }

                    @Override
                    public void onNext(@NonNull Comments comments) {
                        Log.i(TAG, "onNext: 请求成功");
                        EventBus.getDefault().post(comments);
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
