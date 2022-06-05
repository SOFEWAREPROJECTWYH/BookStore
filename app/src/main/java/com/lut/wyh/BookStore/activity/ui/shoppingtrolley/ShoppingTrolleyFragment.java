package com.lut.wyh.BookStore.activity.ui.shoppingtrolley;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.adapter.RecyclerViewAdapter;
import com.lut.wyh.BookStore.adapter.ShoppingTrolleyAdapter;
import com.lut.wyh.BookStore.entity.ShoppingTrolley;
import com.lut.wyh.BookStore.entity.User;
import com.lut.wyh.BookStore.presenter.ShoppingTrolleyPresenter;
import com.lut.wyh.BookStore.util.ShardPrefUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ShoppingTrolleyFragment extends Fragment {

    private ShoppingTrolleyModel shoppingTrolleyModel;
    private RecyclerView recyclerView;
    private ShoppingTrolleyAdapter shoppingTrolleyAdapter;
    private Button deleteProduct;
    private Button settle;
    //购物车数据
    private List<String> productids=new ArrayList<>();
    private List<String> urls=new ArrayList<>();
    private List<String> productNames=new ArrayList<>();
    private List<String> productPrices=new ArrayList<>();
    //用户信息
    private User userInfo;
    private View shop;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shoppingTrolleyModel =
                new ViewModelProvider(this).get(ShoppingTrolleyModel.class);
        View root = inflater.inflate(R.layout.fragment_shoppingtrolley, container, false);
        EventBus.getDefault().register(this);
        shop=root;
        deleteProduct=root.findViewById(R.id.delete_product);
        settle=root.findViewById(R.id.settle);
        initUserData();
        return root;
    }
    public void setDeleteClick(Button deleteProductButton){
        deleteProductButton.setOnClickListener(v->{
            ShoppingTrolleyAdapter shoppingTrolleyAdapterTemp=new ShoppingTrolleyAdapter();
            if (shoppingTrolleyAdapterTemp.getChecked().size()>0){
                for (int i=0;i<shoppingTrolleyAdapterTemp.getChecked().size();i++){
                    int flag=productids.indexOf(shoppingTrolleyAdapterTemp.getChecked().get(i));
                    productids.remove(flag);
                    urls.remove(flag);
                    productNames.remove(flag);
                    productPrices.remove(flag);
                    ShoppingTrolleyAdapter.ViewHolder viewHolder=(ShoppingTrolleyAdapter.ViewHolder)recyclerView.findViewHolderForAdapterPosition(flag);
                    CheckBox checkBox=viewHolder.itemView.findViewById(R.id.checkbox);
                    checkBox.setChecked(false);
                }
                shoppingTrolleyAdapterTemp.updateData(productids,urls,productNames,productPrices);
                shoppingTrolleyAdapterTemp.getChecked().clear();
                shoppingTrolleyAdapter.notifyDataSetChanged();
            }else{
                Toast.makeText(getActivity(),"您未选择需要删除的商品",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setSettleClick(Button settleButton){
        settleButton.setOnClickListener(v->{

        });
    }
    public void initUserData(){
        userInfo= (User)ShardPrefUtils.getSerializableEntity(getActivity(),"userInfo");
        if (userInfo==null){
            Toast.makeText(getActivity(),"您未登录",Toast.LENGTH_SHORT).show();
            return;
        }
        initShoppingTrolley();
    }
    public void initShoppingTrolley(){
        recyclerView=shop.findViewById(R.id.recycler_shoptro);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        new ShoppingTrolleyPresenter().getShopTro(userInfo);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShoppingEvent(ShoppingTrolley shoppingTrolley){
        if (shoppingTrolley.getShoptrobookid()==null){
//            shoppingTrolleyAdapter=new ShoppingTrolleyAdapter(null,null,null,null,this.getActivity());
//            recyclerView.setAdapter(shoppingTrolleyAdapter);
            Toast.makeText(getActivity(),"您的购物车是空的！",Toast.LENGTH_SHORT).show();
        }else if (!shoppingTrolley.getShoptrobookid().contains(";")){
            productids.add(shoppingTrolley.getShoptrobookid());
            urls.add(shoppingTrolley.getShoptrobookurl());
            productNames.add(shoppingTrolley.getShoptrobookname());
            productPrices.add(shoppingTrolley.getShoptrobookprice());
            shoppingTrolleyAdapter=new ShoppingTrolleyAdapter(productids, urls, productNames, productPrices, this.getActivity());
            recyclerView.setAdapter(shoppingTrolleyAdapter);
        }else{
            subShopInfo(shoppingTrolley);
        }
        setDeleteClick(deleteProduct);
        setSettleClick(settle);
    }
    public void subShopInfo(ShoppingTrolley shoppingTrolley){
        String[] bookids=shoppingTrolley.getShoptrobookid().split(";");
        String[] bookurls=shoppingTrolley.getShoptrobookurl().split(";");
        String[] booknames=shoppingTrolley.getShoptrobookname().split(";");
        String[] bookprices=shoppingTrolley.getShoptrobookprice().split(";");
        for (int i=0;i<bookids.length;i++){
            productids.add(bookids[i]);
            urls.add(bookurls[i]);
            productNames.add(booknames[i]);
            productPrices.add(bookprices[i]);
        }
        shoppingTrolleyAdapter=new ShoppingTrolleyAdapter(productids, urls, productNames, productPrices, this.getActivity());
        recyclerView.setAdapter(shoppingTrolleyAdapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
