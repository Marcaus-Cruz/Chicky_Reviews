package com.example.chicky_reviews;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Category {
    private String categoryName;
    private ArrayList<Rating> ratings;

    public Category(String name){
        this.categoryName = name;
        this.ratings = new ArrayList<Rating>();
    }

    public Category(String name, ArrayList<Rating> ratings){
        this.categoryName = name;
        this.ratings = ratings;
    }

    /* Accessors */

    public String getCategoryName(){return this.categoryName;}
    public ArrayList<Rating> getRatings(){
        return this.ratings;
    }

    /* Mutators */
    public void setCategoryName(String name){
        this.categoryName = name;
    }

    public void setRatings(ArrayList<Rating> ratings){
        this.ratings = ratings;
    }

    public void addRating(Rating rating){
        if(!this.ratings.contains(rating)){
            this.ratings.add(rating);
        }
    }



}
