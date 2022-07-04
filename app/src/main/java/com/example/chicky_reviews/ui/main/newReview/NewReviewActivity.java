package com.example.chicky_reviews.ui.main.newReview;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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
import java.util.Locale;

public class NewReviewActivity extends AppCompatActivity {

    private NewReviewViewModel reviewViewModel;

    private Button cancel;
    private Button done;
    private Button addExtraBtn;
    private LinearLayout rootLayout;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        reviewViewModel = new ViewModelProvider(this).get(NewReviewViewModel.class);
//        View root = inflater.inflate(R.layout.review_page, container, false);
        setContentView(R.layout.review_page);

        rootLayout = findViewById(R.id.new_review_parent_lo);
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

        Intent addExtraIntent = new Intent(this, ExtraActivity.class);
        this.startActivity(addExtraIntent);
    }

    /*** Updates view when returning from add extra ***/
    @Override
    public void onResume() {
        addExtraView();
        super.onResume();
    }

    public void addExtraView() {
        //Thirds layout params
        LinearLayout.LayoutParams verticalThirdParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        verticalThirdParams.weight = 1;
        //verticalThirdParams.gravity = Gravity.CENTER;

        //Label text view params
        LinearLayout.LayoutParams ratingLabelParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ratingLabelParams.gravity = Gravity.CENTER_VERTICAL;
        ratingLabelParams.weight = 1;

        //ET params
        LinearLayout.LayoutParams ratingValueParams = ratingLabelParams;
        ratingValueParams.gravity = Gravity.CENTER;
        ratingValueParams.topMargin = 10;
        ratingValueParams.rightMargin = 10;
        ratingValueParams.bottomMargin = 10;
        ratingValueParams.leftMargin = 10;

        ArrayList<Category> extraCategories = null;
        try {
            if (getIntent().getExtras().get("extraCategories") != null) {
                extraCategories = (ArrayList<Category>) getIntent().getExtras().get("extraCategories");
            }
        } catch (Exception e){
            return;
        }

        if (extraCategories != null) {
            Toast.makeText(this, "Adding extras", Toast.LENGTH_SHORT).show();
            for (int i = 0; i < extraCategories.size(); i++) {
                Category currentCategory = extraCategories.get(i);
                LinearLayout.LayoutParams rootParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

                // root layout
                LinearLayout newExtraRootLL = new LinearLayout(this);
                newExtraRootLL.setOrientation(LinearLayout.VERTICAL);
                newExtraRootLL.setLayoutParams(rootParams);
                newExtraRootLL.setTag(currentCategory.getCategoryName() + "_root_LL");

                //Header
                TextView extraHeader = new TextView(this);
                LinearLayout.LayoutParams headerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //headerParams.gravity = Gravity.CENTER;
                extraHeader.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
                extraHeader.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                extraHeader.setLayoutParams(headerParams);
                extraHeader.setText(currentCategory.getCategoryName().toUpperCase(Locale.ROOT));
                extraHeader.setTag(currentCategory.getCategoryName() + "_rating_header");
                newExtraRootLL.addView(extraHeader);

                //Second layer
                LinearLayout newExtraSecondLayer = new LinearLayout(this);
                newExtraSecondLayer.setOrientation(LinearLayout.HORIZONTAL);
                newExtraSecondLayer.setLayoutParams(rootParams);
                newExtraSecondLayer.setTag(currentCategory.getCategoryName() + "_second_layer");

                //Ratings
                // Label third
                LinearLayout newExtraRatingNames = new LinearLayout(this);
                newExtraRatingNames.setLayoutParams(verticalThirdParams);
                newExtraRatingNames.setTag(currentCategory + "_rating_names");

                // ET third
                LinearLayout newExtraRatingValues = new LinearLayout(this);
                newExtraRatingValues.setLayoutParams(verticalThirdParams);
                newExtraRatingValues.setTag(currentCategory.getCategoryName() + "_et_ratings");

                // Button third
                // TODO


                for (int j = 0; j < currentCategory.getNumberOfRatings(); i++) {
                    Rating currentRating = currentCategory.getCurrentRating(i);

                    //Labels
                    TextView currentRatingLabel = new TextView(this);
                    currentRatingLabel.setLayoutParams(ratingLabelParams);
                    currentRatingLabel.setText(currentRating.getRatingName());
                    currentRatingLabel.setTag(currentCategory.getCategoryName() + "_" + currentRating.getRatingName() + "_label");  //Might be irrelevant
                    newExtraRatingNames.addView(currentRatingLabel);

                    //Ratings
                    EditText currentRatingET = new EditText(this);
                    currentRatingET.setInputType(EditorInfo.TYPE_NUMBER_FLAG_DECIMAL);
                    currentRatingET.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    currentRatingET.setEms(4);
                    currentRatingET.setTag(currentCategory.getCategoryName() + "_" + currentRating.getRatingName() + "_rating");
                    newExtraRatingValues.addView(currentRatingET);

                    //Buttons
                }
                //Add sections to second layer
                newExtraSecondLayer.addView(newExtraRatingNames);
                newExtraSecondLayer.addView(newExtraRatingValues);
                //Buttons

                //Add second layer to new root LL
                newExtraRootLL.addView(newExtraSecondLayer);

                //Add Layout to main root just above extra button
                rootLayout.addView(newExtraRootLL, rootLayout.getChildCount() - 5);
            }
        } else {
            Toast.makeText(this, "No extras to populate", Toast.LENGTH_SHORT).show();
            return;
        }
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

        //LinearLayout newReviewRoot = findViewById(R.id.new_review_parent_lo);
        LinearLayout newReviewRoot = rootLayout;
        for (int i = 0; i < newReviewRoot.getChildCount(); i++) {
            if (getResources().getResourceEntryName(newReviewRoot.getChildAt(i).getId()).contains("root_LL") || newReviewRoot.getChildAt(i).getTag().toString().contains("root_LL")) {
                // Found category, assuming LinearLayout == (categoryName)_root_LL
                // Grab that category, find it's ratings, set ratings equal to category, add category to this.categproes!!!!
                LinearLayout parent = (LinearLayout) newReviewRoot.getChildAt(i);
                Category currentCategory = new Category("");
                for (int j = 0; j < parent.getChildCount(); j++) {
                    View childView = parent.getChildAt(j);
                    try {
                        if (getResources().getResourceEntryName(childView.getId()).contains("_rating_header") || childView.getTag().toString().contains("_rating_header")) {
                            //Assuming TextView == (categoryName)_rating_header
                            //Child is a header, make new category of that header

                            currentCategory.setCategoryName(((TextView) parent.getChildAt(j)).getText().toString());
                            newReview.addCategory(currentCategory);
                        } else if (getResources().getResourceEntryName(childView.getId()).contains("_second_layer") || childView.getTag().toString().contains("_second_layer")) {
                            // Child is bulk of (categoryName)_root_LL assuming LL == (categoryName)_second_layer
                            // Iterate through that group

                            //Should be childView.getChildCount?
                            for (int k = 0; k < ((LinearLayout) parent.getChildAt(j)).getChildCount(); k++) {
                                try {
                                    if (getResources().getResourceEntryName(((LinearLayout) parent.getChildAt(j)).getChildAt(k).getId()).contains("_et_")) {
                                        // Found ratings, iterate through, assuming LL = (category)_et_ratings
                                        //Loop through individual ratings
                                        if (k == 3) {
                                            newReview.setHasExtra(true);
                                        }
                                        LinearLayout ratings = (LinearLayout) ((LinearLayout) parent.getChildAt(j)).getChildAt(k);
                                        for (int l = 0; l < ratings.getChildCount(); l++) {
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
