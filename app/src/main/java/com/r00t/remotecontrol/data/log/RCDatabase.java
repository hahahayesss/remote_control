package com.r00t.remotecontrol.data.log;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = LogEntity.class, version = 2)
public abstract class RCDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "rc_database";

    public abstract LogDao getLogDao();
}
