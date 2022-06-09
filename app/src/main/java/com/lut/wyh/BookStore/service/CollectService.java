package com.lut.wyh.BookStore.service;

import com.lut.wyh.BookStore.entity.Collect;
import com.lut.wyh.BookStore.event.CollectInsertEvent;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CollectService {
    @POST("collectInsert")
    Observable<CollectInsertEvent> insertCollectInfo(@Body Collect collect);
    @POST("collectDelete")
    Observable<Integer> deleteCollectInfo(@Body Collect collect);
}
