package com.lut.wyh.BookStore.activity.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;

import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.activity.LoginActivity;
import com.lut.wyh.BookStore.activity.StoreActivity;
import com.lut.wyh.BookStore.entity.User;
import com.lut.wyh.BookStore.entity.UserNick;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class HomeFragment extends Fragment {
    private final String TAG="HomeFragment";
    //用户信息
    private User userInfo;
    private HomeViewModel homeViewModel;
    private Button button;
    private TextView userName;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        EventBus.getDefault().register(this);
        button=root.findViewById(R.id.login);
        userName=root.findViewById(R.id.nickname);
        button.setOnClickListener(v->{
            Intent intent=new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });
        return root;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserNick userNick){
        userName.setText(userNick.getUsername());
        if (userNick.getUsername()!=null){
            button.setVisibility(View.GONE);
        }
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