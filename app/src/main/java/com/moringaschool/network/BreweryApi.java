package com.moringaschool.network;

import com.moringaschool.models.BreweriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BreweryApi {

    @GET("breweries")
    Call<BreweriesResponse>breweries(
            @Query("brewery type") String name
    );
}

