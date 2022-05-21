package com.lut.wyh.BookStore.activity.ui.shoppingtrolley;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.entity.User;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ShoppingTrolleyFragment extends Fragment {

    private ShoppingTrolleyModel shoppingTrolleyModel;
    //用户信息
    private User userInfo;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shoppingTrolleyModel =
                new ViewModelProvider(this).get(ShoppingTrolleyModel.class);
        View root = inflater.inflate(R.layout.fragment_shoppingtrolley, container, false);
        EventBus.getDefault().register(this);
        return root;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserEvent(User user){
        userInfo=user;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
