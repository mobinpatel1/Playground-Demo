package com.neuralit.playground_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;

public class LikeButtonDemo extends AppCompatActivity {


    LikeButton likeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_button_demo);

        likeButton = findViewById(R.id.likeButton);
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                Toast.makeText(LikeButtonDemo.this, "Liked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                Toast.makeText(LikeButtonDemo.this, "Unliked", Toast.LENGTH_SHORT).show();

            }
        });
    }
}