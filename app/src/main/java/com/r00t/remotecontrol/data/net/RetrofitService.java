package com.r00t.remotecontrol.data.net;

import com.r00t.remotecontrol.domain.model.LocationVector;
import com.r00t.remotecontrol.domain.model.StatusResponse;
import com.r00t.remotecontrol.domain.model.TextData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface RetrofitService {

    @POST
    Observable<StatusResponse> changeMouseLocation(@Url String url, @Body LocationVector locationVector);

    @POST
    Observable<StatusResponse> pressMouseButton(@Url String url);

    @POST
    Observable<StatusResponse> pressKeyboardButton(@Url String url);

    @POST
    Observable<StatusResponse> writeText(@Url String url, @Body TextData textData);
}
