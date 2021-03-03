package com.neuralit.playground_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.gowtham.library.utils.CompressOption;
import com.gowtham.library.utils.TrimVideo;

import java.io.File;

public class VideoEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_editor);
        TrimVideo.activity(String.valueOf(Uri.fromFile(
                new File("/sdcard/DCIM/Camera/VID_20210203_160606.mp4"))))
//        .setCompressOption(new CompressOption()) //empty constructor for default compress option
                .setHideSeekBar(true)
                .setDestination("/storage/emulated/0/DOWNLOADS")  //default output path /storage/emulated/0/DOWNLOADS
                .start(this);


    }
}