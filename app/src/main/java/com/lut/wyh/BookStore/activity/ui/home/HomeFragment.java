package com.lut.wyh.BookStore.activity.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lut.wyh.BookStore.R;
import com.lut.wyh.BookStore.activity.LoginActivity;
import com.lut.wyh.BookStore.activity.StoreActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button button;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        button=root.findViewById(R.id.login);
        button.setOnClickListener(v->{
            Intent intent=new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });
        return root;
    }
}