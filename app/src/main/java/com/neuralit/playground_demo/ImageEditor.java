package com.neuralit.playground_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;
import ja.burhanrashid52.photoeditor.PhotoFilter;

public class ImageEditor extends AppCompatActivity {
    PhotoEditorView editorView;
    PhotoEditor builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_editor);


        editorView = findViewById(R.id.photoEditorView);
        editorView.getSource().setImageResource(R.drawable.test);


        builder = new PhotoEditor.Builder(getApplicationContext(),editorView)
                .setPinchTextScalable(true).build();
        builder.setFilterEffect(PhotoFilter.BRIGHTNESS);




    }
}