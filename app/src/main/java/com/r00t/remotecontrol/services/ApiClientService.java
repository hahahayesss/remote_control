package com.r00t.remotecontrol.services;

import com.r00t.remotecontrol.models.ApiVoidResponse;
import com.r00t.remotecontrol.models.LocationVector;
import com.r00t.remotecontrol.models.TextData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiClientService {
    @POST("mouse/movement")
    Call<ApiVoidResponse> updateMouseLocation(@Body LocationVector locationVector);

    @POST("mouse/click/{type}")
    Call<ApiVoidResponse> clickMouseButton(@Path("type") int type);

    @POST("keyboard/press/{keyCode}")
    Call<ApiVoidResponse> pressKey(@Path("keyCode") int keyCode);

    @POST("keyboard/write")
    Call<ApiVoidResponse> writeText(@Body TextData textData);

    @GET("mouse")
    Call<Map<String, Object>> temp();
}
