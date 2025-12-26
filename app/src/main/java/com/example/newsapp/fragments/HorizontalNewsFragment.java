package com.example.newsapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.adapters.NewsAdapter;
import com.example.newsapp.api.ApiService;
import com.example.newsapp.models.ApiResponse;
import com.example.newsapp.models.News;

import java.util.ArrayList;
import java.util.List;

public class HorizontalNewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<News> newsList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_horizontal_news, container, false);

        recyclerView = view.findViewById(R.id.recycler_news);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        adapter = new NewsAdapter(newsList);
        recyclerView.setAdapter(adapter);

        loadNews();
        return view;
    }

    private void loadNews() {
        new ApiService().getNews(1, new ApiService.NewsCallback() {
            @Override
            public void onSuccess(ApiResponse response) {
                requireActivity().runOnUiThread(() -> {
                    newsList.clear();
                    newsList.addAll(response.getResult());
                    adapter.notifyDataSetChanged();
                });
            }

            @Override
            public void onFailure(String error) {
            }
        });
    }
}
