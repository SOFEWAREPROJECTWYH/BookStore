package com.lut.wyh.BookStore.service;

import com.lut.wyh.BookStore.entity.Comment;
import com.lut.wyh.BookStore.entity.Comments;
import com.lut.wyh.BookStore.entity.Inventories;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface CommentService {
    @GET("commentInfo")
    Observable<Comments> getCommentInformation();
}
