package com.example.chicky_reviews.ui.main.newReview;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chicky_reviews.R;
import com.example.chicky_reviews.Category;
import com.example.chicky_reviews.Rating;

import java.util.ArrayList;

public class ExtraActivity extends AppCompatActivity {
    private LinearLayout btnLayout;
    private Button cancelBtn;
    private Button doneBtn;
    private Button newRatingBtn;
    private LinearLayout rootLayout;
    private EditText categoryName;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        reviewViewModel = new ViewModelProvider(this).get(NewReviewViewModel.class);
//        View root = inflater.inflate(R.layout.review_page, container, false);
        setContentView(R.layout.add_extra_page);

        rootLayout = findViewById(R.id.extra_vlo);
        categoryName = findViewById(R.id.extra_name);
        newRatingBtn = findViewById(R.id.new_rating_btn);
        btnLayout = findViewById(R.id.add_extra_btn_lo);
        cancelBtn = findViewById(R.id.cancel_extra);
        doneBtn = findViewById(R.id.done_extra);

        newRatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRating();
                return;
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.done_extra:
                        addExtra();
                        break;

                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
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
        String categoryNameString = categoryName.getText().toString().trim();
        // Validate category name, if rating does not have an entry then don't add to category
        if(categoryNameString != ""){
            Toast.makeText(this, "Adding " + categoryNameString + " to new review", Toast.LENGTH_SHORT).show();
            Category extraCategory = new Category(categoryNameString);
            for(int i = 1; i < rootLayout.getChildCount()-2; i ++){
                if(((EditText) rootLayout.getChildAt(i)).getText().toString().trim() != ""){
                    Rating currentRating = new Rating(((EditText) rootLayout.getChildAt(i)).getText().toString());
                    extraCategory.addRating(currentRating);
                }
            }
            // Add this info to previous intent (new review)
            Intent newReviewIntent = getIntent();
            ArrayList<Category> extras;

            //Might be able to do this with the base Review object
            if(newReviewIntent.getExtras().get("extraCategories") != null){
                // Extras exist

                // Grab old extras
                extras = (ArrayList<Category>) newReviewIntent.getExtras().get("extras");
                //newReviewIntent.removeExtra("extraCategories");
            } else{
                // We are adding the first extra
                extras = new ArrayList<Category>();
            }
            extras.add(extraCategory);
            newReviewIntent.putExtra("extraCategories", extras);

            this.finish();
        } else{
            Toast.makeText(this, "Enter a category name", Toast.LENGTH_SHORT).show();
        }
        return;
    }

    public void addRating(){
        Toast.makeText(this, "New rating", Toast.LENGTH_SHORT).show();

        //Set params for children to be added
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,  ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        params.bottomMargin = 15;
        params.leftMargin = 15;
        params.topMargin = 15;

        //Clear root layout
        rootLayout.removeView(newRatingBtn);
        rootLayout.removeView(btnLayout);


        //add rating
        LinearLayout newRatingLayout = new LinearLayout(this);
        newRatingLayout.setOrientation(LinearLayout.HORIZONTAL);
        newRatingLayout.setLayoutParams(params);

        EditText newRatingName = new EditText(this);
        newRatingName.setHint("Rating Name: ");

        newRatingLayout.addView(newRatingName);

        rootLayout.addView(newRatingLayout);

        //append things removed
        rootLayout.addView(newRatingBtn);
        rootLayout.addView(btnLayout);

    }
    public void cancel() {
        Toast.makeText(this, "aborting", Toast.LENGTH_SHORT).show();
        this.finish();
    }

}
