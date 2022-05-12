package com.example.chicky_reviews.ui.main.newReview;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.chicky_reviews.R;
import com.example.chicky_reviews.ui.main.formattedReview.FormattedReview;
import com.example.chicky_reviews.ui.main.newReview.NewReviewViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NewReviewActivity extends AppCompatActivity {

    private NewReviewViewModel reviewViewModel;

    private Button cancel;
    private Button done;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        reviewViewModel = new ViewModelProvider(this).get(NewReviewViewModel.class);
//        View root = inflater.inflate(R.layout.review_page, container, false);
        setContentView(R.layout.review_page);

        cancel = findViewById(R.id.cancel_btn);
        done = findViewById(R.id.review_done_btn);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.review_done_btn:
                        saveReview();
                        break;
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.cancel_btn:
                        cancel();
                        break;
                }
            }
        });

    }

    public void saveReview () {
        Toast.makeText(this, "Formatting review", Toast.LENGTH_SHORT).show();
        this.finish();

        //This is where we get all data and save as new review.
        //Add it to existing reviews
        //prompt review page
        Intent outputIntent = new Intent(this, FormattedReview.class);
        this.startActivity(outputIntent);
    }
    public void cancel() {
        Toast.makeText(this, "aborting", Toast.LENGTH_SHORT).show();
        this.finish();
    }
}
