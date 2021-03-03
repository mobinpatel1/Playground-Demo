package com.neuralit.playground_demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class TestService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG","Services executed");
        return START_STICKY;

    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
