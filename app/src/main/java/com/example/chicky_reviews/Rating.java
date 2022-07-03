package com.example.chicky_reviews;

import android.os.Parcel;
import android.os.Parcelable;

public class Rating implements Parcelable {
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

    //Parceable
    public Rating(Parcel in){
        this.ratingName = in.readString();
        this.rating = in.readDouble();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ratingName);
        dest.writeDouble(this.rating);
    }

    // public static final Parcelable.Creator<Rating> CREATOR = new Parcelable.Creator<Rating>(){};
    public static final Parcelable.Creator<Rating> CREATOR = new Parcelable.Creator<Rating>(){
        @Override
        public Rating createFromParcel(Parcel in){
            return new Rating(in);
        }
        @Override
        public Rating[] newArray(int size){
            return new Rating[size];
        }
    };

}
