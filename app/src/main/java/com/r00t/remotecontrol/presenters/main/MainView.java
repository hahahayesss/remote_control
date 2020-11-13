package com.r00t.remotecontrol.presenters.main;

import android.app.Activity;
import android.content.Context;

import com.r00t.remotecontrol.models.LocationVector;

public interface MainView {
    interface Presenter {
        void onMouseMove(LocationVector locationVector);

        void onMouseClicked(int buttonType);

        void onSendKey(int keyCode);

        void onWriteText(String text);

        void onDestroy();
    }

    void initViews();

    void initListeners();

    Context getContext();

    Activity getActivity();
}
