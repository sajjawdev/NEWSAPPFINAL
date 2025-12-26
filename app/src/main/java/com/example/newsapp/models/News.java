package com.example.newsapp.models;

import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("summary")
    private String summary;

    @SerializedName("date")
    private long date;

    @SerializedName("press")
    private String press;

    @SerializedName("thumbs_up")
    private int thumbsUp;

    @SerializedName("thumbs_down")
    private int thumbsDown;

    @SerializedName("media")
    private Media media;

    // Constructor
    public News() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public int getThumbsUp() {
        return thumbsUp;
    }
    public String getImageUrl() {
        if (media != null) {
            return media.getOriginal();
        }
        return null;
    }


    public void setThumbsUp(int thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public int getThumbsDown() {
        return thumbsDown;
    }

    public void setThumbsDown(int thumbsDown) {
        this.thumbsDown = thumbsDown;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    // Inner class for Media
    public static class Media {
        @SerializedName("original")
        private String original;

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
        }
    }
}
