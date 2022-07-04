package com.example.chicky_reviews.ui.main.formattedReview;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chicky_reviews.R;
import com.example.chicky_reviews.Review;
import com.example.chicky_reviews.*;
import com.example.chicky_reviews.Rating;
import com.example.chicky_reviews.Category;

import java.util.ArrayList;

public class FormattedReview extends AppCompatActivity {
    LinearLayout root;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.output_page);
        root = findViewById(R.id.output_root_ll);

        Bundle data = getIntent().getExtras();
        Review newReview = data.getParcelable("newReview");

        EditText formattedOutput = new EditText(this);
        String totalString = createReview(newReview);

        formattedOutput.setText(totalString);
        root.addView(formattedOutput);

    }

    public Double calcAverageCategoryRating(Category currentCategory){
        Double average = 0.0;
        for(int i = 0; i < currentCategory.getNumberOfRatings(); i++){
            Rating currentRating = currentCategory.getCurrentRating(i);
            average += currentRating.getRating();
        }
        average /= currentCategory.getNumberOfRatings();
        currentCategory.setCategoryRating(average);
        return average;
    }

    public Double calculateTotalRating(Review review, Boolean extraRating){
        Double average = 0.0;
        if(extraRating) {
            for (int i = 0; i < review.getCategories().size(); i++) {
                average += review.getCategories().get(i).getCategoryRating();
            }
            average /= review.getCategories().size();
            review.setExtraTotalRating(average);
            return average;
        } else{
            for (int i = 0; i < 3; i++) {
                average += review.getCategories().get(i).getCategoryRating();
            }
            average /= 3;
            review.setTotalRating(average);
            return average;
        }
    }

    public String createReview(Review newReview){
        // Header
        String totalString = "Sandie: " + newReview.getSandwichName() + " at " + newReview.getRestName() + "\n";
        totalString += "Location: " + newReview.getRestName() + "\n\n";
        // Introduction
        totalString += newReview.getIntro() + "\n \n";
        // Review
        totalString += "My Review: \n";
        // Categories
        ArrayList<Category> categories = newReview.getCategories();
        for(int i = 0; i < categories.size(); i++){
            // List categories
            Category currentCategory = categories.get(i);
            if(i == 3){
                totalString += "EXTRAS/SPECIALS:";
            }
            totalString += currentCategory.getCategoryName() + ": " + Math.round(calcAverageCategoryRating(currentCategory)*100)/100 + "\n";
            for(int j = 0; j < currentCategory.getNumberOfRatings(); j++) {
                // List ratings
                Rating currentRating = currentCategory.getCurrentRating(j);
                totalString += "/- " + currentRating.getRatingName() + ": " + Math.round(currentRating.getRating() * 100) / 100;
                //Maybe set 'Reaction' on page
                totalString += " -> \n";
            }
            totalString += "\n";

            if(i == 2){
                //Set overall rating, add those emojis
                if(!newReview.getHasExtra()){
                    totalString += "OVERALL RATING: " + Math.round(calculateTotalRating(newReview, false)*100)/100 + "\n\n";
                } else{
                    totalString += "OG RATING: " + Math.round(calculateTotalRating(newReview, false)*100)/100 + "\n\n";
                }
            }
        }
        // Set alternate rating
        if(newReview.getHasExtra()){
            totalString += "ALTERNATE RATING: " + Math.round(calculateTotalRating(newReview, true)*10)/10 + "\n\n";
        }
        totalString += newReview.getConclusion() + "\n \n";

        //Current hit list

        //HashTags

        return totalString;
    }

}
