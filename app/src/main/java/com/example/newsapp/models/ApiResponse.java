package com.example.newsapp.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ApiResponse {

    @SerializedName("status")
    private int status;

    @SerializedName("result")
    private List<News> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<News> getResult() {
        return result;
    }

    public void setResult(List<News> result) {
        this.result = result;
    }
}