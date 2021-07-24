package com.moringaschool.models;

import java.util.ArrayList;

public class Company<latitude> {
    String index;
    public  Company(String name,
                    String phone,
                    String website,
                    double rating,
   double latitude, double longitude,
                    ArrayList<String>categories){
        this.index = "not_specified";
    }
    public  String getIndex(){
        return  index;
    }
    public void setIndex(String index){
        this.index = index;
    }
}
