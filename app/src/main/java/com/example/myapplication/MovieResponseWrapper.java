package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponseWrapper {

    @SerializedName("results")
    private List<MoviesModel> results;

    public List<MoviesModel> getResults() {
        return results;}
    @SerializedName("id")
    private List<MoviesModel> id;

    public List<MoviesModel> getId() {
        return id;}


    public MovieResponseWrapper(List<MoviesModel> m){
        results=m;
    }

}
