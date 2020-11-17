package com.r00t.remotecontrol.presentation.modul;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.r00t.remotecontrol.data.net.NetService;
import com.r00t.remotecontrol.data.net.NetServiceImpl;
import com.r00t.remotecontrol.data.net.RetrofitService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class NetModule {

    @Singleton
    @Provides
    public Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Singleton
    @Provides
    public Retrofit.Builder provideRetrofitBuilder(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl("https://api.spermatorial.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));
    }

    @Singleton
    @Provides
    public RetrofitService provideRemoteService(Retrofit.Builder builder) {
        return builder.build().create(RetrofitService.class);
    }

    @Singleton
    @Provides
    public NetService provideNetService(NetServiceImpl netService) {
        return netService;
    }
}
