package com.neuralit.playground_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.theartofdev.edmodo.cropper.CropImageActivity;

import java.util.ArrayList;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;

public class CaptionImage extends AppCompatActivity implements ImageWithCaptionListener {

    private ArrayList<ImgCap> imgCapArrayList = new ArrayList<>();
    private PerfectAdapter adapter;
    private RecyclerView recyclerView;
    private ImageView select,mainStream;
    private EditText captionEt;
    private int mCurrentPosition;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caption_image);

        recyclerView = findViewById(R.id.recyclerView);
        select = findViewById(R.id.selected_photo);
        mainStream = findViewById(R.id.currentStreamImage);
        captionEt = findViewById(R.id.caption);
        fab = findViewById(R.id.fab);
        test();



    }

    private void test(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < imgCapArrayList.size(); i++){
                    Log.d("TAG","Captions : " + imgCapArrayList.get(i).getCaption() + "\n" +
                            "Image path : " + imgCapArrayList.get(i).getImagePath());
                }
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TedBottomPicker.with(CaptionImage.this).setPeekHeight(1600)
                        .showTitle(false)
                        .setCompleteButtonText("Done")
                        .setEmptySelectionText("No Select")
                        .showMultiImage(new TedBottomSheetDialogFragment.OnMultiImageSelectedListener() {
                            @Override
                            public void onImagesSelected(List<Uri> uriList) {
                                imgCapArrayList.clear();
                                for (int i=0;i<uriList.size();i++) {
                                    ImgCap imgCap = new ImgCap(i,"", uriList.get(i));
                                    imgCapArrayList.add(imgCap);
                                }

                                adapter = new PerfectAdapter(getApplicationContext(),imgCapArrayList,mainStream,
                                        CaptionImage.this);
                                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                                recyclerView.setLayoutManager(mLayoutManager);
                                recyclerView.setAdapter(adapter);
                            }
                        });

            }
        });

        captionEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                imgCapArrayList.get(mCurrentPosition).setCaption(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void imgCaptionCallBack(int position) {

        mCurrentPosition = position;
        captionEt.setText(imgCapArrayList.get(mCurrentPosition).getCaption());
    }
}