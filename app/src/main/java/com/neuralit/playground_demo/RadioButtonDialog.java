package com.neuralit.playground_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RadioButtonDialog extends AppCompatActivity {


    ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button_dialog);

        viewGroup = findViewById(android.R.id.content);
        new RadioDialogFragment().show(getSupportFragmentManager().beginTransaction(),"dialog");


    }



}