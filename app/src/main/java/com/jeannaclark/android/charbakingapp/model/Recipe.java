package com.jeannaclark.android.charbakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * Created by birdy on 7/1/17.
 */

@IgnoreExtraProperties
public class Recipe implements Parcelable {

    public ArrayList<Ingredient> ingredients;
    public ArrayList<Step> steps;
    public String name;
    public String image;
    public int servings;
    public Boolean favorite;

    public Recipe() {
    }

    public Recipe(ArrayList<Ingredient> ingredients, ArrayList<Step> steps, String name, String image,
                  int servings, Boolean favorite) {
        this.ingredients = ingredients;
        this.steps = steps;
        this.name = name;
        this.image = image;
        this.servings = servings;
        this.favorite = favorite;
    }

    public Recipe(Parcel in) {
        ingredients = in.readArrayList(null);
        steps = in.readArrayList(null);
        name = in.readString();
        image = in.readString();
        servings = in.readInt();
        favorite = in.readInt() == 1;
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

    public String getImage() {
        return image;
    }

    public int getServings() {
        return servings;
    }

    public Boolean isFavorite() {
        return favorite;
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

    public void setImage(String image) {
        this.image = image;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
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
        parcel.writeString(image);
        parcel.writeInt(servings);
        parcel.writeInt(favorite ? 1 : 0);
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