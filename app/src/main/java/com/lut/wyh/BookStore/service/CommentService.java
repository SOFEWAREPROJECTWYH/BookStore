package com.lut.wyh.BookStore.service;

import com.lut.wyh.BookStore.entity.Comment;
import com.lut.wyh.BookStore.entity.Comments;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CommentService {
    @GET("commentInfo")
    Observable<Comments> getCommentInformation();
    @POST("searchComment")
    Observable<Comments> getSearchInfoCommment(@Body Comment comment);
}
