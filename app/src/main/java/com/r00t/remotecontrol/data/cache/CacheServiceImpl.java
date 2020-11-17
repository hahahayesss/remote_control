package com.r00t.remotecontrol.data.cache;

import android.content.SharedPreferences;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class CacheServiceImpl implements CacheService {
    private static final String SERVER_URI = "remote_uri";

    private final SharedPreferences sharedPreferences;

    @Inject
    public CacheServiceImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Completable saveServerUri(String serverUri) {
        return Completable.fromRunnable(() -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SERVER_URI, serverUri);
            editor.apply();
        });
    }

    @Override
    public Observable<String> getServerUri() {
        return Observable.just(sharedPreferences.getString(SERVER_URI, "192.168.1.0:1337"));
    }
}
