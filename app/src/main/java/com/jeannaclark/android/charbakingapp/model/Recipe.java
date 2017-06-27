package com.jeannaclark.android.charbakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * Created by birdy on 6/20/17.
 */

@IgnoreExtraProperties
public class Recipe implements Parcelable {

    public ArrayList<Ingredient> ingredients;
    public ArrayList<Step> steps;
    public String name;
    public String imageURL;
    public int servings;

    public Recipe() {}

    public Recipe(ArrayList<Ingredient> ingredients, ArrayList<Step> steps, String name, String imageURL, int servings) {
        this.ingredients = ingredients;
        this.steps = steps;
        this.name = name;
        this.imageURL = imageURL;
        this.servings = servings;
    }

    public Recipe(Parcel in) {
        ingredients = in.readArrayList(null);
        steps = in.readArrayList(null);
        name = in.readString();
        imageURL = in.readString();
        servings = in.readInt();
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getServings() {
        return servings;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(ingredients);
        parcel.writeList(steps);
        parcel.writeString(name);
        parcel.writeString(imageURL);
        parcel.writeInt(servings);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Recipe createFromParcel(Parcel parcel) {
            return new Recipe(parcel);
        }

        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
