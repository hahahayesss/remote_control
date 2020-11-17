package com.r00t.remotecontrol.domain.interactor.empty;

import com.r00t.remotecontrol.domain.executor.PostExecutionThread;
import com.r00t.remotecontrol.domain.executor.ThreadExecutor;
import com.r00t.remotecontrol.domain.interactor.UseCase;
import com.r00t.remotecontrol.domain.repository.DataStore;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SaveServerUriUseCase extends UseCase<Void, String> {
    private final DataStore dataStore;

    @Inject
    public SaveServerUriUseCase(DataStore dataStore, ThreadExecutor te, PostExecutionThread pet) {
        super(te, pet);
        this.dataStore = dataStore;
    }

    @Override
    public Observable<Void> buildUseCaseObservable(String s) {
        return dataStore.saveServerUri(s);
    }
}
