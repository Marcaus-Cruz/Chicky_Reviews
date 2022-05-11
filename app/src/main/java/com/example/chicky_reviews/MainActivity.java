package com.example.chicky_reviews;

import android.content.Intent;
import android.os.Bundle;

import com.example.chicky_reviews.ui.main.newReview.NewReviewActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import com.example.chicky_reviews.ui.main.SectionsPagerAdapter;
import com.example.chicky_reviews.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newReview();
                Toast.makeText(MainActivity.this, "Create new review", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //When new review fab is clicked
    public void newReview(){
        Intent reviewIntent = new Intent(this, NewReviewActivity.class);
        this.startActivity(reviewIntent);
    }

}