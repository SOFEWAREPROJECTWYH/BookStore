package com.lut.wyh.BookStore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.entity.Comment;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    private List<Comment> commentList;
    private Context context;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView headImageExper;
        private TextView title;
        private TextView content;
        private TextView like;
        private TextView comment;
        private TextView share;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }
        public void initView(View view){
            headImageExper=view.findViewById(R.id.head_image_exper);
            title=view.findViewById(R.id.title_exper);
            content=view.findViewById(R.id.content_exper);
            like=view.findViewById(R.id.like);
            comment=view.findViewById(R.id.comment_exper);
            share= view.findViewById(R.id.share_exper);
        }
    }
    public CommentAdapter(List<Comment> comments, Context context){
        commentList=comments;
        this.context=context;
    }
    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feel_experience, parent, false);
        return new CommentAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        holder.title.setText(commentList.get(position).getTitle());
        holder.content.setText(commentList.get(position).getContent());
        holder.like.setText(commentList.get(position).getLike());
        holder.comment.setText(commentList.get(position).getComment());
        holder.share.setText(commentList.get(position).getShare());
        Glide.with(context).load(commentList.get(position).getUrl()).into(holder.headImageExper);
    }


    @Override
    public int getItemCount() {
        return commentList.size();
    }
}

