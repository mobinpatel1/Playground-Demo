package com.neuralit.playground_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import oupson.apng.decoder.ApngDecoder;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class APNGDemo extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_p_n_g_demo);

        imageView = findViewById(R.id.imageView_test);
//        Glide.with(getApplicationContext()).load("https://apng.onevcat.com/assets/elephant.png").into(imageView);
        ApngDecoder.decodeApngAsyncInto(getApplicationContext(), R.drawable.elephant, imageView);

    }
}