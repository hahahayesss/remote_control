package com.r00t.remotecontrol.domain.interactor.multi;

import com.r00t.remotecontrol.data.log.LogEntity;
import com.r00t.remotecontrol.domain.executor.PostExecutionThread;
import com.r00t.remotecontrol.domain.executor.ThreadExecutor;
import com.r00t.remotecontrol.domain.interactor.UseCase;
import com.r00t.remotecontrol.domain.repository.DataStore;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetLogsUseCase extends UseCase<List<LogEntity>, Void> {
    private final DataStore dataStore;

    @Inject
    public GetLogsUseCase(DataStore dataStore, ThreadExecutor te, PostExecutionThread pet) {
        super(te, pet);
        this.dataStore = dataStore;
    }

    @Override
    public Observable<List<LogEntity>> buildUseCaseObservable(Void aVoid) {
        return dataStore.getLogs();
    }
}