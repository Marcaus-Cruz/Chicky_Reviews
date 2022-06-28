package com.example.chicky_reviews.ui.main.newReview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chicky_reviews.R;

public class ExtraActivity extends AppCompatActivity {
    private Button cancel;
    private Button done;
    private Button newRating;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        reviewViewModel = new ViewModelProvider(this).get(NewReviewViewModel.class);
//        View root = inflater.inflate(R.layout.review_page, container, false);
        setContentView(R.layout.add_extra_page);

        newRating = findViewById(R.id.new_rating);
        cancel = findViewById(R.id.cancel_extra);
        done = findViewById(R.id.done_extra);

        newRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRating();
                return;
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.done_extra:
                        addExtra();
                        break;

                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.cancel_extra:
                        cancel();
                        break;
                }
            }
        });


    }

    public void addExtra(){
        Toast.makeText(this, "Add extra to new review", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    public void addRating(){
        Toast.makeText(this, "Need to populate ratings", Toast.LENGTH_SHORT).show();
    }
    public void cancel() {
        Toast.makeText(this, "aborting", Toast.LENGTH_SHORT).show();
        this.finish();
    }

}
