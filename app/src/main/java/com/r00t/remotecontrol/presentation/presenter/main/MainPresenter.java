package com.r00t.remotecontrol.presentation.presenter.main;

import androidx.lifecycle.ViewModelProvider;

import com.r00t.remotecontrol.domain.KeyboardButton;
import com.r00t.remotecontrol.domain.MouseButton;
import com.r00t.remotecontrol.domain.interactor.single.ChangeMouseLocationUseCase;
import com.r00t.remotecontrol.domain.interactor.single.PressKeyboardButtonUseCase;
import com.r00t.remotecontrol.domain.interactor.single.PressMouseButtonUseCase;
import com.r00t.remotecontrol.domain.interactor.single.WriteTextUseCase;
import com.r00t.remotecontrol.domain.model.DoubleValueBundle;
import com.r00t.remotecontrol.domain.model.LocationVector;
import com.r00t.remotecontrol.domain.model.StatusResponse;
import com.r00t.remotecontrol.presentation.DefaultObserver;
import com.r00t.remotecontrol.presentation.view.MainView;
import com.r00t.remotecontrol.presentation.viewmodel.MainViewModel;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

public class MainPresenter implements MainViewPresenter {
    private final ChangeMouseLocationUseCase changeMouseLocation;
    private final PressMouseButtonUseCase pressMouseButton;
    private final PressKeyboardButtonUseCase pressKeyboardButton;
    private final WriteTextUseCase writeText;

    private MainView view;
    private String serverUri;

    @Inject
    public MainPresenter(
            ChangeMouseLocationUseCase changeMouseLocation,
            PressMouseButtonUseCase pressMouseButton,
            PressKeyboardButtonUseCase pressKeyboardButton,
            WriteTextUseCase writeText
    ) {
        this.changeMouseLocation = changeMouseLocation;
        this.pressMouseButton = pressMouseButton;
        this.pressKeyboardButton = pressKeyboardButton;
        this.writeText = writeText;
    }

    @Override
    public void setView(MainView view) {
        this.view = view;
        MainViewModel viewModel = new ViewModelProvider(view.activity()).get(MainViewModel.class);
        viewModel.getServerUri().observe(view.activity(), s -> serverUri = s);
    }

    @Override
    public void onMouseMove(LocationVector locationVector) {
        changeMouseLocation.execute(new StatusResponseObserver(),
                new DoubleValueBundle<>(serverUri, locationVector));
    }

    @Override
    public void onMouseClicked(MouseButton mouseButton) {
        pressMouseButton.execute(new StatusResponseObserver(),
                new DoubleValueBundle<>(serverUri, mouseButton));
    }

    @Override
    public void onKeyPressed(KeyboardButton keyboardButton) {
        pressKeyboardButton.execute(new StatusResponseObserver(),
                new DoubleValueBundle<>(serverUri, keyboardButton));
    }

    @Override
    public void onWriteText(String text) {
        writeText.execute(new StatusResponseObserver(),
                new DoubleValueBundle<>(serverUri, text));
    }

    private class StatusResponseObserver extends DefaultObserver<StatusResponse> {
        @Override
        public void onError(@NonNull Throwable e) {
            view.showErrorMessage(e.getMessage());
            e.printStackTrace();
        }
    }
}
