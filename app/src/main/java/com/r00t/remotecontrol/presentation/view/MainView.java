package com.r00t.remotecontrol.presentation.view;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public interface MainView {

    void showErrorMessage(String message);

    Context context();

    AppCompatActivity activity();
}
