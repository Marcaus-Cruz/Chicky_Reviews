package com.example.chicky_reviews.ui.main.newReview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewReviewViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public NewReviewViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome to New Review!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
