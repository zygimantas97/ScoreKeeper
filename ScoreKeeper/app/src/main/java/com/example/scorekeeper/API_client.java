package com.example.scorekeeper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_client {
    public  static final String BASE_URL = "http://zygimantas.eu/scorekeeper/";
    public  static Retrofit retrofit;

    public  static Retrofit getAPI_client(){
        if (retrofit == null ){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
