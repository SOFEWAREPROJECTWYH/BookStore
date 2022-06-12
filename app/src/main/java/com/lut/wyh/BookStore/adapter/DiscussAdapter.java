package com.lut.wyh.BookStore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lut.wyh.BookStore.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class DiscussAdapter extends RecyclerView.Adapter<DiscussAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView roundedImageView;
        private TextView username;
        private TextView bookComment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView=itemView.findViewById(R.id.image_head_portrait);
            username=itemView.findViewById(R.id.user_name);
            bookComment=itemView.findViewById(R.id.book_comment);
        }
    }

    public DiscussAdapter() {

    }

    @NonNull
    @Override
    public DiscussAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.discuss_recycler, parent, false);
        return new DiscussAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscussAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
