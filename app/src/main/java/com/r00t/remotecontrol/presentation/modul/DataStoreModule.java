package com.r00t.remotecontrol.presentation.modul;

import com.r00t.remotecontrol.data.DataStoreImpl;
import com.r00t.remotecontrol.domain.repository.DataStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DataStoreModule {

    @Singleton
    @Provides
    public DataStore provideDataStore(DataStoreImpl dataStore) {
        return dataStore;
    }
}
