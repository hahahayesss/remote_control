package com.r00t.remotecontrol.data.net;

import com.r00t.remotecontrol.domain.KeyboardButton;
import com.r00t.remotecontrol.domain.MouseButton;
import com.r00t.remotecontrol.domain.model.LocationVector;
import com.r00t.remotecontrol.domain.model.StatusResponse;

import io.reactivex.Observable;

public interface NetService {

    Observable<StatusResponse> changeMouseLocation(String serverUri, LocationVector locationVector);

    Observable<StatusResponse> pressMouseButton(String serverUri, MouseButton mouseButton);

    Observable<StatusResponse> pressKeyboardButton(String serverUri, KeyboardButton keyboardButton);

    Observable<StatusResponse> writeText(String serverUri, String text);
}
