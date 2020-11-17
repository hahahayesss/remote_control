package com.r00t.remotecontrol.domain.repository;

import com.r00t.remotecontrol.data.log.LogEntity;
import com.r00t.remotecontrol.domain.KeyboardButton;
import com.r00t.remotecontrol.domain.MouseButton;
import com.r00t.remotecontrol.domain.model.LocationVector;
import com.r00t.remotecontrol.domain.model.StatusResponse;

import java.util.List;

import io.reactivex.Observable;

public interface DataStore {

    Observable<Void> saveServerUri(String serverUri);

    Observable<String> getServerUri();

    Observable<List<LogEntity>> getLogs();

    Observable<StatusResponse> changeMouseLocation(String serverUri, LocationVector locationVector);

    Observable<StatusResponse> pressMouseButton(String serverUri, MouseButton mouseButton);

    Observable<StatusResponse> pressKeyboardButton(String serverUri, KeyboardButton keyboardButton);

    Observable<StatusResponse> writeText(String serverUri, String text);
}
