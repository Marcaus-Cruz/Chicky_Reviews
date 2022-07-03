package com.example.chicky_reviews.ui.main.newReview;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chicky_reviews.Category;
import com.example.chicky_reviews.R;
import com.example.chicky_reviews.Rating;
import com.example.chicky_reviews.Review;
import com.example.chicky_reviews.ui.main.formattedReview.FormattedReview;
import com.example.chicky_reviews.ui.main.newReview.NewReviewViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NewReviewActivity extends AppCompatActivity {

    private NewReviewViewModel reviewViewModel;

    private Button cancel;
    private Button done;
    private Button addExtraBtn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        reviewViewModel = new ViewModelProvider(this).get(NewReviewViewModel.class);
//        View root = inflater.inflate(R.layout.review_page, container, false);
        setContentView(R.layout.review_page);

        cancel = findViewById(R.id.cancel_btn);
        done = findViewById(R.id.review_done_btn);
        addExtraBtn = findViewById(R.id.add_extra_button);

        addExtraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.add_extra_button:
                        newExtra();
                        break;
                }
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.review_done_btn:
                        saveReview();
                        break;
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.cancel_btn:
                        cancel();
                        break;
                }
            }
        });

    }

    public void newExtra() {
        Toast.makeText(this, "New Extra!", Toast.LENGTH_SHORT).show();
        //this.finish();

        Intent addExtraIntent = new Intent(this, ExtraActivity.class);
        this.startActivity(addExtraIntent);
    }


    public void saveReview() {
        Toast.makeText(this, "Formatting review", Toast.LENGTH_SHORT).show();
        //this.finish();

        String restyName = ((EditText) findViewById(R.id.resty_name_input)).getText().toString();
        String sandieName = ((EditText) findViewById(R.id.chicky_name_input)).getText().toString();
        String intro = ((EditText) findViewById(R.id.intro_input)).getText().toString();
        String conclusion = ((EditText) findViewById(R.id.final_thoughts_input)).getText().toString();

        Review newReview = new Review(restyName, sandieName);
        newReview.setIntro(intro);
        newReview.setConclusion(conclusion);

        //Grab all categories/ratings from review in a for loop with if checks
        //Create all categories
        //Get all ratings and assign them to their respective category
        LinearLayout newReviewRoot = findViewById(R.id.new_review_parent_lo);
        for (int i = 0; i < newReviewRoot.getChildCount(); i++) {
            if (getResources().getResourceEntryName(newReviewRoot.getChildAt(i).getId()).contains("root_LL")) {
                // Found category, assuming LinearLayout == (categoryName)_root_LL
                // Grab that category, find it's ratings, set ratings equal to category, add category to this.categproes!!!!
                LinearLayout parent = (LinearLayout) newReviewRoot.getChildAt(i);
                Category currentCategory = new Category("");
                for (int j = 0; j < parent.getChildCount(); j++) {
                    View childView = parent.getChildAt(j);
                    try {
                        if (getResources().getResourceEntryName(childView.getId()).contains("_rating_header")) {
                            //Assuming TextView == (categoryName)_rating_header
                            //Child is a header, make new category of that header

                            currentCategory.setCategoryName(((TextView) parent.getChildAt(j)).getText().toString());
                            newReview.addCategory(currentCategory);
                        } else if (getResources().getResourceEntryName(childView.getId()).contains("_second_layer")) {
                            // Child is bulk of (categoryName)_root_LL assuming LL == (categoryName)_second_layer
                            // Iterate through that group

                            //Should be childView.getChildCount?
                            for (int k = 0; k < ((LinearLayout) parent.getChildAt(j)).getChildCount(); k++) {
                                try {
                                    if(getResources().getResourceEntryName(((LinearLayout) parent.getChildAt(j)).getChildAt(k).getId()).contains("_et_")){
                                        // Found ratings, iterate through, assuming LL = (category)_et_ratings
                                        //Loop through individual ratings
                                        LinearLayout ratings = (LinearLayout) ((LinearLayout) parent.getChildAt(j)).getChildAt(k);
                                        for(int l = 0; l < ratings.getChildCount(); l++){
                                            //Looping through ETs
                                            //EditText ratingView = (EditText) ((LinearLayout) parent.getChildAt(j)).getChildAt(k);
                                            EditText ratingView = (EditText) ratings.getChildAt(l);
                                            //Grabs rating name, assuming EditText == (categoryName)_(ratingName)_rating
                                            Rating currentRating = new Rating(getResources().getResourceEntryName(ratingView.getId()).split("_")[1], Double.valueOf(ratingView.getText().toString()));
                                            currentCategory.addRating(currentRating);
                                        }
                                    }
                                } catch (Resources.NotFoundException e) {
                                    //ID does not exist
                                    e.printStackTrace();
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    } catch (Resources.NotFoundException ignore) {
                        Toast.makeText(this, "No ID associated with this layout", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }


        //This is where we get all data and save as new review.
        //Add it to existing reviews
        //prompt review page
        Intent outputIntent = new Intent(this, FormattedReview.class);

        // Calling a Parcel: https://stackoverflow.com/questions/49249234/what-is-parcelable-in-android
        outputIntent.putExtra("newReview", newReview);

        this.startActivity(outputIntent);
    }

    public void cancel() {
        Toast.makeText(this, "aborting", Toast.LENGTH_SHORT).show();
        this.finish();
    }
}
