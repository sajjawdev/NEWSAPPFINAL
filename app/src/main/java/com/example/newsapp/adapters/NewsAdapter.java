package com.example.newsapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapp.NewsActivity;
import com.example.newsapp.R;
import com.example.newsapp.models.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsVH> {

    private List<News> list;

    public NewsAdapter(List<News> list) {
        this.list = list;
    }

    public NewsAdapter(ArrayList<News> newsList, NewsActivity newsActivity) {
    }

    @NonNull
    @Override
    public NewsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        return new NewsVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsVH h, int pos) {
        News n = list.get(pos);

        h.title.setText(n.getTitle());
        h.summary.setText(n.getSummary());
        h.press.setText(n.getPress());

        Glide.with(h.itemView.getContext())
                .load(n.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .into(h.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void NewsListFragment(List<News> newsList) {

    }

    static class NewsVH extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, summary, press;

        public NewsVH(@NonNull View v) {
            super(v);
            image = v.findViewById(R.id.img_news);
            title = v.findViewById(R.id.tv_title);
            summary = v.findViewById(R.id.tv_summary);
            press = v.findViewById(R.id.tv_press);
        }
    }
}
