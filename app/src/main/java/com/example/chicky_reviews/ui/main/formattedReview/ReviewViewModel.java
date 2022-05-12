package com.example.chicky_reviews.ui.main.formattedReview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReviewViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReviewViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome to New Review!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
