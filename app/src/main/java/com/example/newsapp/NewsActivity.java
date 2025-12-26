package com.example.newsapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.adapters.NewsAdapter;
import com.example.newsapp.fragments.HorizontalNewsFragment;
import com.example.newsapp.fragments.VerticalNewsFragment;
import com.example.newsapp.models.News;
import com.example.newsapp.utils.SharedPrefManager;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        TextView tvWelcome = findViewById(R.id.tvWelcome);
        SharedPrefManager pref = new SharedPrefManager(this);
        tvWelcome.setText("سلام " + pref.getUserFullName() + "، خوش آمدید!");

        if (savedInstanceState == null) {
            int orientation = getResources().getConfiguration().orientation;

            Fragment fragment;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                fragment = new HorizontalNewsFragment();
            } else {
                fragment = new VerticalNewsFragment();
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.news_fragment_container, fragment)
                    .commit();
        }
    }
}
