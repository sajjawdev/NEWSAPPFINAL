package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.newsapp.fragments.HorizontalNewsFragment;
import com.example.newsapp.fragments.VerticalNewsFragment;
import com.example.newsapp.utils.SharedPrefManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int PERMISSION_REQUEST_CODE = 100;

    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkAndRequestPermissions();
    }

    private void checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSION_REQUEST_CODE);
        } else {
            initApp();
        }
    }

    private void initApp() {
        try {
            sharedPrefManager = new SharedPrefManager(this);

            String savedEmail = sharedPrefManager.getUserEmail();
            if (savedEmail == null || savedEmail.isEmpty()) {

                Intent intent = new Intent(this, AuthActivity.class);
                startActivity(intent);
                finish();
                return;
            }

            showNewsFragment();

            startService(new Intent(this, NewsUpdateService.class));
        } catch (Exception e) {
            Log.e(TAG, "Error initializing app: " + e.getMessage());
            Toast.makeText(this, "خطا در بارگذاری برنامه", Toast.LENGTH_SHORT).show();
        }
    }

    private void showNewsFragment() {
        try {
            Fragment fragment;
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                fragment = new HorizontalNewsFragment();
            } else {
                fragment = new VerticalNewsFragment();
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();

            Log.d(TAG, "News fragment displayed");
        } catch (Exception e) {
            Log.e(TAG, "Error showing news fragment: " + e.getMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initApp();
            } else {
                Toast.makeText(this, "اجازه دسترسی ضروری است، برنامه بسته می‌شود", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            stopService(new Intent(this, NewsUpdateService.class));
            Log.d(TAG, "Service stopped");
        } catch (Exception e) {
            Log.e(TAG, "Error stopping service: " + e.getMessage());
        }
    }
}
