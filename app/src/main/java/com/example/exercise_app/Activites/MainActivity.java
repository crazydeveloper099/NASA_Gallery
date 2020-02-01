package com.example.exercise_app.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.graphics.Color;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;


import com.example.exercise_app.Adapters.imageRecyclerViewAdapter;
import com.example.exercise_app.Models.ImageModel;
import com.example.exercise_app.Models.ImageOverlayModel;
import com.example.exercise_app.R;
import com.example.exercise_app.ViewModel.GirdViewModel;

import com.stfalcon.frescoimageviewer.ImageViewer;


import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements imageRecyclerViewAdapter.onItemClick {
//Declaration

    //adapters
    imageRecyclerViewAdapter recyclerAdapter;

    //variables
    private List<ImageModel> list;
    private boolean isFragmentShown ;
    private int currentPosition;
    //views
    private RecyclerView image_recyclerView;

    //keys to get data from bundle
    private static final String KEY_IS_FRAGMENT_SHOWN  = "IS_FRAGMENT_SHOWN ";
    private static final String KEY_CURRENT_POSITION = "CURRENT_POSITION";

    //models
    private ImageOverlayModel overlayModel;
    private GirdViewModel mGirdViewModel;

    private Vibrator vibrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup_viewModel();
        init_views();

    }

    public void setup_viewModel()
    {
        //instantiation of mGridViewModel
        mGirdViewModel= ViewModelProviders.of(this).get(GirdViewModel.class);
        mGirdViewModel.init(getApplicationContext());



        //observing data from  mGridViewModel
        mGirdViewModel.getImageList().observe(this, new Observer<List<ImageModel>>() {
            @Override
            public void onChanged(List<ImageModel> imageList) {

                //notifying adapter on data change
                recyclerAdapter.notifyDataSetChanged();

            }
        });

    }

    public void init_views()
    {
        //instantiation
        image_recyclerView=findViewById(R.id.image_recyclerView);

        list=new ArrayList<>();
        list=mGirdViewModel.getImageList().getValue();


        //setting columns of image_grid_view according to display size at runtime
        float scaleFactor = getResources().getDisplayMetrics().density * 100;
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int columns = (int) ((float)width /  scaleFactor);
        columns=columns-1;
        columns=2;

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        image_recyclerView.setLayoutManager(layoutManager);

       recyclerAdapter = new imageRecyclerViewAdapter(MainActivity.this,list,width,columns,this);
        image_recyclerView.setAdapter(recyclerAdapter);

        //initialize myVib
        vibrate = (Vibrator) MainActivity.this.getSystemService(VIBRATOR_SERVICE);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        
        //saving isFragmentShown and current position in bundle
        outState.putBoolean(KEY_IS_FRAGMENT_SHOWN , isFragmentShown );
        outState.putInt(KEY_CURRENT_POSITION, currentPosition);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {

            //retrieving isFragmentShown and current position if savedInstanceState is not null
            isFragmentShown  = savedInstanceState.getBoolean(KEY_IS_FRAGMENT_SHOWN );
            currentPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION);
        }

        if (isFragmentShown ) {

            //showing fragment with the same position if isFragmentShown is true
            showFragment(currentPosition);
        }
    }

    private void showFragment(int startPosition) {

        //appending isFragmentShown to true and currentPosition to startPosition
        isFragmentShown  = true;
        currentPosition = startPosition;

        //instantiating overlayModel
        overlayModel = new ImageOverlayModel(this);

        //using ImageViewer library that uses fresco in turn to implement fragment and viewPager
        new ImageViewer.Builder<ImageModel>(MainActivity.this, list)
                .setFormatter(new ImageViewer.Formatter<ImageModel>() {
                    @Override
                    public String format(ImageModel customImage) {
                        return customImage.getImage_url(); }})

                .allowSwipeToDismiss(true)
                .allowZooming(true)
                .hideStatusBar(true)
                .setStartPosition(startPosition)
                .setOnDismissListener(new ImageViewer.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        isFragmentShown  = false;
                        vibrate.vibrate(30); }})

                .setImageChangeListener(new ImageViewer.OnImageChangeListener() {
                    @Override
                    public void onImageChange(int position) {
                        itemChangeFunction(position);

                    }
                })
                .setOverlayView(overlayModel)
                .show();

    }

    public void itemChangeFunction(int position)
    {
        //getting data from list and setting it to textViews in overlayModel
        ImageModel model=list.get(position);

        //setting description, title, date and copyright
        overlayModel.setTvDescription(model.getExplanation());
        overlayModel.setTvTitle(model.getImage_title());
        overlayModel.setTvDate(model.getDate());
        overlayModel.setTvCopyright(model.getImage_copyRight());
        currentPosition=position;

        //provide haptic feedback on every item changed
        vibrate.vibrate(30);

        //limiting tvDescription to 2 lines for every item
        overlayModel.tvDescription.setShowingLine(2);
        overlayModel.tvDescription.addShowMoreText("Read More");
        overlayModel.tvDescription.addShowLessText("Show less");
        overlayModel.tvDescription.setShowMoreColor(Color.WHITE);
        overlayModel.tvDescription.setShowLessTextColor(Color.WHITE);
        
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //clearing list on closing app
        if (isFinishing()) {
            list.clear();
        }
    }


    @Override
    public void onItemClickListener(int position) {
        showFragment(position);
       // vibrate.vibrate(30);

    }
}
