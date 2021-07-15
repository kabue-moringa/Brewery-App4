package com.moringaschool.network;

import com.moringaschool.models.BreweriesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface BreweryApi {

    @GET("breweries")
    Call<List<BreweriesResponse>>getBreweriesResponse(

    );


}

