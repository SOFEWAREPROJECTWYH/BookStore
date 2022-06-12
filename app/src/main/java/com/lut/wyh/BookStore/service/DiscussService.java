package com.lut.wyh.BookStore.service;

import com.lut.wyh.BookStore.entity.Discuss;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface DiscussService {
    @GET("discussInfo")
    Observable<List<Discuss>> getDiscussInfo();
}
