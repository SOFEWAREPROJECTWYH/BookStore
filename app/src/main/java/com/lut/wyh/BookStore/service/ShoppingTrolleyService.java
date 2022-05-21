package com.lut.wyh.BookStore.service;

import com.lut.wyh.BookStore.entity.ShoppingTrolley;
import com.lut.wyh.BookStore.entity.User;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ShoppingTrolleyService {
    @POST("initShoppingTrolley")
    Observable<Integer> initShoppingTrolley(@Body User user);
    @POST("ShoppingTrolley")
    Observable<ShoppingTrolley> getShoppingTrolley(@Body User user);
    @POST("insertShopTro")
    Observable<Integer> insertShoppingTrolley(@Body ShoppingTrolley shoppingTrolley);
}
