package com.r00t.remotecontrol.presentation.modul;

import com.r00t.remotecontrol.presentation.presenter.main.MainPresenter;
import com.r00t.remotecontrol.presentation.presenter.main.MainViewPresenter;
import com.r00t.remotecontrol.presentation.presenter.start.StartPresenter;
import com.r00t.remotecontrol.presentation.presenter.start.StartViewPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class PresenterModule {

    @Singleton
    @Provides
    public MainViewPresenter provideMainViewPresenter(MainPresenter mainPresenter) {
        return mainPresenter;
    }

    @Singleton
    @Provides
    public StartViewPresenter provideStartViewPresenter(StartPresenter startPresenter) {
        return startPresenter;
    }
}
