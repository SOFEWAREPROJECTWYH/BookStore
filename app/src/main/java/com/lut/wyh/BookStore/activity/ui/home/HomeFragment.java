package com.lut.wyh.BookStore.activity.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;

import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.activity.LoginActivity;
import com.lut.wyh.BookStore.activity.StoreActivity;
import com.lut.wyh.BookStore.entity.User;
import com.lut.wyh.BookStore.entity.UserNick;
import com.lut.wyh.BookStore.util.ShardPrefUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class HomeFragment extends Fragment {
    private final String TAG="HomeFragment";
    //用户信息
    private User userInfo;
    private HomeViewModel homeViewModel;
    private Button login;
    private Button logout;
    private TextView userName;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        EventBus.getDefault().register(this);
        userInfo=(User)ShardPrefUtils.getSerializableEntity(getActivity(),"userInfo");
        login=root.findViewById(R.id.login);
        logout=root.findViewById(R.id.log_out);
        userName=root.findViewById(R.id.nickname);
        initUserData();
        login.setOnClickListener(v->{
            Intent intent=new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });
        logout.setOnClickListener(v->{
            ShardPrefUtils.saveSerializableEntity(null,"userInfo",null);
            login.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);
            userName.setText("游客书友");
            Toast.makeText(getActivity(),"退出成功",Toast.LENGTH_SHORT).show();
        });
        return root;
    }
    public void initUserData(){
        if (userInfo!=null){
            userName.setText(userInfo.getName());
            login.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserNick userNick){
        userName.setText(userNick.getUsername());
        if (userNick.getUsername()!=null){
            login.setVisibility(View.GONE);
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