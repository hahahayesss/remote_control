package com.r00t.remotecontrol.data.log;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface LogDao {

    @Insert
    long insertLog(LogEntity logEntity);

    @Query("SELECT * FROM logs ORDER BY timestamp DESC LIMIT 15")
    Flowable<List<LogEntity>> getLogs();
}
