package com.r00t.remotecontrol.data;

import com.r00t.remotecontrol.data.cache.CacheService;
import com.r00t.remotecontrol.data.log.LogEntity;
import com.r00t.remotecontrol.data.log.LogService;
import com.r00t.remotecontrol.data.net.NetService;
import com.r00t.remotecontrol.domain.KeyboardButton;
import com.r00t.remotecontrol.domain.LogEvent;
import com.r00t.remotecontrol.domain.MouseButton;
import com.r00t.remotecontrol.domain.model.LocationVector;
import com.r00t.remotecontrol.domain.model.StatusResponse;
import com.r00t.remotecontrol.domain.repository.DataStore;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DataStoreImpl implements DataStore {
    private final CacheService cacheService;
    private final LogService logService;
    private final NetService netService;

    @Inject
    public DataStoreImpl(CacheService cacheService, LogService logService, NetService netService) {
        this.cacheService = cacheService;
        this.logService = logService;
        this.netService = netService;
    }

    @Override
    public Observable<Void> saveServerUri(String serverUri) {
        return cacheService.saveServerUri(serverUri)
                .doOnComplete(() -> logService.insertLog(LogEvent.CONNECT))
                .toObservable();
    }

    @Override
    public Observable<String> getServerUri() {
        return cacheService.getServerUri();
    }

    @Override
    public Observable<List<LogEntity>> getLogs() {
        return logService.getLogs();
    }

    @Override
    public Observable<StatusResponse> changeMouseLocation(String serverUri, LocationVector locationVector) {
        return netService.changeMouseLocation(serverUri, locationVector);
    }

    @Override
    public Observable<StatusResponse> pressMouseButton(String serverUri, MouseButton mouseButton) {
        return netService.pressMouseButton(serverUri, mouseButton)
                .doOnNext(r -> logService.insertLog(LogEvent.MOUSE));
    }

    @Override
    public Observable<StatusResponse> pressKeyboardButton(String serverUri, KeyboardButton keyboardButton) {
        return netService.pressKeyboardButton(serverUri, keyboardButton)
                .doOnNext(r -> logService.insertLog(LogEvent.KEYBOARD));
    }

    @Override
    public Observable<StatusResponse> writeText(String serverUri, String text) {
        return netService.writeText(serverUri, text)
                .doOnNext(r -> logService.insertLog(LogEvent.KEYBOARD));
    }
}
