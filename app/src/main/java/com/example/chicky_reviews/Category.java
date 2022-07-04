package com.example.chicky_reviews;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.chicky_reviews.Rating;
import java.util.ArrayList;

public class Category implements Parcelable {
    private String categoryName;
    private Double categoryRating;
    private ArrayList<Rating> ratings;

    public Category(String name){
        this.categoryName = name;
        this.categoryRating = 0.0;
        this.ratings = new ArrayList<Rating>();
    }

    public Category(String name, ArrayList<Rating> ratings){
        this.categoryName = name;
        this.categoryRating = 0.0;
        this.ratings = ratings;
    }

    /* Accessors */

    public String getCategoryName(){return this.categoryName;}
    public Double getCategoryRating() {return this.categoryRating; }
    public ArrayList<Rating> getRatings(){
        return this.ratings;
    }
    public int getNumberOfRatings(){ return this.ratings.size(); }
    public Rating getCurrentRating(int index){ return this.ratings.get(index); }

    /* Mutators */
    public void setCategoryName(String name){
        this.categoryName = name;
    }
    public void setCategoryRating(Double average){ this.categoryRating = average;}

    public void setRatings(ArrayList<Rating> ratings){
        this.ratings = ratings;
    }

    public void addRating(Rating rating){
        if(!this.ratings.contains(rating)){
            this.ratings.add(rating);
        }
    }

    // Parcelling part
    public Category(Parcel in){
        this.categoryName = in.readString();
        this.categoryRating = in.readDouble();
        //this.ratings = in.readParcelable(Rating.class.getClassLoader());
        this.ratings = in.readArrayList(Rating.class.getClassLoader());
        //this.ratings = in.readArrayList(null);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.categoryName);
        dest.writeDouble(this.categoryRating);
        //dest.writeParcelable(this.ratings, flags);
        dest.writeList(this.ratings);
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>(){
        @Override
        public Category createFromParcel(Parcel in){
            return new Category(in);
        }
        @Override
        public Category[] newArray(int size){
            return new Category[size];
        }
    };

}
