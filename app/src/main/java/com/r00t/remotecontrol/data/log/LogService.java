package com.r00t.remotecontrol.data.log;

import com.r00t.remotecontrol.domain.LogEvent;

import java.util.List;

import io.reactivex.Observable;

public interface LogService {

    Observable<Long> insertLog(LogEvent logEvent);

    Observable<List<LogEntity>> getLogs();
}
