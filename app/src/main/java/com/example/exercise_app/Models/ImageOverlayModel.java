package com.example.exercise_app.Models;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.exercise_app.R;
import com.skyhope.showmoretextview.ShowMoreTextView;

//A model class for storing an item data
public class ImageOverlayModel extends RelativeLayout {

    //ShowMoreTextView is a textView library to show minimum lines of text initially and show full text content on "show more" click
    public ShowMoreTextView tvDescription;
    private TextView tvTitle;
    private TextView tvDate;
    private TextView tvCopyright;


    //constructor
    public ImageOverlayModel(Context context) {
        super(context);
        init();
    }
    //setters
    public void setTvDescription(String description) {
        tvDescription.setText(description);

    }
    public void setTvTitle(String title) {
        tvTitle.setText(title);
    }
    public void setTvDate(String date) {
        tvDate.setText(date);
    }
    public void setTvCopyright(String copyright) {
        tvCopyright.setText(copyright);
    }

    //A method to inflate image_overlay.xml to initialize textviews
    private void init() {
        View view = inflate(getContext(),R.layout.image_overlay, this);
        tvDescription =  view.findViewById(R.id.tvDescription);
        tvDescription.setShowingLine(2);
        tvDescription.addShowMoreText("Read More");
        tvDescription.addShowLessText("Show less");

        tvDescription.setShowMoreColor(Color.WHITE);
        tvDescription.setShowLessTextColor(Color.WHITE);
        tvTitle=view.findViewById(R.id.tvTitle);
        tvDate=view.findViewById(R.id.tvDate);
        tvCopyright=view.findViewById(R.id.tvCopyright);
    }
}

