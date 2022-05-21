package com.lut.wyh.BookStore.adapter;

import android.content.Context;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.entity.ShoppingTrolley;

import java.util.List;

public class ShoppingTrolleyAdapter extends  RecyclerView.Adapter<ShoppingTrolleyAdapter.ViewHolder> {
    private List<String> ids;
    private List<String> urls;
    private List<String> productNames;
    private List<String> productPrices;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RadioButton radioButton;
        private ImageView imageView;
        private TextView productName;
        private TextView productPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton=itemView.findViewById(R.id.radio);
            imageView=itemView.findViewById(R.id.image_shop);
            productName=itemView.findViewById(R.id.product_name);
            productPrice=itemView.findViewById(R.id.product_price);
        }
    }
    @NonNull
    @Override
    public ShoppingTrolleyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_shopping_trolley, parent, false);
        return new ShoppingTrolleyAdapter.ViewHolder(view);
    }

    public ShoppingTrolleyAdapter(List<String> ids, List<String> urls, List<String> names, List<String> prices, Context context) {
        this.ids=ids;
        this.urls=urls;
        this.productNames=names;
        this.productPrices=prices;
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingTrolleyAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
