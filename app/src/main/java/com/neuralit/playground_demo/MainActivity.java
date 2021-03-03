package com.neuralit.playground_demo;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.media.ExifInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;
import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ScheduledExecutorService scheduler;
    Handler handler = new Handler();
    Runnable runnable;
    int delay = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG","Lifecycle : onCreate " );
        scheduler = Executors.newSingleThreadScheduledExecutor();


        try {
            ExifInterface exif = new ExifInterface(
                    "/sdcard/DCIM/Camera/IMG_20210210_171527.jpg");

            String lat = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            String lon = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
            Toast.makeText(this, lat + lon, Toast.LENGTH_SHORT).show();
            Log.d("TAG","LATLN : " + convertToDegree(lat) +"-" + lon);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    private Float convertToDegree(String stringDMS){
        Float result = null;
        String[] DMS = stringDMS.split(",", 3);

        String[] stringD = DMS[0].split("/", 2);
        Double D0 = new Double(stringD[0]);
        Double D1 = new Double(stringD[1]);
        Double FloatD = D0/D1;

        String[] stringM = DMS[1].split("/", 2);
        Double M0 = new Double(stringM[0]);
        Double M1 = new Double(stringM[1]);
        Double FloatM = M0/M1;

        String[] stringS = DMS[2].split("/", 2);
        Double S0 = new Double(stringS[0]);
        Double S1 = new Double(stringS[1]);
        Double FloatS = S0/S1;

        result = new Float(FloatD + (FloatM/60) + (FloatS/3600));

        return result;


    };

    @Override
    protected void onPause() {
        super.onPause();
//        stopService(new Intent(getApplicationContext(),TestService.class));
//        scheduler.shutdown();
        handler.removeCallbacks(runnable);
        Log.d("TAG","Lifecycle : onPause " );
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG","Lifecycle : onRestart " );
//        if (is_run == 1){
//            scheduler.scheduleAtFixedRate
//                    (new Runnable() {
//                        public void run() {
//                            startService(new Intent(getApplicationContext(),TestService.class));
//                        }
//                    }, 0, 5, TimeUnit.SECONDS);
//        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                Log.d("TAG","Run every 5 sec");
            }
        }, delay);
        Log.d("TAG","Lifecycle : onStart " );

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG","Lifecycle : onStop " );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG","Lifecycle : onDestroy " );
//        stopService(new Intent(getApplicationContext(),TestService.class));
//        scheduler.shutdown();
    }
}