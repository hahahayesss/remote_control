package com.r00t.remotecontrol.domain.interactor.single;

import com.r00t.remotecontrol.domain.executor.PostExecutionThread;
import com.r00t.remotecontrol.domain.executor.ThreadExecutor;
import com.r00t.remotecontrol.domain.interactor.UseCase;
import com.r00t.remotecontrol.domain.repository.DataStore;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetServerUriUserCase extends UseCase<String, Void> {
    private final DataStore dataStore;

    @Inject
    public GetServerUriUserCase(DataStore dataStore, ThreadExecutor te, PostExecutionThread pet) {
        super(te, pet);
        this.dataStore = dataStore;
    }

    @Override
    public Observable<String> buildUseCaseObservable(Void aVoid) {
        return dataStore.getServerUri();
    }
}