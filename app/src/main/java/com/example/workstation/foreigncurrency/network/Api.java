package com.example.workstation.foreigncurrency.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by workstation on 07/02/2018.
 */

public class Api {
    public final static String BASE_URL = "https://api.fixer.io/";
    public static Retrofit retrofit;

    public static Retrofit getApi() {
        if (retrofit == null){
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
            retrofit = builder.client(httpClient.build()).build();
            return retrofit;
        } else {
            return retrofit;
        }
    }
}
