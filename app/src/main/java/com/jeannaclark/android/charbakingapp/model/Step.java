package com.jeannaclark.android.charbakingapp.model;

/**
 * Created by birdy on 6/20/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;


/**
 * Created by birdy on 6/20/17.
 */

@IgnoreExtraProperties
public class Step implements Parcelable {

    public String description;
    public String shortDescription;
    public String thumbnailURL;
    public String videoURL;
    public int stepNumber;

    public Step() {}

    public Step(String description, String shortDescription, String thumbnailURL, String videoURL, int stepNumber) {
        this.description = description;
        this.shortDescription = shortDescription;
        this.thumbnailURL = thumbnailURL;
        this.videoURL = videoURL;
        this.stepNumber = stepNumber;
    }

    public Step(Parcel in) {
        description = in.readString();
        shortDescription = in.readString();
        thumbnailURL = in.readString();
        videoURL = in.readString();
        stepNumber = in.readInt();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeString(shortDescription);
        parcel.writeString(thumbnailURL);
        parcel.writeString(videoURL);
        parcel.writeInt(stepNumber);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Step createFromParcel(Parcel parcel) {
            return new Step(parcel);
        }

        public Step[] newArray(int size) {
            return new Step[size];
        }
    };
}
