package com.lut.wyh.BookStore.presenter;

import android.util.Log;

import com.lut.wyh.BookStore.entity.Collect;
import com.lut.wyh.BookStore.entity.Comments;
import com.lut.wyh.BookStore.event.CollectDeleteEvent;
import com.lut.wyh.BookStore.event.CollectInsertEvent;
import com.lut.wyh.BookStore.service.CollectService;
import com.lut.wyh.BookStore.service.CommentService;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CollectPresenter {
    private final static String TAG="CollectPresenter";
    public void insertData(Collect collect){
        String URL="http://192.168.43.27:8080/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        CollectService collectCall=retrofit.create(CollectService.class);
        collectCall.insertCollectInfo(collect).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread())
                .subscribe(new Observer<CollectInsertEvent>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "onSubscribe: 计划递送");
                    }

                    @Override
                    public void onNext(@NonNull CollectInsertEvent collectInsertEvent) {
                        Log.i(TAG, "onNext: 请求成功"+collectInsertEvent.getInsertCondition());
                        EventBus.getDefault().post(collectInsertEvent);
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
    public void deleteData(Collect collect){
        String URL="http://192.168.43.27:8080/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        CollectService collectCall=retrofit.create(CollectService.class);
        collectCall.deleteCollectInfo(collect).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "onSubscribe: 计划递送");
                    }

                    @Override
                    public void onNext(@NonNull Integer res) {
                        Log.i(TAG, "onNext: 请求成功");
                        CollectDeleteEvent collectDeleteEvent=new CollectDeleteEvent(res);
                        EventBus.getDefault().post(collectDeleteEvent);
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
