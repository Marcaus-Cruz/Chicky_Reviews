package com.example.chicky_reviews;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Array;
import com.example.chicky_reviews.Category;
import java.util.ArrayList;

public class Review implements Parcelable {
    /* Fields */
    private String intro;
    private String restName;
    private String sandwichName;
    private String conclusion;
    private ArrayList<Category> categories;
    private Boolean hasExtra;
    private Double totalRating;
    private Double extraTotalRating;

    /* Constructors */
    public Review(String restName, String sandwichName
    ) {
        this.intro = "";
        this.restName = restName;
        this.sandwichName = sandwichName;
        this.conclusion = "";
        this.categories = new ArrayList<Category>();
        this.hasExtra = false;
        this.totalRating = 0.0;
        this.extraTotalRating = 0.0;
    }

    public Review(String restName, String sandwichName, ArrayList<Category> categories) {
        this.intro = "";
        this.restName = restName;
        this.sandwichName = sandwichName;
        this.conclusion = "";
        this.categories = categories;
        this.hasExtra = false;
        this.totalRating = 0.0;
        this.extraTotalRating = 0.0;
    }

    /* Accessors */

    public String getIntro() {
        return this.intro;
    }

    public String getConclusion() {
        return this.conclusion;
    }

    public String getRestName() {
        return this.restName;
    }

    public String getSandwichName() {
        return this.sandwichName;
    }

    public ArrayList<Category> getCategories() {
        return this.categories;
    }

    public Boolean getHasExtra(){return this.hasExtra;}

    public Double getTotalRating(){return this.totalRating;}

    public Double getExtraTotalRating(){return this.extraTotalRating;}

    /* Mutators */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public void setRestName(String name) {
        this.restName = name;
    }

    public void setSandwichName(String name) {
        this.sandwichName = name;
    }

    public void setCategories(ArrayList<Category> cats) {
        this.categories = cats;
    }

    public void setHasExtra(Boolean hasExtra){this.hasExtra = hasExtra;}

    public void setTotalRating(Double totalRating){this.totalRating = totalRating;}

    public void setExtraTotalRating(Double extraTotalRating){this.extraTotalRating = extraTotalRating;}

    public void addCategory(Category cat) {
        if (!this.categories.contains(cat)) {
            this.categories.add(cat);
        }
    }

    // Parcelling part
    // Ressies
    // https://www.vogella.com/tutorials/AndroidParcelable/article.html
    // https://stackoverflow.com/questions/7181526/how-can-i-make-my-custom-objects-parcelable
    // https://stackoverflow.com/questions/22446359/android-class-parcelable-with-arraylist
    // https://stackoverflow.com/questions/59453520/use-of-classloader-in-parcelable-readarraylist-in-android
    // https://guides.codepath.com/android/using-parcelable
    // https://stackoverflow.com/questions/49249234/what-is-parcelable-in-android


    @RequiresApi(api = Build.VERSION_CODES.Q)
    public Review(Parcel in){
        this.intro = in.readString();
        this.restName = in.readString();
        this.sandwichName = in.readString();
        this.conclusion = in.readString();
        this.categories = in.readArrayList(Category.class.getClassLoader());
        this.hasExtra = in.readBoolean();
        this.totalRating = in.readDouble();
        this.extraTotalRating = in.readDouble();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.intro);
        dest.writeString(this.restName);
        dest.writeString(this.sandwichName);
        dest.writeString(this.conclusion);
        dest.writeList(this.categories);
        dest.writeBoolean(this.hasExtra);
        dest.writeDouble(this.totalRating);
        dest.writeDouble(this.extraTotalRating);
    }

    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>(){
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public Review createFromParcel(Parcel in){
            return new Review(in);
        }
        @Override
        public Review[] newArray(int size){
            return new Review[size];
        }
    };
}
