package com.example.newsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NewsBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if ("com.example.newsapp.NEWS_UPDATE".equals(intent.getAction())) {
            Toast.makeText(context,
                    intent.getStringExtra("message"),
                    Toast.LENGTH_LONG).show();
        }
    }
}
