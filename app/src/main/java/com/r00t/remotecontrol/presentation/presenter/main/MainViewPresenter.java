package com.r00t.remotecontrol.presentation.presenter.main;

import com.r00t.remotecontrol.domain.KeyboardButton;
import com.r00t.remotecontrol.domain.MouseButton;
import com.r00t.remotecontrol.domain.model.LocationVector;
import com.r00t.remotecontrol.presentation.view.MainView;

public interface MainViewPresenter {

    void setView(MainView view);

    void onMouseMove(LocationVector locationVector);

    void onMouseClicked(MouseButton mouseButton);

    void onKeyPressed(KeyboardButton keyboardButton);

    void onWriteText(String text);
}
