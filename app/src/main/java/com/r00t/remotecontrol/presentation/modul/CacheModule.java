package com.r00t.remotecontrol.presentation.modul;

import android.content.Context;
import android.content.SharedPreferences;

import com.r00t.remotecontrol.data.cache.CacheService;
import com.r00t.remotecontrol.data.cache.CacheServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Module
@InstallIn(ApplicationComponent.class)
public class CacheModule {

    @Singleton
    @Provides
    public SharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
        return context.getSharedPreferences("com.r00t.remotecontrol", Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    public CacheService provideCacheService(CacheServiceImpl cacheService) {
        return cacheService;
    }
}
