package com.example.exercise_app.ViewModel;


import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.exercise_app.ImageRepository.ImageRepository;
import com.example.exercise_app.Models.ImageModel;

import java.util.List;

//GridViewModel class that observes from ImageRepository and returns list of type ImageModel LiveData
public class GirdViewModel extends ViewModel {

    //instantiation
    private MutableLiveData<List<ImageModel>> mImagesList;
    private ImageRepository mImageRepository;

    //init method to initialize mImageRepository if it is not already initialized
    public void init(Context mContext)
    {
        if(mImageRepository!=null)
        {
            return;
        }

        mImageRepository=ImageRepository.getInstance(mContext);

        //getting list from mImageRepository
        mImagesList=mImageRepository.getImageList();
    }

    //method that returns LiveData of type imageList for MainActivity to observe
    public LiveData<List<ImageModel>> getImageList()
    {
        return mImagesList;
    }
}
