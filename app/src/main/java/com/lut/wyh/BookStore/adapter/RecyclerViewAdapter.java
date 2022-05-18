package com.lut.wyh.BookStore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.entity.Inventory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context contextFragment;
    private List<Inventory> inventoryList=new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private TextView textView2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_soft);
            textView=itemView.findViewById(R.id.text_soft);
            textView2=itemView.findViewById(R.id.text_softprice);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_software, parent, false);
        return new ViewHolder(view);
    }

    public RecyclerViewAdapter(List<Inventory> bookInfoList, Context context) {
        inventoryList.addAll(bookInfoList);
        contextFragment=context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(inventoryList.get(position).getBookname());
        holder.textView2.setText("￥"+inventoryList.get(position).getPrice().toString());
        Glide.with(contextFragment).load(inventoryList.get(position).getUrl()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(holder.imageView,holder.getLayoutPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return inventoryList.size();
    }


    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
