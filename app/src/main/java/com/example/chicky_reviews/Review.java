package com.example.chicky_reviews;

import java.lang.reflect.Array;

public class Review {
    /* Fields */
    private String review_name;

    private String rest_name;
    private String sandwich_name;
    //private String extra_name;

    private float overall_sandwich_rating;
    private float overall_chicken_rating;
    private float overall_sauce_rating;
    private float overall_bun_rating;
    //private float overall_extra_rating;

    private int chick_crunch_rating;
    private int chick_taste_rating;
    private int chick_juice_rating;

    private int sauce_amount_rating;
    private int sauce_taste_rating;

    private int bun_texture_rating;
    private int bun_taste_rating;

    /* Constructors */
    public Review(String review_name, String rest_name, String sandwich_name,
                  float overall_sandwich_rating,
                  float overall_chicken_rating,
                  float overall_sauce_rating,
                  float overall_bun_rating,
                  int chick_crunch_rating,
                  int chick_taste_rating,
                  int chick_juice_rating,
                  int sauce_amount_rating,
                  int sauce_taste_rating,
                  int bun_texture_rating,
                  int bun_taste_rating){
        this.review_name = review_name;
        this.rest_name = rest_name;
        this.sandwich_name = sandwich_name;
        this.overall_sandwich_rating = overall_sandwich_rating;
        this.overall_sauce_rating = overall_sauce_rating;
        this.overall_chicken_rating = overall_chicken_rating;
        this.overall_bun_rating = overall_bun_rating;
        this.chick_crunch_rating = chick_crunch_rating;
        this.chick_taste_rating = chick_taste_rating;
        this.chick_juice_rating = chick_juice_rating;
        this.sauce_amount_rating = sauce_amount_rating;
        this.sauce_taste_rating = sauce_taste_rating;
        this.bun_taste_rating = bun_taste_rating;
        this.bun_texture_rating = bun_texture_rating;

    }

    /* Accessors */

    /* Mutators */
}
