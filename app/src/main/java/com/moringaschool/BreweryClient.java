package com.moringaschool;

import com.moringaschool.brewerydb.Constants;

import retrofit2.Retrofit;

public class BreweryClient {
    private static Retrofit.Builder retrofit = null;

   public static BreweryApi getClient(){
       if(retrofit == null){
           retrofit = new Retrofit.Builder();
           baseUrl(Constants.BREWERYDB_BASE_URL);


       }

    }


}
