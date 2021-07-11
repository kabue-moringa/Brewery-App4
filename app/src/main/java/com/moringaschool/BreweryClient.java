package com.moringaschool;

import com.moringaschool.brewerydb.Constants;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.moringaschool.brewerydb.Constants.BREWERYDB_BASE_URL;

public class BreweryClient {
    private static Retrofit retrofit = null;

   public static BreweryApi getClient(){
       if(retrofit == null){

                    retrofit = new Retrofit.Builder()
                                    .baseUrl(BREWERYDB_BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                     .build();

       }
              return retrofit.create(BreweryApi.class);

    }






}
