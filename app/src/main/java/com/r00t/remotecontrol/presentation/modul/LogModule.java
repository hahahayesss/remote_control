package com.r00t.remotecontrol.presentation.modul;

import android.content.Context;

import androidx.room.Room;

import com.r00t.remotecontrol.data.log.LogDao;
import com.r00t.remotecontrol.data.log.LogService;
import com.r00t.remotecontrol.data.log.LogServiceImpl;
import com.r00t.remotecontrol.data.log.RCDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Module
@InstallIn(ApplicationComponent.class)
public class LogModule {

    @Singleton
    @Provides
    public RCDatabase provideCacheDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, RCDatabase.class, RCDatabase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    public LogDao provideProductDao(RCDatabase rcDatabase) {
        return rcDatabase.getLogDao();
    }

    @Singleton
    @Provides
    public LogService provideCacheService(LogServiceImpl logService) {
        return logService;
    }
}
