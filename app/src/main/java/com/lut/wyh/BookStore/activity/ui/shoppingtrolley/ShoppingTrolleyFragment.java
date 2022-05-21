package com.lut.wyh.BookStore.activity.ui.shoppingtrolley;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    //用户信息
    private User userInfo;
    private View shop;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shoppingTrolleyModel =
                new ViewModelProvider(this).get(ShoppingTrolleyModel.class);
        View root = inflater.inflate(R.layout.fragment_shoppingtrolley, container, false);
        shop=root;
        initUserData();
        return root;
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
        recyclerView=shop.findViewById(R.id.recycler_shopping_trolley);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        new ShoppingTrolleyPresenter().getShopTro(userInfo);

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShoppingEvent(ShoppingTrolley shoppingTrolley){
        if (shoppingTrolley.getShoptrobookid()==null){
            shoppingTrolleyAdapter=new ShoppingTrolleyAdapter(null,null,null,null,this.getActivity());
            recyclerView.setAdapter(shoppingTrolleyAdapter);
        }else if (!shoppingTrolley.getShoptrobookid().contains(";")){
            List<String> productids=new ArrayList<>();
            List<String> urls=new ArrayList<>();
            List<String> productNames=new ArrayList<>();
            List<String> productPrices=new ArrayList<>();
            productids.add(shoppingTrolley.getShoptrobookid());
            urls.add(shoppingTrolley.getShoptrobookurl());
            productNames.add(shoppingTrolley.getShoptrobookname());
            productPrices.add(shoppingTrolley.getShoptrobookprice());
            shoppingTrolleyAdapter=new ShoppingTrolleyAdapter(productids, urls, productNames, productPrices, this.getActivity());
            recyclerView.setAdapter(shoppingTrolleyAdapter);
        }else{
            subShopInfo(shoppingTrolley);
        }
    }
    public void subShopInfo(ShoppingTrolley shoppingTrolley){
        List<String> productids=new ArrayList<>();
        List<String> urls=new ArrayList<>();
        List<String> productNames=new ArrayList<>();
        List<String> productPrices=new ArrayList<>();
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
    }
}
