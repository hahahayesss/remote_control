package com.r00t.remotecontrol.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
    private static volatile ApiController instance;
    private ApiClientService service;

    public ApiController(String serverURL) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serverURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(ApiClientService.class);
    }

    public static synchronized ApiController getInstance() {
        return instance;
    }

    public static ApiController createInstance(String serverURL) {
        instance = new ApiController(serverURL);
        return instance;
    }

    public ApiClientService getService() {
        return service;
    }
}
