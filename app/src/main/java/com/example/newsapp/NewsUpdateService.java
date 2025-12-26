package com.example.newsapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class NewsUpdateService extends Service {

    private static final String TAG = "NewsUpdateService";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service onStartCommand");

        sendBroadcastMessage("سرویس خبری فعال شد");

        return START_STICKY;
    }

    private void sendBroadcastMessage(String message) {
        try {
            Intent broadcastIntent = new Intent("com.example.newsapp.NEWS_UPDATE");
            broadcastIntent.putExtra("message", message);
            sendBroadcast(broadcastIntent);
            Log.d(TAG, "Broadcast sent: " + message);
        } catch (Exception e) {
            Log.e(TAG, "Error sending broadcast: " + e.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service onDestroy");
    }
}