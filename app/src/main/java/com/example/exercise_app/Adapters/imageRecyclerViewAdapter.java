package com.example.exercise_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise_app.Models.ImageModel;
import com.example.exercise_app.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class imageRecyclerViewAdapter extends RecyclerView.Adapter<imageRecyclerViewAdapter.MyViewHolder> {

        //declaration
        private List<ImageModel> imageList;
        private Context mContext;
        int width;
        int columns;
        private onItemClick itemClickInterface;
        public imageRecyclerViewAdapter(Context mContext,List<ImageModel> imageList,int width,int columns,onItemClick itemClickInterface) {
            this.mContext = mContext;
            this.imageList=imageList;
            this.width=width;
            this.columns=columns;
            this.itemClickInterface=itemClickInterface;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // infalte the item Layout
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
            // set the view's size, margins, paddings and layout parameters
            return new MyViewHolder(item,itemClickInterface);
        }


    @Override
        public int getItemCount() {
            return imageList.size();
        }
        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            // init the item view's
            SimpleDraweeView draweeView ;
            TextView titleTextView ;
            TextView copyRightTextView;

            onItemClick itemClickInterface;
            public MyViewHolder(View itemView,onItemClick itemClickInterface) {
                super(itemView);
                // get the reference of item view's
                draweeView = itemView.findViewById(R.id.item_image);
                titleTextView=itemView.findViewById(R.id.item_image_title);
                copyRightTextView =itemView.findViewById(R.id.item_image_copyright);
                this.itemClickInterface=itemClickInterface;

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                itemClickInterface.onItemClickListener(getAdapterPosition());
            }
        }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ImageModel image_model = imageList.get(position);


        //if view is null then inflate the grid_item layout


        ///initializing SimpleDraweeView and TextViews from view

        // Convert the dps to pixels, based on density scale
        final SimpleDraweeView draweeView = holder.draweeView;

        final TextView titleTextView = holder.titleTextView;
        final TextView copyRightTextView = holder.copyRightTextView;

        //getting values from image_model and setting in SimpleDraweeView and TextViews
        String url=image_model.getImage_url();
        Uri uri = Uri.parse(url);

        int w= width/columns-(columns*5)-30;
        draweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(w, ViewGroup.LayoutParams.WRAP_CONTENT);
        draweeView.setLayoutParams(layoutParams);
        draweeView.setImageURI(uri);
        titleTextView.setText((image_model.getImage_title()));
        copyRightTextView.setText(image_model.getImage_copyRight());
    }
    public interface onItemClick
    {
        void onItemClickListener(int position);
    }


}
