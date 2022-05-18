package com.lut.wyh.BookStore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lut.wyh.BookStore.R;

public class ShoppingTrolleyAdapter extends  RecyclerView.Adapter<ShoppingTrolleyAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    @NonNull
    @Override
    public ShoppingTrolleyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_shopping_trolley, parent, false);
        return new ShoppingTrolleyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingTrolleyAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
