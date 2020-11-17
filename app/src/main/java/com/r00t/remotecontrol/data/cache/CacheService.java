package com.r00t.remotecontrol.data.cache;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface CacheService {

    Completable saveServerUri(String serverUri);

    Observable<String> getServerUri();
}
