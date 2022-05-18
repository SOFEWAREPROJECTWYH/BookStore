package com.lut.wyh.BookStore.activity.ui.shoppingtrolley;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShoppingTrolleyModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ShoppingTrolleyModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is shopping trolley fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
