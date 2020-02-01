package com.example.exercise_app.Models;

import android.os.Parcel;
import android.os.Parcelable;

//A model class to store single item data
public class ImageModel{
    private String image_url,image_title,image_copyRight,date,explanation,version;

    //Constructor
    public ImageModel(String image_url, String image_title, String image_copyRight, String date, String explanation, String version) {
        this.image_url = image_url;
        this.image_title = image_title;
        this.image_copyRight = image_copyRight;
        this.date = date;
        this.explanation = explanation;
        this.version = version;
    }




    //Getter methods
    public String getImage_url() {
        return image_url;
    }
    public String getImage_title() {
        return image_title;
    }

    public String getImage_copyRight() {
        return image_copyRight;
    }

    public String getDate() {
        return date;
    }
    public String getExplanation() {
        return explanation;
    }


    public String getVersion() {
        return version;
    }




    //Setter Methods
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setImage_title(String image_title) {
        this.image_title = image_title;
    }

    public void setImage_copyRight(String image_copyRight) {
        this.image_copyRight = image_copyRight;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }



}
