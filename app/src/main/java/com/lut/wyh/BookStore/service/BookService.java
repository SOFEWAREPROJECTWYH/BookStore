package com.lut.wyh.BookStore.service;

import com.lut.wyh.BookStore.entity.Inventories;
import com.lut.wyh.BookStore.entity.Inventory;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BookService {
    @GET("bookinfo/book")
    Observable<Inventories> getBookInformation();

    @GET("bookinfo/bookimage")
    Observable<Inventories> getBookImage();
}
