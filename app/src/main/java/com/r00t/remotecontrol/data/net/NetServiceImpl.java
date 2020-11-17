package com.r00t.remotecontrol.data.net;

import com.r00t.remotecontrol.domain.KeyboardButton;
import com.r00t.remotecontrol.domain.MouseButton;
import com.r00t.remotecontrol.domain.model.LocationVector;
import com.r00t.remotecontrol.domain.model.StatusResponse;
import com.r00t.remotecontrol.domain.model.TextData;

import javax.inject.Inject;

import io.reactivex.Observable;

public class NetServiceImpl implements NetService {
    private static final String PREFIX = "http://";
    private static final String CHANGE_MOUSE_LOCATION = "mouse/movement";
    private static final String MOUSE_BUTTON = "mouse/click";
    private static final String KEYBOARD_BUTTON = "keyboard/press";
    private static final String KEYBOARD_WRITE = "keyboard/write";

    private final RetrofitService retrofitService;

    @Inject
    public NetServiceImpl(RetrofitService retrofitService) {
        this.retrofitService = retrofitService;
    }

    @Override
    public Observable<StatusResponse> changeMouseLocation(String serverUri, LocationVector locationVector) {
        String uri = PREFIX + serverUri + "/" + CHANGE_MOUSE_LOCATION;
        return retrofitService.changeMouseLocation(uri, locationVector);
    }

    @Override
    public Observable<StatusResponse> pressMouseButton(String serverUri, MouseButton mouseButton) {
        String uri = PREFIX + serverUri + "/" + MOUSE_BUTTON + "/" + mouseButton.getType();
        return retrofitService.pressMouseButton(uri);
    }

    @Override
    public Observable<StatusResponse> pressKeyboardButton(String serverUri, KeyboardButton keyboardButton) {
        String uri = PREFIX + serverUri + "/" + KEYBOARD_BUTTON + "/" + keyboardButton.getType();
        return retrofitService.pressKeyboardButton(uri);
    }

    @Override
    public Observable<StatusResponse> writeText(String serverUri, String text) {
        String uri = PREFIX + serverUri + "/" + KEYBOARD_WRITE;
        return retrofitService.writeText(uri, new TextData(text));
    }
}
