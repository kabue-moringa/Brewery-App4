package com.moringaschool;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BreweryApi {

    @GET("breweries")
    Call<com.moringaschool.BreweriesResponse>breweries(
            @Query("brewery type") String name
    );
}

