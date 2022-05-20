package com.lut.wyh.BookStore.service;

import com.lut.wyh.BookStore.entity.User;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("login")
    Observable<User> login(@Body User user);
    @POST("register")
    Observable<Integer> register(@Body User user);
}
