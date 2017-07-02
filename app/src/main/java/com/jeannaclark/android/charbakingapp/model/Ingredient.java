package com.jeannaclark.android.charbakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by birdy on 7/1/17.
 */

@IgnoreExtraProperties
public class Ingredient implements Parcelable {

    public String ingredient;
    public String measure;
    public int quantity;

    public Ingredient() {}

    public Ingredient(String ingredient, String measure, int quantity) {
        this.ingredient = ingredient;
        this.measure = measure;
        this.quantity = quantity;
    }

    public Ingredient(Parcel in) {
        ingredient = in.readString();
        measure = in.readString();
        quantity = in.readInt();
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ingredient);
        parcel.writeString(measure);
        parcel.writeInt(quantity);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Ingredient createFromParcel(Parcel parcel) {
            return new Ingredient(parcel);
        }

        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
}