package com.example.chicky_reviews;

import android.os.Parcel;
import android.os.Parcelable;

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

    /* Constructors */
    public Review(String restName, String sandwichName
    ) {
        this.intro = "";
        this.restName = restName;
        this.sandwichName = sandwichName;
        this.conclusion = "";
        this.categories = new ArrayList<Category>();


    }

    public Review(String restName, String sandwichName, ArrayList<Category> categories) {
        this.intro = "";
        this.restName = restName;
        this.sandwichName = sandwichName;
        this.conclusion = "";
        this.categories = categories;
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


    public Review(Parcel in){
        this.intro = in.readString();
        this.restName = in.readString();
        this.sandwichName = in.readString();
        this.conclusion = in.readString();

        this.categories = in.readArrayList(Category.class.getClassLoader());
        //this.categories = in.readArrayList(null);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.intro);
        dest.writeString(this.restName);
        dest.writeString(this.sandwichName);
        dest.writeString(this.conclusion);

        //dest.writeParcelableArray(this.categories);
        dest.writeList(this.categories);
    }

    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>(){
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
