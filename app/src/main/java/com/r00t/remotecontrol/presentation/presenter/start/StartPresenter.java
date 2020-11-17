package com.r00t.remotecontrol.presentation.presenter.start;

import android.content.Intent;

import androidx.lifecycle.ViewModelProvider;

import com.r00t.remotecontrol.data.log.LogEntity;
import com.r00t.remotecontrol.domain.interactor.multi.GetLogsUseCase;
import com.r00t.remotecontrol.presentation.DefaultObserver;
import com.r00t.remotecontrol.presentation.view.StartView;
import com.r00t.remotecontrol.presentation.view.activity.MainActivity;
import com.r00t.remotecontrol.presentation.viewmodel.MainViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

public class StartPresenter implements StartViewPresenter {
    private final GetLogsUseCase getLogs;

    private StartView view;
    private MainViewModel viewModel;

    @Inject
    public StartPresenter(GetLogsUseCase getLogs) {
        this.getLogs = getLogs;
    }

    @Override
    public void setView(StartView view) {
        this.view = view;
        init();
    }

    @Override
    public void onNext(String serverUri) {
        viewModel.setServerUri(serverUri);
        view.activity().startActivity(new Intent(view.activity(), MainActivity.class));
        view.activity().finish();
    }

    private void init() {
        viewModel = new ViewModelProvider(view.activity()).get(MainViewModel.class);
        viewModel.getServerUri().observe(view.activity(), s -> {
            String[] address = s.split(":");
            view.setServerAddress(address[0]);
            view.setServerPort(address[1]);
        });

        getLogs.execute(new DefaultObserver<List<LogEntity>>() {
            @Override
            public void onNext(@NonNull List<LogEntity> logEntities) {
                view.renderLogsEntities(logEntities);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showErrorMessage(e.getMessage());
                e.printStackTrace();
            }
        }, null);
    }
}
