package com.example.amaweatherapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("api/data/synop")
    Call<List<Stacja>> getStacje();
}
