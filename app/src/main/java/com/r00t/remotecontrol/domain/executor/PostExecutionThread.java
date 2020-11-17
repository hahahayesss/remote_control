package com.r00t.remotecontrol.domain.executor;

import io.reactivex.Scheduler;

public interface PostExecutionThread {

    Scheduler getScheduler();
}
