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
        String totalString = "Location: " + newReview.getRestName() + "\n";

//        totalString += "Sandie: " + (String) getIntent().getSerializableExtra("sandieName") + "\n \n";
//        totalString += (String) getIntent().getSerializableExtra("intro") + "\n \n";
//        totalString += "My Review: \n";
//        totalString += "CHICKEN: " + calcChick().toString();




        formattedOutput.setText(totalString);
        root.addView(formattedOutput);

    }

    public Double calcChick(){
        Double chickAvg = 0.0;
        ArrayList<Double> chickRatings = (ArrayList<Double>) getIntent().getSerializableExtra("chickRatings");
        for(int i = 0; i < chickRatings.size(); i++){
            chickAvg += chickRatings.get(i);
        }
        chickAvg /= chickRatings.size();

        return chickAvg;
    }

    public Double calcSauce(){
        return 0.0;
    }
    public Double calcBun(){
        return 0.0;
    }

}
