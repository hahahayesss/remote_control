package com.r00t.remotecontrol.data.executor;

import com.r00t.remotecontrol.domain.executor.ThreadExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class JobExecutor implements ThreadExecutor {
    private final ThreadPoolExecutor threadPoolExecutor;

    @Inject
    public JobExecutor() {
        threadPoolExecutor = new ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new JobThreadFactory());
    }

    @Override
    public void execute(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    private static class JobThreadFactory implements ThreadFactory {
        private int counter = 0;

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "android_" + counter++);
        }
    }
}
