package com.r00t.remotecontrol.presentation.view;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.r00t.remotecontrol.data.log.LogEntity;

import java.util.List;

public interface StartView {

    void setServerAddress(String address);

    void setServerPort(String port);

    void renderLogsEntities(List<LogEntity> logEntities);

    void showErrorMessage(String message);

    Context context();

    AppCompatActivity activity();
}
