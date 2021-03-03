package com.neuralit.playground_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import iamutkarshtiwari.github.io.ananas.editimage.EditImageActivity;
import iamutkarshtiwari.github.io.ananas.editimage.ImageEditorIntentBuilder;


public class ImageEditorDemo extends AppCompatActivity {
    private final int PHOTO_EDITOR_REQUEST_CODE = 231;
    private String sourcePath = "/sdcard/DCIM/Camera/IMG_20210210_171527.jpg";
    private String destinationPath = "/storage/emulated/0/Pilgrimslog Images/test.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_editor_demo);
        openEditor();


    }

    private void openEditor(){
        try {
            Intent intent = new ImageEditorIntentBuilder(this, sourcePath,
                    destinationPath)
                    .withAddText() // Add the features you need
                    .withPaintFeature()
                    .withFilterFeature()
                    .withRotateFeature()
                    .withCropFeature()
                    .withBrightnessFeature()
                    .withSaturationFeature()
                    .withBeautyFeature()
                    .withStickerFeature()
                    .forcePortrait(true)  // Add this to force portrait mode (It's set to false by default)
                    .setSupportActionBarVisibility(false) // To hide app's default action bar
                    .build();

            EditImageActivity.start(ImageEditorDemo.this, intent,
                    PHOTO_EDITOR_REQUEST_CODE);
        } catch (Exception e) {
            Log.d("TAG", e.getMessage()); // This could throw if either `sourcePath` or `outputPath` is blank or Null
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PHOTO_EDITOR_REQUEST_CODE) { // same code you used while starting
            String newFilePath = data.getStringExtra(ImageEditorIntentBuilder.OUTPUT_PATH);
            boolean isImageEdit = data.getBooleanExtra(EditImageActivity.IS_IMAGE_EDITED, false);


            Log.d("TAG","New file path : " + newFilePath);
        }
    }
}