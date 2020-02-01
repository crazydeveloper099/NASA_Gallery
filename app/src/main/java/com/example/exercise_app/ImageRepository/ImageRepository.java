package com.example.exercise_app.ImageRepository;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.example.exercise_app.Models.ImageModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import men.ngopi.zain.jsonloaderlibrary.JSONArrayLoaderListener;
import men.ngopi.zain.jsonloaderlibrary.JSONLoader;

//ImageRepository to provide data to GridViewModel
public class ImageRepository {

    //singleton class ImageRepository having its static instance
    private static ImageRepository instance;

    private List<ImageModel> data=new ArrayList<>();
    private final Context mContext;

    //constructor
    private ImageRepository(@NonNull Context context)
    {
        mContext=context.getApplicationContext();
    }

    //setting instance
    public static ImageRepository getInstance(@NonNull Context context)
    {
        //making thread safe
        synchronized (ImageRepository.class) {
            if (instance == null) {
                instance = new ImageRepository(context);
            }
            return instance;
        }
    }

    //function getImageList returning MutableLiveData for GridViewModel to observe
    public MutableLiveData<List<ImageModel>> getImageList()
    {
        //populate arrayList imageList
        setImageList();

        //defining listOfImages MutableLiveData for returning
        MutableLiveData<List<ImageModel>> listOfImages=new MutableLiveData<>();

        //sorting imageList byDate function defined below
        Collections.sort(data, byDate);

        //setting and returning listOfImages MutableLiveData
        listOfImages.setValue(data);
        return listOfImages;
    }

    //function to populate imageList arrayList
    private void setImageList()
    {
        //using jsonloader-library that loads json data stored in file "imageList.json"
        JSONLoader.with(mContext)
                .fileName("data.json")
                .getAsJSONArray(new JSONArrayLoaderListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj=response.getJSONObject(i);
                                String url=obj.get("url").toString();
                                String title=obj.get("title").toString();
                                String cp=obj.get("copyright").toString();
                                String date=obj.get("date").toString();
                                String explanation=obj.get("explanation").toString();
                                String version=obj.get("service_version").toString();
                                ImageModel imageModel = new ImageModel(url,title,cp,date,explanation,version);
                                data.add(imageModel);

                            } catch (JSONException e) {
                            }
                        }
                    }
                    @Override
                    public void onFailure(Exception error) {
                    }
                });
    }

    //Comparator definition byDate to compare ImageModel objects by date
    static final Comparator<ImageModel> byDate = new Comparator<ImageModel>() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        public int compare(ImageModel model1, ImageModel model2) {
            Date d1 = null;
            Date d2 = null;
            try {
                d1 = sdf.parse(model1.getDate());
                d2 = sdf.parse(model2.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //Sorting by dates in descending order
            return (d1.getDate() > d2.getDate() ? -1 : 1);
        }
    };
}



