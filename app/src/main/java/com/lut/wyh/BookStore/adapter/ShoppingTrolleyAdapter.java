package com.lut.wyh.BookStore.adapter;

import android.content.Context;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.entity.ShoppingTrolley;

import java.util.ArrayList;
import java.util.List;

public class ShoppingTrolleyAdapter extends  RecyclerView.Adapter<ShoppingTrolleyAdapter.ViewHolder> {
    private static List<String> idsS=new ArrayList<>();
    private static List<String> urlsS;
    private static List<String> productNamesS;
    private static List<String> productPricesS;
    private static List<String> ids_dispose=new ArrayList<>();
    private Context contextFragment;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private ImageView imageView;
        private TextView productName;
        private TextView productPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.checkbox);
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

    public ShoppingTrolleyAdapter() {
    }
    public List<String> getChecked(){
        return ids_dispose;
    }
    public ShoppingTrolleyAdapter(List<String> ids, List<String> urls, List<String> names, List<String> prices, Context context) {
        idsS=ids;
        urlsS=urls;
        productNamesS=names;
        productPricesS=prices;
        contextFragment=context;
    }
    public void updateData(List<String> ids, List<String> urls, List<String> names, List<String> prices){
        idsS=ids;
        urlsS=urls;
        productNamesS=names;
        productPricesS=prices;
    }
    @Override
    public void onBindViewHolder(@NonNull ShoppingTrolleyAdapter.ViewHolder holder, int position) {
        holder.productName.setText(productNamesS.get(position));
        holder.productPrice.setText(productPricesS.get(position));
        Glide.with(contextFragment).load(urlsS.get(position)).into(holder.imageView);
        holder.checkBox.setOnCheckedChangeListener((v,isChecked)->{
            if (isChecked){
                ids_dispose.add(idsS.get(position));
            }else{
                ids_dispose.remove(idsS.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        return idsS.size();
    }
}
