package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


interface RequestInterface {
    @GET("movie/popular")
    Call<MovieResponseWrapper> getMoviesJson(@Query("api_key") String api, @Query("page") int page);

    @GET("movie/now_playing")
    Call<MovieResponseWrapper> getMoviesJson1(@Query("api_key") String api);

    @GET("movie/top_rated")
    Call<MovieResponseWrapper> getMoviesJson2(@Query("api_key") String api);
}
