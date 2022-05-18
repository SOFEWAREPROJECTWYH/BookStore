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

public class ShoppingTrolleyFragment extends Fragment {

    private ShoppingTrolleyModel shoppingTrolleyModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shoppingTrolleyModel =
                new ViewModelProvider(this).get(ShoppingTrolleyModel.class);
        View root = inflater.inflate(R.layout.fragment_shoppingtrolley, container, false);

        return root;
    }
}
