package com.example.chicky_reviews;

import java.util.ArrayList;

public class Rating {
    private String ratingName;
    private Double rating;

    public Rating(String name){
        this.ratingName = name;
        this.rating = 0.0;
    }

    public Rating(String name, Double rating){
        this.ratingName = name;
        this.rating = rating;
    }

    public String getRatingName(){
        return this.ratingName;
    }

    public Double getRating(){
        return this.rating;
    }

    public void setRatingName(String name){
        this.ratingName = name;
    }

    public void setRating(Double rating){
        this.rating = rating;
    }

}
