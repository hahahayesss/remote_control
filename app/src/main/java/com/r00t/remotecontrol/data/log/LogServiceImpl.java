package com.r00t.remotecontrol.data.log;

import com.r00t.remotecontrol.domain.LogEvent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LogServiceImpl implements LogService {
    private final LogDao logDao;

    @Inject
    public LogServiceImpl(LogDao logDao) {
        this.logDao = logDao;
    }

    @Override
    public Observable<Long> insertLog(LogEvent logEvent) {
        return Observable.just(logDao.insertLog(new LogEntity(logEvent.getEvent(), System.currentTimeMillis())));
    }

    @Override
    public Observable<List<LogEntity>> getLogs() {
        return logDao.getLogs().toObservable();
    }
}
