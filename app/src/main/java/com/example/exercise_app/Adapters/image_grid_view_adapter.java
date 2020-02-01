package com.example.exercise_app.Adapters;

import android.content.Context;

import android.net.Uri;
import android.nfc.Tag;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.exercise_app.Models.ImageModel;
import com.example.exercise_app.R;

import com.facebook.drawee.view.SimpleDraweeView;
//import com.huxq17.handygridview.HandyGridView;
//import com.huxq17.handygridview.scrollrunner.OnItemMovedListener;
import java.util.List;

//Grid view adapter class
public class image_grid_view_adapter extends BaseAdapter  {

    //declaration
    private List<ImageModel> imageList;
    private Context mContext;
    int width;
    int columns;
    public image_grid_view_adapter(Context mContext,List<ImageModel> imageList,int width,int columns) {
        this.mContext = mContext;
        this.imageList=imageList;
        this.width=width;
        this.columns=columns;
    }

    //method will be called when the item is moved to a position


    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {



        ImageModel image_model = imageList.get(position);


        //if view is null then inflate the grid_item layout
        if(view==null) {
            LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.grid_item, null);

        }

        ///initializing SimpleDraweeView and TextViews from view

        // Convert the dps to pixels, based on density scale
        final SimpleDraweeView draweeView = view.findViewById(R.id.item_image);

        final TextView titleTextView = view.findViewById(R.id.item_image_title);
        final TextView copyRightTextView = view.findViewById(R.id.item_image_copyright);

       //getting values from image_model and setting in SimpleDraweeView and TextViews
        String url=image_model.getImage_url();
        Uri uri = Uri.parse(url);

        int w= width/columns-(columns*5)-30;
        draweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(w, w);
        draweeView.setLayoutParams(layoutParams);
        draweeView.setImageURI(uri);
        titleTextView.setText((image_model.getImage_title()));
        copyRightTextView.setText(image_model.getImage_copyRight());
        return view;
    }


}