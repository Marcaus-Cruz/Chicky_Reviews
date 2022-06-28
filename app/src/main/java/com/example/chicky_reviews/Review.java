package com.example.chicky_reviews;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Review implements Parcelable {
    /* Fields */
    private String reviewName;

    private String intro;
    private String restName;
    private String sandwichName;
    private String conclusion;

    private ArrayList<Category> categories;

    /* Constructors */
    public Review(String restName, String sandwichName
    ) {
        this.reviewName = restName + " -> " + sandwichName;
        this.intro = "";
        this.restName = restName;
        this.sandwichName = sandwichName;
        this.conclusion = "";
        this.categories = new ArrayList<Category>();


    }

    public Review(String restName, String sandwichName, ArrayList<Category> categories) {
        this.reviewName = restName + " -> " + sandwichName;
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

    public String getReviewName() {
        return this.reviewName;
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
    // https://www.vogella.com/tutorials/AndroidParcelable/article.html
    // https://stackoverflow.com/questions/7181526/how-can-i-make-my-custom-objects-parcelable
    public Review(Parcel in){
        this.reviewName = in.readString();
        this.intro = in.readString();
        this.restName = in.readString();
        this.sandwichName = in.readString();
        this.conclusion = in.readString();

        //KEYLIME
        //this.categories = in.readArrayList(null, Category);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        //dest.write
    }
}
