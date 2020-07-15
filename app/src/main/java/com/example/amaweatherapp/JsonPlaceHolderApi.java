package com.example.amaweatherapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface JsonPlaceHolderApi {
    @GET
    Call<List<Stacja>> getStacje(@Url String url);

}
